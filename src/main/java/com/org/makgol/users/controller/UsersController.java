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
        System.out.println("joinUser");
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



}
