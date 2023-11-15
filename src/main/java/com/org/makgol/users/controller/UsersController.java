package com.org.makgol.users.controller;

import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.service.UsersService;
import com.org.makgol.users.vo.AuthNumberVo;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService userService;
    private final FileUpload fileUpload;
    //joinUser_POST
    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@ModelAttribute @Valid UsersRequestVo usersRequestVo) {
        Boolean result = userService.joinUser(usersRequestVo);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }// joinUser_END

    @GetMapping("/join")
    public String userJoinPage() {
        String nextPage = "jsp/user/user_join";
        return nextPage;
    } // userJoinPage_END


    @PostMapping("/findPassword")
    public void userFindPassword() {
        String userEmail = "tjsgus223@naver.com";
        userService.userFindPassword(userEmail);

    } // userFindPassword_END

    @PostMapping("/mailCheck")
    @ResponseBody
    public ResponseEntity<?> mailCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {

        Boolean result = userService.checkEmail(authNumberVo.getEmail());

        //인증번호 송신 성공
        return new ResponseEntity<>(result, HttpStatus.OK);
    } // mailCheck_END

    @PostMapping("/mailCheckDuplication")
    @ResponseBody
    public ResponseEntity<?> mailCheckDuplication(@RequestParam("email") String email) {
        Boolean result = userService.mailCheckDuplication(email);

        return new ResponseEntity<>(result, HttpStatus.OK);

    } // mailCheckDuplication_END

    @PostMapping("/authNumberCheck")
    public ResponseEntity<?>  authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
        //int number = Integer.parseInt(auth_number);
        boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());

        //인증 성공
        return new ResponseEntity<>(result, HttpStatus.OK);

    } //authNumberCheck_END



    @GetMapping("/login")
    public String loginForm() {
        return "jsp/user/user_login";
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UsersRequestVo usersRequestVo, HttpSession session) {
        String nextPage = "home";

        UsersRequestVo loginedUsersRequestVo = userService.loginConfirm(usersRequestVo);
        if (loginedUsersRequestVo == null) {
            nextPage = "jsp/user/user_login_ng";
        } else {
            if (!(loginedUsersRequestVo.getGrade() != null && "블랙리스트".equals(loginedUsersRequestVo.getGrade()))) {
                session.setAttribute("loginedUsersRequestVo", loginedUsersRequestVo);
            } else{
                session.setAttribute("blackList", loginedUsersRequestVo);
            }
        }
        return nextPage;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, @RequestParam("link") String link){
        System.out.println("링크는???"+link);
        session.removeAttribute("blackList");
        session.invalidate();

        if(link.contains("/admin/userManagement")||link.contains("/suggestion/create")||link.contains("/suggestion/modify")||link.contains("/user/modifyUser")||link.contains("/user/myHistory")||link.contains("/user/myPage")||link.contains("/user/myStoreList")){
            return "home";
        }else {
            return "redirect:" + link;
        }
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "jsp/user/my_page";
    }

    @GetMapping("/modifyUser")
    public String modify_user(){
        return "jsp/user/modify_user";
    }

    @PostMapping("/modifyUserConfirm")
    public String modifyUserConfirm(@ModelAttribute UsersRequestVo usersRequestVo, @RequestParam("oldFile") String oldFile, HttpSession session) {
        int result = userService.modifyUserInfo(usersRequestVo, oldFile, session);
        if (result > 0) {
            return "jsp/user/my_page";
        }
        return "jsp/user/modify_user";
    }

    @GetMapping("/myStoreList")
    public String myStoreList(@RequestParam("user_id") int user_id, Model model){
        List<StoreResponseVo> storeVos = userService.myStoreList(user_id);
        model.addAttribute("storeVos", storeVos);
        return "jsp/user/my_store_list";
    }

}
