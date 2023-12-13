package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ToString
public class CreateReviewDto {
    int userId;
    int storeId;
    String review;
    List<MultipartFile> reviewImages;

    int id; // 리뷰를 생성하고 받는 리뷰 아이디
}
