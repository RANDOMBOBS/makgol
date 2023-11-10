package com.org.makgol.stores.vo;

import lombok.Getter;

@Getter
public class Category {
	
	@Getter
	public enum CategoryFood {

		한식, 양식, 일식, 중식, 분식, 카페;
	}
	
	@Getter
	public enum CategoryKoreaStewMenu {

		찌개류, 부대찌개, 김치찌개, 된장찌개, 순두부찌개, 해물찌개, 동태찌개, 감자탕, 비지찌개;
	}
	
	@Getter
	public enum CategoryKoreaRoastMenu {

		구이류, 불고기, 갈비, 삼겹살, 대하구이, 오징어구이, 조개구이, 생선구이, 야채구이;
	}
	
	@Getter
	public enum CategoryKoreaRiceMenu {

		밥류, 비빔밥, 불고기, 김치볶음밥, 김밥, 주먹밥, 떡국, 볶음밥, 제육덮밥;
	}
	
	@Getter
	public enum CategoryKoreaNoodleMenu {

		면류, 비빔밥, 불고기, 김치볶음밥, 김밥, 주먹밥, 떡국, 볶음밥, 제육덮밥;
	}
	
}
