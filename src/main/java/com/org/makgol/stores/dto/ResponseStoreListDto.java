package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString()
@Data
public class ResponseStoreListDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int likes;
    private double longitude;
    private double latitude;
    private String category;
    private String phone;
    private String address;
    private int distance;
    private String opening_hours;
}
