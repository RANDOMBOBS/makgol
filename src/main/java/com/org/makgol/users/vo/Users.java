package com.org.makgol.users.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class Users {
    // ID
    int id;

    // 이름
    String name;

    // 이메일
    String email;

    // 비밀번호
    String password;

    // 전화번호
    String phone;

    //파일 이름
    String photo;

    // 프로필사진경로
    String photo_path;

    //경도
    double longitude;
    //위도
    double latitude;

    String grade;

    String date;

    String address;
}
