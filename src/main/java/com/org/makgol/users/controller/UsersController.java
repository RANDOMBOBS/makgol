package com.org.makgol.users.controller;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.service.UsersService;
import com.org.makgol.users.vo.AuthNumberVo;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService userService;

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

        boolean result = userService.checkEmail(authNumberVo.getEmail());

        //인증번호 송신 성공
        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } // mailCheck_END

    @PostMapping("/mailCheckDuplication")
    @ResponseBody
    public ResponseEntity<?> mailCheckDuplication(@RequestParam("email") String email) {
        boolean result = userService.mailCheckDuplication(email);

        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } // mailCheckDuplication_END

    @PostMapping("/authNumberCheck")
    public ResponseEntity<?> authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
        //int number = Integer.parseInt(auth_number);
        boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());

        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } //authNumberCheck_END


    @GetMapping("/login")
    public String loginForm() {
        // 로그인 화면 템플릿 경로를 설정
        return "jsp/user/user_login";
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UsersRequestVo usersRequestVo, HttpSession session) {
        String nextPage = "home";
        UsersResponseVo loginedUserVo = userService.loginConfirm(usersRequestVo);
        System.out.println("로그인한 유저 정보는?"+loginedUserVo);

        if (loginedUserVo == null) {
            // 로그인 실패 시 'login_ng' 화면을 표시
            nextPage = "jsp/user/user_login_ng";
        } else {
            if (!(loginedUserVo.getGrade() != null && "블랙리스트".equals(loginedUserVo.getGrade()))) {
                // 로그인 성공 시 사용자 정보를 세션에 저장하고 세션
                session.setAttribute("loginedUserVo", loginedUserVo);
            } else {
                session.setAttribute("blackList", loginedUserVo);
            }
        }
        return nextPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, @RequestParam("link") String link){
        session.invalidate();
        if(link.contains("/admin/userManagement")||link.contains("/suggestion/create")||link.contains("/suggestion/modify")||link.contains("/user/modifyUser")||link.contains("/user/myHistory")||link.contains("/user/myPage")||link.contains("/user/myStoreList")||link.contains("/user/loginConfirm")){
            return "home";
        }else {
            return "redirect:" + link;
        }
    }

    @GetMapping("/blackList")
    public String blackList(HttpSession session){
        session.removeAttribute("blackList");
        session.invalidate();
        return "home";
    }


    @GetMapping("/myPage")
    public String myPage() {
        return "jsp/user/my_page";
    }

    @GetMapping("/modifyUser")
    public String modify_user() {
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

    @GetMapping("/myHistory")
    public String myHistory(@RequestParam("show") String show, Model model){
        model.addAttribute("show", show);
        return "jsp/user/my_history";
    }
    @RequestMapping(value = "/myPostList/{user_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public String myPostList(@PathVariable("user_id") int user_id, Model model){
        String nextPage = "jsp/user/my_post_list";
        List<BoardVo> boardVos = userService.getMyPostList(user_id);
        model.addAttribute("boardVos", boardVos);
        return nextPage;
    }

    @RequestMapping(value = "/myCommentList/{user_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public String myCommentList(@PathVariable("user_id") int user_id, Model model){
        String nextPage = "jsp/user/my_comment_list";
        List<CommentResponseVo> commentVos = userService.getMyCommentList(user_id);
        model.addAttribute("commentVos", commentVos);
        return nextPage;
    }

    @RequestMapping(value = "/myLikePost/{user_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public String myLikePost(@PathVariable("user_id") int user_id, Model model){
        String nextPage = "jsp/user/my_like_post_list";
        List<BoardVo> boardVos = userService.getMyLikePost(user_id);
        model.addAttribute("boardVos", boardVos);
        return nextPage;
    }

}


