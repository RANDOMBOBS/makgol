package com.org.makgol.common.jwt.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenVo {

    private String accessToken;
    private String refreshToken;

    public TokenVo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}