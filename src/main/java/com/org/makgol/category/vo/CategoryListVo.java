package com.org.makgol.category.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryListVo {

    int id;
    int store_id;
    String menu_name;
    String category;
    String photo;
    String photoPath;

}
