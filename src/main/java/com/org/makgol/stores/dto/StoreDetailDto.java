package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreDetailDto {
    private String name;
    private int likes;
    private String address;
    private String category;
    private String phone;
    private String photo;
    private String site;
}
