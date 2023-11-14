package com.org.makgol.users.vo;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import net.bytebuddy.implementation.bind.annotation.Empty;
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
	@NotBlank(message = "경도")
	@NotEmpty
    double longitude;

    //위도
	@NotBlank(message = "위도")
	@NotEmpty
    double latitude;

	@NotBlank(message = "회원등급(필수)")
	@NotEmpty
	String grade;

	String date;

	@NotBlank(message = "주소")
	@NotEmpty
	String address;
}
