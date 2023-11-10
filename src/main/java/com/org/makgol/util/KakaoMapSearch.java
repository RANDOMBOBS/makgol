package com.org.makgol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.service.StoreService;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMapSearch {

	private final StoreService storeService;

	public void addressSearch(String address, KakaoLocalRequestVo kakaoLocalRequestVo) throws Exception {

			kakaoLocalRequestVo.setKeyword(address);
			KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
			// ShopInfo 리스트를 가져온다
			List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
			// logger.logResponseJson(kakaoResponseJSON);
			//HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
			//httpTransactionLogger.logResponseJson(kakaoResponseJSON);

			// ShopInfo를 StoreRequestVo로 매핑
			List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
					.collect(Collectors.toList());

	}

	public void search(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo) throws Exception {
		List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();

		// 음식 카테고리 매핑
		for (int i = 0; i < categories.length; i++) {

			kakaoLocalRequestVo.setKeyword(categories[i]);
			KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
			// ShopInfo 리스트를 가져온다
			List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
			// logger.logResponseJson(kakaoResponseJSON);
			//HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
			//httpTransactionLogger.logResponseJson(kakaoResponseJSON);

			// ShopInfo를 StoreRequestVo로 매핑
			List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
					.collect(Collectors.toList());

			storeRequestVoList.addAll(storeRequestVoListAdd);

			for (StoreRequestVo storeRequestVo : storeRequestVoList) {
				storeRequestVo.setKeyword(categories[i]);
			}
		}
		
		//restApiCrawller(storeRequestVoList);
	}

	public List<StoreRequestVo> searchMenu(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo, List<StoreRequestVo> storeRequestVoList) {
		
		// 음식 카테고리 매핑
		for (int i = 1; i < categories.length; i++) {

			kakaoLocalRequestVo.setKeyword(categories[i]);
			KakaoLocalResponseJSON kakaoResponseJSON = storeService.callKakaoLocalAPI(kakaoLocalRequestVo);
			// ShopInfo 리스트를 가져온다
			List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
			// logger.logResponseJson(kakaoResponseJSON);
			//HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
			//httpTransactionLogger.logResponseJson(kakaoResponseJSON);

			
			// ShopInfo를 StoreRequestVo로 매핑
			List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
					.collect(Collectors.toList());
			
			
			int beforeAddSize = storeRequestVoList.size();
			storeRequestVoList.addAll(storeRequestVoListAdd);
			int afterAddSize = storeRequestVoList.size();
			
			
			for(int j=beforeAddSize; j < afterAddSize; j++) {
				storeRequestVoList.get(j).setKeyword(categories[0]);
				storeRequestVoList.get(j).setMenuName(categories[i]);
			}
		}
		return storeRequestVoList;
	}
	
	public HashMap<String, Object> restApiCrawller(List<StoreRequestVo> storeRequestVoList) throws Exception {


		Crawller crawller = new Crawller();
		HashMap<String, Object> hashMap = crawller.new_crawller(storeRequestVoList);
		System.out.println("after storeInfoSize --> : "+ hashMap.size()/2);

		for(int index=0; index<(hashMap.size()/2); index ++){
			StoreRequestVo storeRequestVo = (StoreRequestVo) hashMap.get("store_info_" + index);
			if(storeRequestVo == null){ continue; }
			System.out.println("thread "+ index +": 이름 : "+ storeRequestVo.getName());
			System.out.println("thread "+ index +": 주소 : "+ storeRequestVo.getAddress());
			System.out.println("thread "+ index +": 도로명 : "+ storeRequestVo.getLoad_address());
			System.out.println("thread "+ index +": 전화번호 : "+ storeRequestVo.getPhone());
			System.out.println("thread "+ index +": 카테고리 : "+ storeRequestVo.getCategory());
			System.out.println("thread "+ index +": 상세페이지 : "+ storeRequestVo.getPlace_url());
			System.out.println("thread "+ index +": 업데이트 : "+ storeRequestVo.getUpdate_date());
			System.out.println("thread "+ index +": 영업시간 : "+ storeRequestVo.getOpening_hours());
			System.out.println("thread "+ index +": 메뉴 업데이트 : "+ storeRequestVo.getMenu_update());
			System.out.println();
		}

		return hashMap;
	}
}
