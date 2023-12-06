package com.org.makgol.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class UploadReviewImageDto {
    int reviewId;
    String photoName;
    String photoPath;
}
