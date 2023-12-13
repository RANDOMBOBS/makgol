package com.org.makgol.common.oauth2.ui;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping(value = "token")
    public String token(@RequestParam String token, @RequestParam String error) {
        if (StringUtils.isNotBlank(error)) {
            return error;
        } else {
            return token;
        }
    }

}
