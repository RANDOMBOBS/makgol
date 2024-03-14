package com.org.makgol.common.jwt.vo;

import lombok.*;
import org.apache.ibatis.annotations.CacheNamespace;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CacheNamespace(flushInterval = 1000000, size = 1024)
public class TokenResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;
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
