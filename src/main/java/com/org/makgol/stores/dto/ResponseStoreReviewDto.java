package com.org.makgol.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStoreReviewDto {
    int id;
    String content;
    String date;
    String name;
    int userId;
    String user_photo_path;
    List<String> review_photo_path;
}