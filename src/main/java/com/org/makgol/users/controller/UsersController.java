package com.org.makgol.users.controller;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.service.UsersService;
import com.org.makgol.users.vo.AuthNumberVo;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService userService;
    private final ServletContext servletContext;

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
        return "jsp/user/user_login";
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(UsersRequestVo usersRequestVo, HttpServletResponse response, HttpSession session){
       userService.loginConfirm(usersRequestVo, response);
        return "redirect:/user/loginSucceed";
    }

    @GetMapping("/loginSucceed")
    public String getCookieValue(HttpServletRequest request){
       userService.getCookieValue(request);
       return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res,@RequestParam("link") String link) {
        userService.blackList(req, res);
        return "home";
    }

    @GetMapping("/blackList")
    public String blackList(HttpServletRequest req, HttpServletResponse res){
        userService.blackList(req, res);
        return "home";
    }


    @GetMapping("/myPage")
    public String myPage(@RequestParam("user_id") int user_id, Model model) {
       UsersResponseVo userInfo = userService.userInfo(user_id);
       model.addAttribute("userInfo", userInfo);
       return "jsp/user/my_page";
    }

    @GetMapping("/modifyUser")
    public String modify_user(@RequestParam("user_id") int user_id, Model model) {
        UsersResponseVo userInfo = userService.userInfo(user_id);
        model.addAttribute("userInfo", userInfo);
        return "jsp/user/modify_user";
    }

    @PostMapping("/modifyUserConfirm")
    public String modifyUserConfirm(@ModelAttribute UsersRequestVo usersRequestVo, @RequestParam("oldFile") String oldFile,HttpServletResponse response, Model model) {
        System.out.println("usersRequestVo = " + usersRequestVo);
        int result = userService.modifyUserInfo(usersRequestVo, oldFile, response);
        if (result > 0) {
            UsersResponseVo userInfo = userService.userInfo(usersRequestVo.getId());
            model.addAttribute("userInfo", userInfo);
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
        List<BoardLikeVo> boardVos = userService.getMyLikePost(user_id);
        model.addAttribute("boardVos", boardVos);
        return nextPage;
    }


    @ResponseBody
    @GetMapping("/countingPosts/{user_id}")
    public int countingPosts(@PathVariable("user_id") int user_id){
       return userService.countingPosts(user_id);
    }

    @ResponseBody
    @GetMapping("/countingComments/{user_id}")
    public int countingComments(@PathVariable("user_id") int user_id){
        return userService.countingComments(user_id);
    }

    @ResponseBody
    @GetMapping("/countingLikes/{user_id}")
    public int countingLikes(@PathVariable("user_id") int user_id){
        int a = userService.countingLikes(user_id);
        return a;
    }
}


