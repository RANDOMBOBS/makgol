package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ToString
public class ModifyReviewDto {
    int reviewId;
    String review;
    List<MultipartFile> reviewImages;
}
