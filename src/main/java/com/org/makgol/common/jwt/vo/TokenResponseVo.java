package com.org.makgol.common.jwt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseVo {
    private int id;
    private String token;
    private String email;
    private boolean expired;
    private boolean revoked;
}
