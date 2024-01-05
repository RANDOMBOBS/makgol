package com.org.makgol.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class healthController {

    /**
     * CI/CD Nginx 애플리케이션 정상 작동 확인 컨트롤러
     *
     * @return 정상 반환코드
     */
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        // Your health check logic goes here
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
