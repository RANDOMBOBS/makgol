package com.org.makgol.category.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias(value = "categoryListVo")
public class CategoryListVo {

    int id;
    String category;
    String menu;


}
