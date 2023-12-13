package com.org.makgol.stores.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Data
public class StoreRequestVo {

    //식당이름
    private String    name;
    //식당 좋아요 개수
    private int 	  likes;
    //경도
    private double    longitude;
    //위도
    private double    latitude;
    //주소
    private String    address;
    //주소2
    private String    load_address;
    //카테고리
    private String    category;
    //운영시간
    private String    opening_hours;
    //전화번호
    private String    phone;
    //식당사이트
    private String    site;
    //메뉴 업데이트 날짜
    private LocalDate menu_update;
    //식당 상세페이지
    private String    place_url;
    //식당정보 업데이트 날짜
    private LocalDate update_date;
    //검색 키워드
    private String 	  keyword = "empty";
    //메뉴 이름
    private String    menuName = "empty";
    //업장 이미지
    private String    photo;

    @Builder
    public StoreRequestVo(String address_name, String category_name, String distance, String phone, String place_name,String place_url, String road_address_name, String x, String y) {

        String[] regexArray = {"한식", "양식", "중식", "분식", "일식"};


        String find_word = "";
        for (String regex : regexArray) {

            // 패턴 객체 생성
            Pattern pattern = Pattern.compile(regex);

            // Matcher 객체 생성
            Matcher matcher = pattern.matcher(category_name);

            // 매칭된 문자열을 찾고 출력
            if (matcher.find()) {
                find_word = matcher.group();

            } else {
                find_word = "기타";
            }
        }



        this.name = place_name;
        this.likes = 0;
        this.longitude = Double.parseDouble(x);
        this.latitude = Double.parseDouble(y);
        this.address = address_name;
        this.load_address = road_address_name;
        this.category = find_word;
        this.phone = phone;
        this.place_url = place_url;
    }

    @Builder
    public StoreRequestVo(String opening_hours, String site,  LocalDate menu_update, LocalDate update_date, String photo) {
        this.opening_hours = opening_hours;
        this.site = site;
        this.menu_update = menu_update;
        this.update_date = update_date;
        this.photo = photo;
    }



}
