package com.org.makgol.boards.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class BoardDetailResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    int user_id;
    int hit;
    String title;
    String date;
    String contents;
    String category;
    int sympathy;
    String name;
    String user_photo_path;
    String board_photo_path;
    List<String> images;



}
