package com.org.makgol.stores.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreMenuInfo {
    private String name;
    private int likes;
    private double longitude;
    private double latitude;
    private String address;
    private String load_address;
    private String category;
    private String opening_hours;
    private String phone;
    private String site;
    private String menu_update;
    private String place_url;
    private LocalDate update_date;

    // Getter와 Setter 메서드
}