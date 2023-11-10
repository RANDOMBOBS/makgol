package com.org.makgol.stores.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.org.makgol.stores.bean.HttpTransactionLogger;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.makgol.stores.service.StoreService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

	private final StoreService storeService;
	private final HttpTransactionLogger logger;
	
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