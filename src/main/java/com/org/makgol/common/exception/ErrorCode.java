package com.org.makgol.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ex) FAILED_MESSAGE(500, "E-FAI500","서버에서 오류가 발생하였습니다."),
    FAILED_MESSAGE(500, "E-FAI500","서버에서 오류가 발생하였습니다."),
    WRONG_INPUT(400, "E_WRI400", "입력 값을 확인해주세요."),
    NOT_FOUND_USER(400, "E-NFU400", "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요."),
    ;

    private final int status;
    private final String code;
    private final String message;
}