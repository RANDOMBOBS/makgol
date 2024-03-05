package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class ModifyReviewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    int reviewId;
    String review;
    List<MultipartFile> reviewImages;
}
