package com.org.makgol.common.jwt.vo;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenResponseVo {
    private int     id;
    private String  token;
    private String  email;
    private boolean expired;
    private boolean revoked;
    private String    date;

    public TokenResponseVo update(String token) {
        this.token = token;
        return this;
    }
}
