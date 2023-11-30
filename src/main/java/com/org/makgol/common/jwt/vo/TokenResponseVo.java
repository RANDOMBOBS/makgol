package com.org.makgol.common.jwt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseVo {
    private int     id;
    private String  token;
    private String  email;
    private boolean expired;
    private boolean revoked;
    private Date    data;

    public TokenResponseVo update(String token) {
        this.token = token;
        return this;
    }
}
