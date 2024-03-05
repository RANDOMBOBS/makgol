package com.org.makgol.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@AllArgsConstructor
public class UploadReviewImageDto implements Serializable {
    private static final long serialVersionUID = 1L;
    int reviewId;
    String photoName;
    String photoPath;
}
