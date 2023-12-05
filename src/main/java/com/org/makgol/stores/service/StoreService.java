package com.org.makgol.stores.service;


import java.util.HashMap;
import java.util.List;

import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreService {
	
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;

	public KakaoLocalResponseJSON callKakaoLocalAPI(KakaoLocalRequestVo searchRequestVo) {
		String x = searchRequestVo.getX();
		String y = searchRequestVo.getY();
		String keyword = searchRequestVo.getKeyword();
		int radius = searchRequestVo.getRadius();
		int size = searchRequestVo.getSize();
		int page = searchRequestVo.getPage();

		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("https://dapi.kakao.com/v2/local/search/keyword.json")
				.queryParam("y", y)
				.queryParam("x", x)
				.queryParam("query", keyword)
				.queryParam("radius", radius)
				.queryParam("size", size)
				.queryParam("page", page)
				.queryParam("category_group_code", "FD6")
				.build();

		headers.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

		HttpEntity<String> request = new HttpEntity<>(headers);


		return restTemplate.exchange(uri.toString(), HttpMethod.GET, request, KakaoLocalResponseJSON.class).getBody();
	}



	public void getMenu(List<StoreRequestVo> storeRequestVoList) throws JsonMappingException, JsonProcessingException {


		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/api/v1/crawl/kakaoStoreCrwall";

		// HTTP 요청 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP 요청 본문에 List<StoreRequestVo> 객체 추가
		HttpEntity<List<StoreRequestVo>> request = new HttpEntity<>(storeRequestVoList, headers);

		// 서버로 HTTP GET 요청 보내기
		restTemplate.exchange(
				url,
				HttpMethod.POST,
				request,
				new ParameterizedTypeReference<HashMap<String, Object>>() {}
		);


		// 응답 처리
//		if (resStoreMap.getStatusCode() == HttpStatus.OK) {
//			for (int index = 0; index < storeMap.size()/2; index++) {
//		    	System.out.println("====================="+index+"=====================");
//
//		        System.err.println("-----------------" + "No." + (index + 1) + " store_info------------------------");
//		        System.out.println("식당 이름  -->  :" + storeRequestVo.getName());
//		        System.out.println("식당 주소 -->  :" + storeRequestVo.getAddress());
//		        System.out.println("식당 카테고리 -->  :" + storeRequestVo.getCategory());
//		        System.out.println("식당 좋아요 -->  :" + storeRequestVo.getLikes());
//		        System.out.println("식당 도로명주소  -->  :" + storeRequestVo.getLoad_address());
//		        System.out.println("식당 경도  -->  :" + storeRequestVo.getLongitude());
//		        System.out.println("식당 위도  -->  :" + storeRequestVo.getLatitude());
//		        System.out.println("식당 전화번호  -->  :" + storeRequestVo.getPhone());
//		        System.out.println("식당 상세페이지  -->  :" + storeRequestVo.getPlace_url());
//		        System.out.println("식당 운영시간  -->  :" + storeRequestVo.getOpening_hours());
//		        System.out.println("식당 사이트  -->  :" + storeRequestVo.getSite());
//		        System.out.println("식당 업데이트 날짜  -->  :" + storeRequestVo.getUpdate_date());
//		        System.out.println("식당 메뉴 업데이트 날짜  -->  :" + storeRequestVo.getMenu_update());
//
//		        for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
//		            System.out.println("메뉴 :  " + storeRequestMenuVoList.get(menuIndex).getMenu() + ",   가격  :  " + storeRequestMenuVoList.get(menuIndex).getPrice());
//		        }
//		    }
//		} else {
//		    System.out.println("서버 응답 오류: " + resStoreMap.getStatusCode());
//		}
	}

}
