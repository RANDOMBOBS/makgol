package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

@ToString()
@Data
public class ResponseStoreListDto {
    private String name;
    private int likes;
    private double longitude;
    private double latitude;
    private String category;
    private String phone;
    private String address;
    private int distance;
}
