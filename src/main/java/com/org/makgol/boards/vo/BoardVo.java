package com.org.makgol.boards.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
public class BoardVo implements Serializable {
    private static final long serialVersionUID = 1L;
    int b_id;
    int user_id;
    int hit;
    String title;
    String date;
    String contents;
    String category;
    int sympathy;
    String photo;
    String photo_path;
    String name;
    String grade;
    MultipartFile photoFile;
    List<String> images;
}