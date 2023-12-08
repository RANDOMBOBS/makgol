package com.org.makgol.users.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UsersResponseVo {
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

    String weatherAddr;

    int valueX;
    int valueY;

    public void modifyMapper(UsersRequestVo usersRequestVo){
        this.id = usersRequestVo.getId();
        this.name = usersRequestVo.getName();
        this.email = usersRequestVo.getEmail();
        this.password = usersRequestVo.getPassword();
        this.phone = usersRequestVo.getPhone();
        this.photo = usersRequestVo.getPhoto();
        this.photo_path = usersRequestVo.getPhoto_path();
        this.longitude = usersRequestVo.getLongitude();
        this.latitude = usersRequestVo.getLatitude();
        this.grade = usersRequestVo.getGrade();
        this.date = usersRequestVo.getDate();
        this.address = usersRequestVo.getAddress();

    }
}
