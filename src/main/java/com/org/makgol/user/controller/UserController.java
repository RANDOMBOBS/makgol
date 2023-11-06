package com.org.makgol.user.controller;

import com.org.makgol.user.exception.EmailExistException;
import com.org.makgol.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResponseEntity<String> successResponse;

    @ResponseBody
    @GetMapping("/check-email")
    public ResponseEntity<String> checkExistEmail(@RequestParam(value = "email") String email) throws EmailExistException {
        userService.checkExistEmail(email);

        return successResponse;
    }
}
