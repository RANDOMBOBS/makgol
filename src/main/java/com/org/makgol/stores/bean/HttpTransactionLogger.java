package com.org.makgol.stores.bean;

import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import org.springframework.stereotype.Component;


@Component
public class HttpTransactionLogger {

	public void logRequestDto(KakaoLocalRequestVo vo) {
		System.out.println("검색 요청 데이터");
		System.out.println("[위도]: " + vo.getX());
		System.out.println("[경도]: " + vo.getY());
		System.out.println("[키워드]: " + vo.getKeyword());
		System.out.println("[반경]: " + vo.getRadius());
		System.out.println("[식당 개수]: " + vo.getSize());
		System.out.println("[페이지 개수]: " + vo.getPage());
	}

	public void logResponseJson(KakaoLocalResponseJSON json) {
		System.out.println("검색 응답 데이터");
		System.out.println("[총 응답 개수]: " + json.documents.size());
		json.documents.forEach((shop) -> System.out.println("[식당 정보]: " + shop));
	}
}
