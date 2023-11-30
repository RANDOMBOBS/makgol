package com.org.makgol.category.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequestVo {

    String menu_name;
    String photo;
    String photoPath;
    MultipartFile photoFile;
}



