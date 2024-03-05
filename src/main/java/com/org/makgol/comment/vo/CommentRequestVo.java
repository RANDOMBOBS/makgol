package com.org.makgol.comment.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentRequestVo implements Serializable {
	private static final long serialVersionUID = 1L;
	int user_id;
	int board_id;
	String content;
	String nickname;
	String grade;
}
