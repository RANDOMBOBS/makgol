package com.org.makgol.boards.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardLikeVo {
    int id;
    int b_id;
    int my_id;
    int user_id;
    int hit;
    String title;
    String category;
    int sympathy;
    String date;
}
