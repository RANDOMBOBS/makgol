package com.org.makgol.stores.controller;

import com.org.makgol.stores.bean.HttpTransactionLogger;
import com.org.makgol.stores.dto.*;
import com.org.makgol.stores.service.StoreService;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.KakaoLocalResponseVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final HttpTransactionLogger logger;

    /**
     * 룰렛 결과 페이지로 이동하는 메서드입니다.
     *
     * @param category 카테고리 정보
     * @param model    Spring의 Model 객체
     * @return 업장 리스트 페이지의 뷰 이름
     */
    @GetMapping("/rouletteResult")
    public String rouletteResult(@RequestParam("category") String category, Model model) {
        System.out.println("category = " + category);
        String nextPage = "jsp/store/store_list";
        model.addAttribute("keyword", category);
        return nextPage;
    }

    /**
     * 테스트 페이지로 이동하는 메서드입니다.
     *
     * @return 테스트 페이지의 뷰 이름
     */
    @GetMapping(value = "/test")
    public String test() {
        return "jsp/store/test";
    }

    /**
     * 업장 리스트 페이지로 이동하는 메서드입니다.
     *
     * @param x       경도 정보
     * @param y       위도 정보
     * @param keyword 검색 키워드
     * @param model   Spring의 Model 객체
     * @return 업장 리스트 페이지의 뷰 이름
     */
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

    /**
     * 업장 상세 페이지로 이동하는 메서드입니다.
     *
     * @return 업장 상세 페이지의 뷰 이름
     */
    @GetMapping(value = "/detail")
    public String detailPage() {
        return "jsp/store/store_detail";
    }

    /**
     * 업장 리스트 데이터를 조회하는 메서드입니다.
     *
     * @param longitude 경도 정보
     * @param latitude  위도 정보
     * @param keyword   검색 키워드
     * @return ResponseEntity 객체
     */
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

    /**
     * 업장 상세 데이터를 조회하는 메서드입니다.
     *
     * @param store_id 업장 아이디
     * @return ResponseEntity 객체
     */
    @GetMapping(value = "/detail_data/store_id/{store_id}")
    @ResponseBody
    public ResponseEntity<?> findStoreDetailData(@PathVariable int store_id) {
        StoreDetailDto storeDetailDtos = storeService.findStoreDetailWithId(store_id);

        KakaoLocalResponseVo<StoreDetailDto> response = new KakaoLocalResponseVo<>(true, "업장 아이디에 해당하는 세부정보를 가져옵니다.", storeDetailDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 업장 메뉴 데이터를 조회하는 메서드입니다.
     *
     * @param store_id 업장 아이디
     * @return ResponseEntity 객체
     */
    @GetMapping(value = "/menu_data/store_id/{store_id}")
    @ResponseBody
    public ResponseEntity<?> findStoreMenuData(@PathVariable int store_id) {
        List<StoreMenuDto> storeMenuDtos = storeService.findStoreMenuWithId(store_id);

        KakaoLocalResponseVo<List<StoreMenuDto>> response = new KakaoLocalResponseVo<>(true, "업장 아이디에 해당하는 메뉴를 가져옵니다.", storeMenuDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 업장 리뷰 데이터를 조회하는 메서드입니다.
     *
     * @param store_id 업장 아이디
     * @return ResponseEntity 객체
     */
    @GetMapping(value = "/review_data/store_id/{store_id}")
    @ResponseBody
    public ResponseEntity<?> findStoreReviewData(@PathVariable int store_id) {
        List<StoreReviewDto> storeReviewDtos = storeService.findStoreReviewWithId(store_id);
        Map<Integer, List<String>> storeReviewImages = new HashMap<>();

        storeReviewDtos.forEach(review -> storeReviewImages.put(review.getId(), storeService.findStoreReviewImageWithId(review.getId())));

        List<ResponseStoreReviewDto> responseStoreReviewDtos = IntStream
                .range(0, storeReviewDtos.size())
                .mapToObj(i -> new ResponseStoreReviewDto())
                .collect(Collectors.toList());

        List<UserInfoDto> userInfoDtos = storeReviewDtos
                .stream()
                .map((review) -> storeService.findUserInfoWithId(review.getUser_id()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        IntStream.range(0, responseStoreReviewDtos.size())
                .forEach(i -> {
                    ResponseStoreReviewDto responseStoreReviewDto = responseStoreReviewDtos.get(i);
                    StoreReviewDto storeReviewDto = storeReviewDtos.get(i);
                    UserInfoDto userInfoDto = userInfoDtos.get(i);

                    responseStoreReviewDto.setId(storeReviewDto.getId());
                    responseStoreReviewDto.setContent(storeReviewDto.getContent());
                    responseStoreReviewDto.setDate(storeReviewDto.getDate());
                    responseStoreReviewDto.setReview_photo_path(storeReviewImages.get(storeReviewDto.getId()));
                    responseStoreReviewDto.setUserId(storeReviewDto.getUser_id());
                    responseStoreReviewDto.setName(userInfoDto.getName());
                    responseStoreReviewDto.setUser_photo_path(userInfoDto.getPhoto_path());
                });

        KakaoLocalResponseVo<List<ResponseStoreReviewDto>> response = new KakaoLocalResponseVo<>(true, "업장 아이디에 해당하는 리뷰를 가져옵니다.", responseStoreReviewDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 리뷰를 생성하는 메서드입니다.
     *
     * @param createReviewDto 생성할 리뷰 정보를 담은 DTO 객체
     * @param userId          현재 로그인한 사용자의 아이디 (쿠키에서 추출)
     * @return ResponseEntity 객체
     */
    @PostMapping(value = "/review")
    public ResponseEntity<?> createReview(@ModelAttribute CreateReviewDto createReviewDto, @CookieValue(name = "id", required = false) Integer userId) {
        if (userId == null) {
            KakaoLocalResponseVo fail = new KakaoLocalResponseVo<>(false, "로그인되지 않았습니다.", null);
            return new ResponseEntity<>(fail, HttpStatus.FORBIDDEN);
        }

        createReviewDto.setUserId(userId);

        // 이미지 업로드를 안한 경우 빈 리스트로 초기화
        if (createReviewDto.getReviewImages() == null) {
            createReviewDto.setReviewImages(new ArrayList<>());
        }

        storeService.createReview(createReviewDto);

        KakaoLocalResponseVo response = new KakaoLocalResponseVo<>(true, "리뷰를 생성합니다.", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 리뷰 이미지를 조회하는 메서드입니다.
     *
     * @param review_id 리뷰 아이디
     * @return ResponseEntity 객체
     */
    @GetMapping(value = "/review_image/review_id/{review_id}")
    public ResponseEntity<?> findStoreReviewImageWithId(@PathVariable int review_id) {
        List<String> reviewImages = storeService.findStoreReviewImageWithId(review_id);

        KakaoLocalResponseVo<List<String>> response = new KakaoLocalResponseVo<>(true, "리뷰 이미지를 가져옵니다.", reviewImages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 좋아요 상태를 가져오는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담은 DTO 객체
     * @return ResponseEntity 객체
     */
    @GetMapping(value = "/likes")
    public ResponseEntity<?> getLikesStatus(LikesDto likesDto) {
        boolean result = storeService.getLikesStatus(likesDto);

        KakaoLocalResponseVo<Boolean> response = new KakaoLocalResponseVo<>(true, "좋아요 상태를 가져옵니다.", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 좋아요 수를 증가시키는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담은 DTO 객체
     * @param userId   현재 로그인한 사용자의 아이디 (쿠키에서 추출)
     * @return ResponseEntity 객체
     */
    @PutMapping(value = "/likes")
    public ResponseEntity<?> increaseLikesWithId(LikesDto likesDto, @CookieValue(name = "id", required = false) Integer userId) {
        if (userId == null) {
            KakaoLocalResponseVo fail = new KakaoLocalResponseVo<>(false, "로그인되지 않았습니다.", null);
            return new ResponseEntity<>(fail, HttpStatus.FORBIDDEN);
        }

        storeService.increaseLikesWithId(likesDto);
        KakaoLocalResponseVo response = new KakaoLocalResponseVo<>(true, "좋아요를 하나 올렸습니다.", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 좋아요 수를 감소시키는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담은 DTO 객체
     * @param userId   현재 로그인한 사용자의 아이디 (쿠키에서 추출)
     * @return ResponseEntity 객체
     */
    @PutMapping(value = "/delikes")
    public ResponseEntity<?> decreaseLikesWithId(LikesDto likesDto, @CookieValue(name = "id", required = false) Integer userId) {
        if (userId == null) {
            KakaoLocalResponseVo fail = new KakaoLocalResponseVo<>(false, "로그인되지 않았습니다.", null);
            return new ResponseEntity<>(fail, HttpStatus.FORBIDDEN);
        }

        storeService.decreaseLikesWithId(likesDto);
        KakaoLocalResponseVo response = new KakaoLocalResponseVo<>(true, "좋아요를 하나 내렸습니다.", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 리뷰를 수정하는 메서드입니다.
     *
     * @param review_id       수정할 리뷰의 아이디
     * @param modifyReviewDto 수정할 리뷰 정보를 담은 DTO 객체
     * @return ResponseEntity 객체
     */
    @PutMapping(value = "/review_id/{review_id}")
    public ResponseEntity<?> modifyReviewWithId(@PathVariable int review_id, @ModelAttribute ModifyReviewDto modifyReviewDto) {
        modifyReviewDto.setReviewId(review_id);

        if (modifyReviewDto.getReviewImages() == null) {
            modifyReviewDto.setReviewImages(new ArrayList<>());
        }

        storeService.modifyReviewWithId(modifyReviewDto);

        KakaoLocalResponseVo response = new KakaoLocalResponseVo<>(true, "리뷰를 수정하였습니다.", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 리뷰를 삭제하는 메서드입니다.
     *
     * @param review_id 삭제할 리뷰의 아이디
     * @return ResponseEntity 객체
     */
    @DeleteMapping(value = "/review_id/{review_id}")
    public ResponseEntity<?> deleteReviewWithId(@PathVariable int review_id) {
        storeService.deleteReviewWithId(review_id);
        KakaoLocalResponseVo response = new KakaoLocalResponseVo<>(true, "리뷰를 삭제하였습니다.", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * 카카오 로컬 API를 호출하는 메서드입니다.
     *
     * @param kakaoLocalRequestVo 카카오 로컬 API에 전달할 요청 정보를 담은 VO 객체
     * @return 업장 정보를 담은 카카오 로컬 API 응답 JSON 객체
     */
    @GetMapping(value = "/kakao-local-api")
    public String callKakaoLocalApi(KakaoLocalRequestVo kakaoLocalRequestVo) {
        // 요청 데이터를 로그에 기록
        logger.logRequestDto(kakaoLocalRequestVo);

        // 카카오 로컬 API 호출 및 응답 데이터를 받아옴
        KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);

        // ShopInfo 리스트를 가져옴
        List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;

        // HTTP 트랜잭션 로그 기록
        HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
        httpTransactionLogger.logResponseJson(kakaoResponseJSON);

        // ShopInfo를 StoreRequestVo로 매핑
        List<StoreRequestVo> storeRequestVoList = shopInfoList.stream()
                .map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
                .collect(Collectors.toList());

        try {
            // 업장 메뉴 정보를 가져오는 메서드 호출
            storeService.getMenu(storeRequestVoList);
        } catch (Exception e) {
            // 예외 처리
        }

        // 카카오 로컬 API로부터 받은 업장 정보를 뷰로 전달
        List<KakaoLocalResponseJSON.ShopInfo> shops = kakaoResponseJSON.documents;
        return "store/store_list";
    }


}