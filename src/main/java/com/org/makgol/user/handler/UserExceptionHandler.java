package com.org.makgol.user.handler;

import com.org.makgol.user.exception.EmailExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({EmailExistException.class})
    protected ResponseEntity<String> handleEmailExistException() {
        return new ResponseEntity<>("이메일이 중복되었습니다", HttpStatus.BAD_REQUEST);
    }

}
