package com.org.makgol.comment.vo;

import lombok.Data;

@Data
public class CommentResponseVo {
	int id;
	
	int user_id;
	
	int board_id;
	
	String date;
	
	String content;
	
	String nickname;

	String photo_path;
	
}
