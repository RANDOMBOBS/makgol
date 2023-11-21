package com.org.makgol.comment.vo;

import lombok.Data;

@Data
public class CommentRequestVo {
	int user_id;
	int board_id;
	String content;
	String nickname;
	String grade;
}
