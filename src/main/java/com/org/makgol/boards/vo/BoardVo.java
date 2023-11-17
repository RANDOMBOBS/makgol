package com.org.makgol.boards.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    String grade;
    String attachment;
    MultipartFile file;


}