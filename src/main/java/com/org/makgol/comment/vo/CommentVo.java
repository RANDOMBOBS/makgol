package com.org.makgol.comment.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CommentVo {
	int id;
	
	int user_id;
	
	int board_id;
	
	String date;
	
	String content;
	
	String nickname;

	String photo;
	
}
