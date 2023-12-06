package com.org.makgol.util.kakaoMap.vo;

import lombok.Getter;

@Getter
public class Category {

	@Getter
	public enum CategoryFood {

		한식, 양식, 일식, 중식, 분식, 카페;
	}

	@Getter
	public enum CategoryKoreaStewMenu {

		한식_찌개류, 부대찌개, 김치찌개, 된장찌개, 순두부찌개, 해물찌개, 동태찌개, 감자탕, 비지찌개;
	}

	@Getter
	public enum CategoryKoreaRoastMenu {

		한식_구이류, 불고기, 갈비, 삼겹살, 제육, 오징어구이, 조개구이, 생선구이, 야채구이;
	}

	@Getter
	public enum CategoryKoreaRiceMenu {

		한식_밥류, 비빔밥, 불고기, 김치볶음밥, 김밥, 주먹밥, 떡국, 볶음밥, 제육덮밥;
	}

	@Getter
	public enum CategoryKoreaNoodleMenu {

		한식_면류, 칼국수, 수제비, 잔치국수, 비빔국수, 막국수, 쫄면, 공국수, 매밀국수;
	}

	@Getter
	public enum CategoryKoreaSnasickMenu {

		한식_분식류, 떡볶이, 튀김, 순대, 김밥;
	}

	@Getter
	public enum CategoryWesternNoodleMenu {
		양식_면류, 봉골레, 아라비아따, 투움바, 라비올리, 알프레도, 까르보나라, 토마토, 알리오올리오;
	}

	@Getter
	public enum CategoryWesternRiceMenu {
		양식_밥류, 리조또, 필라프;
	}

	@Getter
	public enum CategoryWesternRoastMenu {
		양식_구이류, 스테이크, 오믈렛, 바베큐폭립, 커틀렛;
	}

	@Getter
	public enum CategoryWesternETCMenu {
		양식_기타, 피자, 햄버거, 샌드위치, 핫도그, 감바스, 샐러드, 뇨끼, 하몽, 카나페, 스프, 스튜;
	}

	@Getter
	public enum CategoryChinaNoodleMenu {
		중식_면류, 짜장면, 짬뽕, 우육면, 마라탕, 울면, 완탕면;
	}

	@Getter
	public enum CategoryChinaRiceMenu {
		중식_밥류, 볶음밥, 잡채밥, 짬뽕밥, 짜장밥;
	}

	@Getter
	public enum CategoryChinaETCMenu {
		중식_기타, 마라샹궈, 훠궈, 딤섬, 고추잡채, 양장피, 꽃빵, 해파리냉채, 마파두부, 동파육;
	}

	@Getter
	public enum CategoryChinaFriedMenu {
		중식_튀김류, 탕수육, 깐풍기, 꿔봐로우, 어향가지, 군만두, 마라룽샤, 멘보샤, 칠리새우, 라조기, 크림새우, 난자완스
	}

	@Getter
	public enum CategoryChinaRoastMenu {
		중식_구이류, 양꼬치, 베이징덕;
	}

	@Getter
	public enum CategoryJapanRoastMenu {
		일식_구이류, 오꼬노미야끼, 타코야끼, 야끼니꾸, 야끼토리, 메로구이;
	}

	@Getter
	public enum CategoryJapanFrideMenu {
		일식_튀김류, 돈가스, 가라아게, 테바사키, 규카츠, 덴푸라;
	}

	@Getter
	public enum CategoryJapanRiceMenu {
		일식_밥류, 가츠동, 규동, 텐동, 장어덮밥, 스시, 카레, 호르몬동, 사케동, 오야코동, 알밥;
	}

	@Getter
	public enum CategoryJapanNoodleMenu {
		일식_면류, 라멘, 우동, 모밀, 소면, 야끼소바;
	}

	@Getter
	public enum CategoryGlobal_bMenu {
		세계_베트남, 쌀국수, 분짜, 반미, 월남쌈, 짜조, 스프링롤;
	}

	@Getter
	public enum CategoryGlobal_tMenu {
		세계_태국, 똠양꿍, 팟타이, 푸팟퐁커리, 시리얼새우;
	}

	@Getter
	public enum CategoryGlobal_mMenu {
		세계_맥시코, 타코, 부리또, 퀘사디아;
	}
}

