package com.org.makgol.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ex) FAILED_MESSAGE(500, "E-FAI500","서버에서 오류가 발생하였습니다."),
    FAILED_MESSAGE(500, "E-FAI500","서버에서 오류가 발생하였습니다."),
    WRONG_INPUT(400, "E_WRI400", "입력 값을 확인해주세요."),
    NOT_FOUND_USER(400, "E-NFU400", "유저가 존재하지 않습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}