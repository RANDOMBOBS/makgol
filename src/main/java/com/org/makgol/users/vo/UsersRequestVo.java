package com.org.makgol.users.vo;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
@ToString
public class UsersRequestVo {
	// ID
	int id;

	// 이름
	@NotBlank(message = "이름(필수)")
	@NotEmpty
	String name;
	
	// 이메일 
	@Email
	@NotBlank(message = "이메일(필수)")
	@NotEmpty
	String email;
	
	// 비밀번호
	@NotBlank(message = "비밀번호(필수)")
	@NotEmpty
	String password;
	
	// 전화번호 
	@NotBlank(message = "전화번호(필수)")
	@NotEmpty
	String phone;
	
	//photo file
	MultipartFile photoFile;

	//파일 이름
	String photo;

	// 프로필사진경로
	String photo_path;

	//경도
    double longitude = 127.027589;
    //위도
    double latitude = 37.498102;


	@NotBlank(message = "회원등급(필수)")
	@NotEmpty
	String grade;

	String date;
	String address;
}
