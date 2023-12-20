package com.org.makgol.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class healthController {

    @GetMapping("/health")
    public HttpStatus checkHealth() {
        log.info("public String checkHealth() {");
        return HttpStatus.OK;
    }
}
