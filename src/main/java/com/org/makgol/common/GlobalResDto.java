package com.org.makgol.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlobalResDto {

    private String msg;
    private int statusCode;


    public GlobalResDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

}