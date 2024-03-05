package com.org.makgol.stores.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class StoreResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int       id;
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
    private String    place_url ;
    //식당정보 업데이트 날짜
    private LocalDate update_date;
    //사진
    private String    photo;
}
