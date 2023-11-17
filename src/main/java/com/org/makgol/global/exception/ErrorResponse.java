package com.org.makgol.global.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Getter
@Builder
public class ErrorResponse {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @CreatedDate
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String error;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .error(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> constraintMsgToResponseEntity(ErrorCode errorCode, String msg) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .error(errorCode.name())
                        .message(msg)
                        .build()
                );
    }
}