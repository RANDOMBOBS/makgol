package com.org.makgol.comment.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CommentVo {
	// ��� ��ȣ
	int id;
	
	// user ���̵�
	int user_id;
	
	// �Խñ� ��ȣ
	int board_id;
	
	// �ۼ� �ð�
	String date;
	
	// ��� ����
	String content;
	
	// ��� �ۼ��� �г���
	String nickname;

	String photo;
	
}
