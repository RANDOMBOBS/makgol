package com.org.makgol.stores.bean;

import com.org.makgol.stores.vo.KakaoLocalResponseVo;
import org.springframework.stereotype.Component;


import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class KakaoLocalResponseBuilder<T> {

	private boolean success;
    private String message;
    private T result;


    public KakaoLocalResponseBuilder<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public KakaoLocalResponseBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public KakaoLocalResponseBuilder<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public KakaoLocalResponseVo<T> build() {
        return new KakaoLocalResponseVo<T>(success, message, result);
    }
	
}
