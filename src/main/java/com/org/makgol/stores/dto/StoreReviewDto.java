package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreReviewDto {
    int id;
    int user_id;
    String date;
    String content;
}
