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


    /**
     * 성공 여부를 설정하는 메서드입니다.
     *
     * @param success 성공 여부
     * @return 현재의 KakaoLocalResponseBuilder 객체
     */
    public KakaoLocalResponseBuilder<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 응답 메시지를 설정하는 메서드입니다.
     *
     * @param message 응답 메시지
     * @return 현재의 KakaoLocalResponseBuilder 객체
     */
    public KakaoLocalResponseBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 응답 결과를 설정하는 메서드입니다.
     *
     * @param result 응답 결과
     * @return 현재의 KakaoLocalResponseBuilder 객체
     */
    public KakaoLocalResponseBuilder<T> setResult(T result) {
        this.result = result;
        return this;
    }

    /**
     * KakaoLocalResponseVo 객체를 생성하여 반환하는 메서드입니다.
     *
     * @return KakaoLocalResponseVo 객체
     */
    public KakaoLocalResponseVo<T> build() {
        return new KakaoLocalResponseVo<T>(success, message, result);
    }
}
