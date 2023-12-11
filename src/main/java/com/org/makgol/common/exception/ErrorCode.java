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
    INVALID_ACCESS_TOKEN(401, "E-IAT401","유효하지 않은 Access Token 입니다."),
    WRONG_TYPE_TOKEN(401, "E-WTT401","잘못된 타입의 JWT 토큰입니다."),
    WRONG_TYPE_SIGNATURE(401, "E-WTS401", "잘못된 JWT 서명입니다."),
    ACCESS_DENIED(401, "E-ACD401","접근이 거부되었습니다."),
    EXPIRED_ACCESS_TOKEN(402, "E-EAT402", "만료된 Access Token 입니다."),
    ACCESS_TOKEN_NOT_EXIST(401, "E-RTN402", "Access Token이 존재하지 않습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}