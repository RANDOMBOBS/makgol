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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 사용자 컨트롤러 클래스입니다. 사용자와 관련된 기능을 처리합니다.
 */
@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService userService;
    private final ServletContext servletContext;

    /**
     * 사용자 회원가입 처리를 위한 POST 메서드입니다.
     *
     * @param usersRequestVo 사용자 요청 정보를 담은 객체
     * @return 회원가입 결과를 나타내는 ResponseEntity
     */
    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@ModelAttribute @Valid UsersRequestVo usersRequestVo) {
        Boolean result = userService.joinUser(usersRequestVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }// joinUser_END

    /**
     * 사용자 회원가입 페이지를 반환합니다.
     *
     * @return 사용자 회원가입 페이지 경로
     */
    @GetMapping("/join")
    public String userJoinPage() {
        String nextPage = "jsp/user/user_join";
        return nextPage;
    } // userJoinPage_END

    /**
     * 사용자 비밀번호 찾기 처리를 위한 POST 메서드입니다.
     *
     * @param email 사용자 이메일 주소
     * @return 비밀번호 찾기 결과를 나타내는 ResponseEntity
     */
    @PostMapping("/findPassword")
    public ResponseEntity<?> userFindPassword(@RequestParam("email") String email) {
        boolean result = userService.userFindPassword(email);
        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    } // userFindPassword_END

    /**
     * 사용자 이메일 인증번호 확인을 위한 POST 메서드입니다.
     *
     * @param authNumberVo 인증번호 및 이메일 정보를 담은 객체
     * @return 인증번호 확인 결과를 나타내는 ResponseEntity
     */
    @PostMapping("/mailCheck")
    @ResponseBody
    public ResponseEntity<?> mailCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
        boolean result = userService.checkEmail(authNumberVo.getEmail());
        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } // mailCheck_END

    /**
     * 사용자 이메일 중복 확인을 위한 POST 메서드입니다.
     *
     * @param email 사용자 이메일 주소
     * @return 이메일 중복 확인 결과를 나타내는 ResponseEntity
     */
    @PostMapping("/mailCheckDuplication")
    @ResponseBody
    public ResponseEntity<?> mailCheckDuplication(@RequestParam("email") String email) {
        boolean result = userService.mailCheckDuplication(email);
        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } // mailCheckDuplication_END

    /**
     * 사용자 인증번호 확인을 위한 POST 메서드입니다.
     *
     * @param authNumberVo 인증번호 및 이메일 정보를 담은 객체
     * @return 인증번호 확인 결과를 나타내는 ResponseEntity
     */
    @PostMapping("/authNumberCheck")
    public ResponseEntity<?> authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
        boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());
        return result ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    } //authNumberCheck_END

    /**
     * 로그인 화면을 반환합니다.
     *
     * @return 홈 화면 경로
     */
    @GetMapping("/index")
    public String loginForm() {
        return "home";
    }

    /**
     * 로그인 확인을 처리하는 POST 메서드입니다.
     *
     * @param request         HttpServletRequest 객체
     * @param usersRequestVo  사용자 요청 정보를 담은 객체
     * @param response        HttpServletResponse 객체
     * @return 새로운 경로로 리다이렉트합니다.
     * @throws URISyntaxException URI 문제가 발생할 경우 예외를 던집니다.
     */
    @PostMapping("/loginConfirm")
    public String loginConfirm(HttpServletRequest request, UsersRequestVo usersRequestVo, HttpServletResponse response) throws URISyntaxException {
        String urlString = request.getHeader("Referer");
        log.info("urlString --> : {}", urlString);
        URI uri = new URI(urlString);
        String path = uri.getPath() + "?" + uri.getQuery();
        // 경로 부분을 얻어옴
        String newPath = path.replaceAll("amp;", "");
        log.info("path --> : {}", newPath);
        userService.loginConfirm(usersRequestVo, response);
        return "redirect:" + newPath;
    }

