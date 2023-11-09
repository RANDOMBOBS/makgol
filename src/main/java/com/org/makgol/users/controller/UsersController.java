package com.org.makgol.users.controller;

import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.users.service.UsersService;
import com.org.makgol.users.vo.AuthNumberVo;
import com.org.makgol.users.vo.UsersRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService userService;

    @PostMapping("/findPassword")
    public void userFindPassword() {
        String userEmail = "tjsgus223@naver.com";
        userService.userFindPassword(userEmail);

    } // userFindPassword_END


    //joinUser
    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@RequestBody @Valid UsersRequestVo usersRequestVo) {
        Boolean result = userService.joinUser(usersRequestVo);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }// ResponseEntity_END



    @PostMapping("/mailCheck")
    @ResponseBody
    public ResponseEntity<?> mailCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {


        Boolean result = userService.checkEmail(authNumberVo.getEmail());

        //인증번호 송신 성공
        if(result) {
            return new ResponseEntity<>("true", HttpStatus.OK);

            //실패
        } else {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    } // mailCheck_END

    @PostMapping("/authNumberCheck")
    public ResponseEntity<?>  authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
        //int number = Integer.parseInt(auth_number);
        boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());

        System.out.println(result);
        //인증 성공
        if(result) {
            return new ResponseEntity<>("true", HttpStatus.OK);

            //실패
        } else {
            return new ResponseEntity<>("false", HttpStatus.OK);

        }
    } //authNumberCheck_END


    @GetMapping("/join")
    public String userJoinPage() {

        String nextPage = "jsp/user/user_join";
        return nextPage;
    } // userJoinPage_END

    @GetMapping("/login")
    public String loginForm() {
        // 로그인 화면 템플릿 경로를 설정
        return "jsp/user/user_login";
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UsersRequestVo usersRequestVo, HttpSession session) {
        // 기본적으로 로그인 성공 시 'login_ok' 화면을 표시
        String nextPage = "home";

        // 사용자 로그인 정보를 서비스를 통해 확인
        UsersRequestVo loginedUsersRequestVo = userService.loginConfirm(usersRequestVo);

        if (loginedUsersRequestVo == null) {
            // 로그인 실패 시 'login_ng' 화면을 표시
            nextPage = "jsp/user/user_login_ng";
        } else {
            if (!(loginedUsersRequestVo.getGrade() != null && "블랙리스트".equals(loginedUsersRequestVo.getGrade()))) {
                // 로그인 성공 시 사용자 정보를 세션에 저장하고 세션
                session.setAttribute("loginedUsersRequestVo", loginedUsersRequestVo);
            } else{
                session.setAttribute("blackList", loginedUsersRequestVo);
            }
        }
        return nextPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("blackList");
        session.invalidate();

        return "home";
    }


}
