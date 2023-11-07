package com.org.makgol.boards.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoardVo {


	int b_id;	
	int user_id;
	int hit;
	String title;
	String date;
	String contents;
	String category;
	int sympathy;
	String name;
	String attachment;
	
	

	
}
