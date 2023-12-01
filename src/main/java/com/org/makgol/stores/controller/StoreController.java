package com.org.makgol.stores.controller;

import com.org.makgol.stores.bean.HttpTransactionLogger;
import com.org.makgol.stores.dto.RequestStoreListDto;
import com.org.makgol.stores.dto.ResponseStoreListDto;
import com.org.makgol.stores.dto.StoreDetailDto;
import com.org.makgol.stores.dto.StoreMenuDto;
import com.org.makgol.stores.service.StoreService;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.KakaoLocalResponseVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final HttpTransactionLogger logger;

    @GetMapping(value = "/test")
    public String test() {
        return "jsp/store/test";
    }

    @GetMapping(value = "/list")
    public String listPage(
            @RequestParam String x,
            @RequestParam String y,
            @RequestParam String keyword,
            Model model
    ) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("keyword", keyword);
        return "jsp/store/store_list";
    }

    @GetMapping(value = "/detail")
    public String detailPage() {
        return "jsp/store/store_detail";
    }


    @GetMapping(value = "/list_data")
    @ResponseBody
    public ResponseEntity<?> findStoreListData(
            @RequestParam String longitude,
            @RequestParam String latitude,
            @RequestParam String keyword
    ) {
        RequestStoreListDto requestStoreListDto = RequestStoreListDto
                .builder()
                .longitude(longitude)
                .latitude(latitude)
                .keyword(keyword)
                .build();

        List<ResponseStoreListDto> responseStoreListDto = storeService.findStoreListData(requestStoreListDto);

        KakaoLocalResponseVo<List<ResponseStoreListDto>> response = new KakaoLocalResponseVo<>(true, "업장 리스트 정보를 가져옵니다.", responseStoreListDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/detail_data/store_id/{store_id}")
    @ResponseBody
    public ResponseEntity<?> findStoreDetailData(@PathVariable String store_id) {
        StoreDetailDto storeDetailDtos = storeService.findStoreDetailWithId(store_id);

        KakaoLocalResponseVo<StoreDetailDto> response = new KakaoLocalResponseVo<>(true, "업장 아이디에 해당하는 세부정보를 가져옵니다.", storeDetailDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value= "/menu_data/store_id/{store_id}")
    @ResponseBody
    public ResponseEntity<?> findStoreMenuData(@PathVariable String store_id) {
        List<StoreMenuDto> storeMenuDtos = storeService.findStoreMenuWithId(store_id);

        KakaoLocalResponseVo<List<StoreMenuDto>> response = new KakaoLocalResponseVo<>(true, "업장 아이디에 해당하는 메뉴를 가져옵니다.", storeMenuDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/kakao-local-api")
    public String callKakaoLocalApi(KakaoLocalRequestVo kakaoLocalRequestVo) {
        logger.logRequestDto(kakaoLocalRequestVo);

        KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);

        // ShopInfo 리스트를 가져온다
        List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;


        //logger.logResponseJson(kakaoResponseJSON);
        HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
        httpTransactionLogger.logResponseJson(kakaoResponseJSON);

        // ShopInfo를 StoreRequestVo로 매핑
        List<StoreRequestVo> storeRequestVoList = shopInfoList.stream()
                .map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
                .collect(Collectors.toList());

        try {
            storeService.getMenu(storeRequestVoList);
        } catch (Exception e) {

        }

        List<KakaoLocalResponseJSON.ShopInfo> shops = kakaoResponseJSON.documents;

        return "store/store_list";
    }

}