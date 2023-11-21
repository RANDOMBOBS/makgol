package com.org.makgol.users.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class AuthNumberVo {
	
	@Email
	@NotBlank(message = "메일 필수")
	private String email;
	
private int auth_number;
}
