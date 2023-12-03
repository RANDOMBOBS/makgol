package com.org.makgol.users.service;

import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.common.exception.CustomException;
import com.org.makgol.common.exception.ErrorCode;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.jwt.vo.TokenResponseVo;
import com.org.makgol.common.jwt.vo.TokenVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import com.org.makgol.util.mail.MailSendUtil;
import com.org.makgol.util.redis.RedisUtil;
import com.org.makgol.util.service.WeatherInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.org.makgol.util.CompletableFuture.fetchDataAsync;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService implements LogoutHandler {
    @Value("${domain.name}")
    private String domainName;
    private final JwtUtil         jwtUtil;
    private final UserDao         userDao;
    private final RedisUtil       redisUtil;
    private final FileUpload      fileUpload;
    private final WeatherInfo     weatherInfo;
    private final MailSendUtil    mailSendUtil;
    private final UsersRepository usersRepository;



    //userFindPassword
    public String userFindPassword(String userEmail) {

        if (usersRepository.findUserEmail(userEmail)) {

            int randomNumber = mailSendUtil.makeRandomNumber();
            String newPassword = String.valueOf(randomNumber);
            Map<String, String> map = new HashMap<>();
            map.put("newPassword", newPassword);
            map.put("userEmail", userEmail);
            if (usersRepository.updatePassword(map)) {
                mailSendUtil.sendMail(randomNumber, userEmail);
            }
            return newPassword;

        } else {
            return "회원가입된 이메일이 아닙니다.";
        }

    }// userFindPassword_END


    //이메일 번호 송신
    public boolean checkEmail(String email) {
        int authNumber = mailSendUtil.makeRandomNumber();
        String key = String.valueOf(authNumber);
        if (mailSendUtil.sendMail(authNumber, email)) {
            return redisUtil.setDataExpire(key, email, 60 * 3L);
        }
        return false;

    } //checkEmail_END

    public boolean checkNumber(int auth_number, String email) {
        String key = String.valueOf(auth_number);
        Boolean result = (email.equals(redisUtil.getData(key)));
        //result = userDao.checkNumber(auth_number);

        return result;
    }//checkEmail_END


    //joinUser
    public Boolean joinUser(UsersRequestVo usersRequestVo) {
        //1.사용자 회원가입 기능
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if (usersRequestVo.getPhotoFile() != null) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            usersRequestVo.setPhoto_path("/resources/static/image/default/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        if (usersRepository.saveUser(usersRequestVo)) {

//            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
//            // 비동기 작업이 완료되면 결과를 출력
//            future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
        }

        return true;
    }// joinUser_END




    public UsersResponseVo loginConfirm(UsersRequestVo usersRequestVo, HttpServletResponse response) {
        String email = usersRequestVo.getEmail();
        UsersResponseVo loginedUserVo = usersRepository.findUserByEmail(email);

        // 만약 로그인을 못했다면?
        if(loginedUserVo == null){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        } else{
            List<Integer> coordinate = weatherInfo.findCoordinate(loginedUserVo.getAddress());
            loginedUserVo.setCoordinate(coordinate);
        }

        if (!BCrypt.checkpw(usersRequestVo.getPassword(), loginedUserVo.getPassword())) {
            loginedUserVo = null;

        } else {
            // 아이디 정보로 Token생성
            TokenVo tokenVo = jwtUtil.createAllToken(email);
            // Refresh토큰 있는지 확인
            Optional<TokenResponseVo> refreshToken = jwtUtil.findTokenByEmail(email);

            Map<String, Object> map = new HashMap<>();
            map.put("token", tokenVo.getRefreshToken());
            map.put("email", email);
            map.put("expired", false);
            map.put("revoked", false);

            // 있다면 새토큰 발급후 업데이트
            if(refreshToken.isPresent()) {
                jwtUtil.saveTokenUpdate(email, JwtUtil.REVOKED);
                jwtUtil.saveToken(map);

                // 없다면 새로 만들고 디비 저장
            } else { jwtUtil.saveToken(map); }
            //access token in header, refresh token in cookie
            setAccessTokenInHeader(response, tokenVo.getAccessToken());
            setRefreshTokenInCookie(response, tokenVo.getRefreshToken());
        }

        return loginedUserVo;
    }

    private void setAccessTokenInHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, accessToken);
    }

    private void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from(JwtUtil.REFRESH_TOKEN, refreshToken)
                .domain(domainName)
                .path("/")
                .sameSite("Lax")            //sameSite 모르면 검색!! 중요함!!! 돼지꼬리 떙떙!!
                .httpOnly(true)
                .secure(false)
                //.maxAge(7 * 24 * 60 * 60)
                .build();
          response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

//        Cookie cookie = new Cookie(JwtUtil.REFRESH_TOKEN, refreshToken);
//        cookie.setDomain(domainName);       // 여기서는 localhost로 설정되어 해당 도메인에서만 쿠키가 유효합니다.
//        cookie.setPath("/");                // "/"로 설정되어 해당 도메인 전체에서 쿠키가 유효합니다.
//        cookie.setMaxAge(7 * 24 * 60 * 60); // 1주일간 유지
//        cookie.setHttpOnly(true);           //javascript로 접근이 불가능하게 함
//        cookie.setSecure(false);           //https 일 경우에만 쿠키 전송
//          response.addCookie(cookie);
    }

    public Boolean mailCheckDuplication(String email) {

        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));

        return !result;
    }

    public int modifyUserInfo(UsersRequestVo usersRequestVo, String oldFile, HttpSession session) {
        System.out.println("바뀐회원정보는??"+usersRequestVo);
        UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
        System.out.println("세션 유저 정보?"+loginedUserVo);

        String oldFileName = oldFile.substring(oldFile.lastIndexOf("/") + 1);
        String currentDirectory = System.getProperty("user.dir");
        int result =0;
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if (usersRequestVo.getPhotoFile() != null && !usersRequestVo.getPhotoFile().isEmpty()) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
            result = userDao.updateUserPhotoInfo(usersRequestVo);
            if(result > 0){
                String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + oldFileName;
                File oldfile = new File(deleteFile);
                oldfile.delete();
            }
        } else {
            result = userDao.updateUserInfo(usersRequestVo);
            usersRequestVo.setPhoto_path(loginedUserVo.getPhoto_path());
            usersRequestVo.setPhoto(loginedUserVo.getPhoto());
        }

        if (result > 0) {
            usersRequestVo.setName(loginedUserVo.getName());
            usersRequestVo.setEmail(loginedUserVo.getEmail());
            UsersResponseVo newUserVo = new UsersResponseVo();
            newUserVo.modifyMapper(usersRequestVo);
            System.out.println("새로 저장할 유저 정보?"+newUserVo);
            List<Integer> coordinate = weatherInfo.findCoordinate(newUserVo.getAddress());
            newUserVo.setCoordinate(coordinate);
            session.setAttribute("loginedUserVo", newUserVo);

            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
        }
        return result;
    }

    public List<StoreResponseVo> myStoreList(int user_id){
        return userDao.selectMyStoreList(user_id);
    }


    public List<BoardVo> getMyPostList(int user_id){
        return userDao.selectMyPostList(user_id);
    }

    public List<CommentResponseVo> getMyCommentList(int user_id){
        return userDao.selectMyCommentList(user_id);
    }

    public List<BoardVo> getMyLikePost(int user_id){
        return userDao.selectMyLikePostList(user_id);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }
}



