package com.org.makgol.stores.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class RequestStoreListDto {
    private String longitude;
    private String latitude;
    private String keyword;
}
