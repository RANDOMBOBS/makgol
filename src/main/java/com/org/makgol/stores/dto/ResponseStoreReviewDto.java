package com.org.makgol.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStoreReviewDto {
    int id;
    String content;
    String date;
    String name;
    String user_photo_path;
    String review_photo_path;
}