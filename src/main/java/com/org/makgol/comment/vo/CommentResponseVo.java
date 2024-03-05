package com.org.makgol.comment.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	
	int user_id;
	
	int board_id;

	String title;

	String date;
	
	String content;
	
	String nickname;

	String photo_path;
	
}
