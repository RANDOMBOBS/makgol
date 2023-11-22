package com.org.makgol.stores.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Data
@Builder
@Getter
public class RequestStoreListDto {
    private String longitude;
    private String latitude;
    private String keyword;
    private String page;
}
