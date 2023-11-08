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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
@ToString
public class UsersRequestVo {
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
	
	// 프로필사진경로
	String photo;

	//경도
    double longitude = 127.027589;
    //위도
    double latitude = 37.498102;

	// ID
	int id;

	@NotBlank(message = "회원등급(필수)")
	@NotEmpty
	String grade;

	String date;
	String address;


}
