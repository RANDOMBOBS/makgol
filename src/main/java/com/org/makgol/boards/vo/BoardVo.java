package com.org.makgol.boards.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias(value = "boardVo")
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