//    @GetMapping("/loginSucceed")
//    public UsersResponseVo getCookieValue(HttpServletRequest request, Model model) {
//        System.out.println("로그인성공!");
//        return userService.getCookieValue(request);
//    }


    /**
     * 사용자 블랙리스트 페이지를 처리하는 메서드입니다.
     *
     * @param req HttpServletRequest 객체
     * @param res HttpServletResponse 객체
     * @return 홈 화면 경로
     */
    @GetMapping("/blackList")
    public String blackList(HttpServletRequest req, HttpServletResponse res) {
        userService.blackList(req, res);
        return "home";
    }

    /**
     * 사용자 개인 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자 개인 페이지 경로
     */
    @GetMapping("/myPage")
    public String myPage(@RequestParam("user_id") int user_id, Model model) {
        UsersResponseVo userInfo = userService.userInfo(user_id);
        model.addAttribute("userInfo", userInfo);
        return "jsp/user/my_page";
    }

    /**
     * 사용자 정보 수정 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자 정보 수정 페이지 경로
     */
    @GetMapping("/modifyUser")
    public String modify_user(@RequestParam("user_id") int user_id, Model model) {
        UsersResponseVo userInfo = userService.userInfo(user_id);
        model.addAttribute("userInfo", userInfo);
        return "jsp/user/modify_user";
    }

    /**
     * 사용자 정보 수정을 확인하는 POST 메서드입니다.
     *
     * @param usersRequestVo 사용자 요청 정보를 담은 객체
     * @param oldFile        이전 파일 이름
     * @param response       HttpServletResponse 객체
     * @param model          뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자 개인 페이지로 리다이렉트하거나 정보 수정 페이지로 이동
     */
    @PostMapping("/modifyUserConfirm")
    public String modifyUserConfirm(@ModelAttribute UsersRequestVo usersRequestVo, @RequestParam("oldFile") String oldFile, HttpServletResponse response, Model model) {
        int result = userService.modifyUserInfo(usersRequestVo, oldFile, response);
        if (result > 0) {
            UsersResponseVo userInfo = userService.userInfo(usersRequestVo.getId());
            model.addAttribute("userInfo", userInfo);
            return "jsp/user/my_page";
        }
        return "jsp/user/modify_user";
    }

    /**
     * 사용자 소유 가게 목록 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자 소유 가게 목록 페이지 경로
     */
    @GetMapping("/myStoreList")
    public String myStoreList(@RequestParam("user_id") int user_id, Model model) {
        List<StoreResponseVo> storeVos = userService.myStoreList(user_id);
        model.addAttribute("storeVos", storeVos);
        return "jsp/user/my_store_list";
    }

    /**
     * 사용자 활동 내역 페이지를 반환합니다.
     *
     * @param show  페이지에 표시할 내역 종류
     * @param model 뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자 활동 내역 페이지 경로
     */
    @GetMapping("/myHistory")
    public String myHistory(@RequestParam("show") String show, Model model) {
        model.addAttribute("show", show);
        return "jsp/user/my_history";
    }

    /**
     * 사용자가 작성한 게시물 목록 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자가 작성한 게시물 목록 페이지 경로
     */
    @RequestMapping(value = "/myPostList/{user_id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String myPostList(@PathVariable("user_id") int user_id, Model model) {
        String nextPage = "jsp/user/my_post_list";
        List<BoardVo> boardVos = userService.getMyPostList(user_id);
        model.addAttribute("boardVos", boardVos);
        return nextPage;
    }

    /**
     * 사용자가 작성한 댓글 목록 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자가 작성한 댓글 목록 페이지 경로
     */
    @RequestMapping(value = "/myCommentList/{user_id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String myCommentList(@PathVariable("user_id") int user_id, Model model) {
        String nextPage = "jsp/user/my_comment_list";
        List<CommentResponseVo> commentVos = userService.getMyCommentList(user_id);
        model.addAttribute("commentVos", commentVos);
        return nextPage;
    }

    /**
     * 사용자가 좋아요한 게시물 목록 페이지를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @param model   뷰에 전달될 데이터를 담은 Model 객체
     * @return 사용자가 좋아요한 게시물 목록 페이지 경로
     */
    @RequestMapping(value = "/myLikePost/{user_id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String myLikePost(@PathVariable("user_id") int user_id, Model model) {
        String nextPage = "jsp/user/my_like_post_list";
        List<BoardLikeVo> boardVos = userService.getMyLikePost(user_id);
        model.addAttribute("boardVos", boardVos);
        return nextPage;
    }

    /**
     * 사용자가 작성한 게시물 수를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @return 사용자가 작성한 게시물 수
     */
    @ResponseBody
    @GetMapping("/countingPosts/{user_id}")
    public int countingPosts(@PathVariable("user_id") int user_id) {
        return userService.countingPosts(user_id);
    }

    /**
     * 사용자가 작성한 댓글 수를 반환합니다.
     *
     * @param user_id 사용자 식별자
     * @return 사용자가 작성한 댓글 수
     */
    @ResponseBody
    @GetMapping("/countingComments/{user_id}")
    public int countingComments(@PathVariable("user_id") int user_id) {
        return userService.countingComments(user_id);
    }
}
