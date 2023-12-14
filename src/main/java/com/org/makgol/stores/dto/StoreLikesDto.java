package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreLikesDto {
    private int id;
    private int store_id;
    private int user_id;
}
