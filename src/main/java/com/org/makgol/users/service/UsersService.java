package com.org.makgol.users.service;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.common.exception.CustomException;
import com.org.makgol.common.exception.ErrorCode;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.jwt.vo.TokenVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.cookie.CookieUtil;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import com.org.makgol.util.mail.MailSendUtil;
import com.org.makgol.util.redis.RedisUtil;
import com.org.makgol.util.service.WeatherInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.org.makgol.util.CompletableFuture.fetchDataAsync;


@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
public class UsersService implements LogoutHandler {
    @Value("${domain.address}")
    private String domainAddress;

    private final JwtUtil jwtUtil;
    private final UserDao userDao;
    private final RedisUtil redisUtil;
    private final FileUpload fileUpload;
    private final WeatherInfo weatherInfo;
    private final MailSendUtil mailSendUtil;
    private final UsersRepository usersRepository;
    private final ServletContext servletContext;

    //userFindPassword
    public boolean userFindPassword(String userEmail) {
        if (usersRepository.findUserEmail(userEmail) != null) {
            int randomNumber = mailSendUtil.makeRandomNumber();
            String newPassword = BCrypt.hashpw(String.valueOf(randomNumber), BCrypt.gensalt());
            Map<String, String> map = new HashMap<>();
            map.put("newPassword", newPassword);
            map.put("userEmail", userEmail);
            usersRepository.updatePassword(map);
            mailSendUtil.sendMail(randomNumber, userEmail, false);
            return true;
        } else {
            return false;
        }
    }// userFindPassword_END


    //이메일 번호 송신
    public boolean checkEmail(String email) {
        int authNumber = mailSendUtil.makeRandomNumber();
        String key = String.valueOf(authNumber);
        if (mailSendUtil.sendMail(authNumber, email, true)) {
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
            usersRequestVo.setPhoto_path(domainAddress+"/resources/static/image/default/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        if (usersRepository.saveUser(usersRequestVo)) {

            //CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            //future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
        }

        return true;
    }// joinUser_END


    public void loginConfirm(UsersRequestVo usersRequestVo, HttpServletResponse response) {
        String email = usersRequestVo.getEmail();
        UsersResponseVo loginedUserVo = usersRepository.findUserByEmail(email);
        // 이메일과 일치하는 유저 정보가 있고, 비밀번호도 일치하면!
        if (loginedUserVo != null && BCrypt.checkpw(usersRequestVo.getPassword(), loginedUserVo.getPassword())) {
            List<String> coordinate = weatherInfo.findCoordinate(loginedUserVo.getAddress());
            String valueX = coordinate.get(0);
            String valueY = coordinate.get(1);
            String weatherAddr = coordinate.get(2);
            loginedUserVo.setValueX(Integer.parseInt(valueX));
            loginedUserVo.setValueY(Integer.parseInt(valueY));
            loginedUserVo.setWeatherAddr(weatherAddr);
            CookieUtil.saveCookies(response, loginedUserVo);
            // 1. 기존에 있던 리프레쉬 토큰을 취소 시킨다.
            jwtUtil.saveTokenUpdate(email, JwtUtil.REVOKED);
            // 2. 리프레쉬를 만들고 date 값을 가져와 access 토큰에 주입 시켜 연관관계를 형성 시킨다.
            TokenVo tokenVo = jwtUtil.createSettingToken(email);
            // 3. 악세스 토큰을 쿠키에 담아 클라이언트에게 전송 시킨다.
            jwtUtil.setTokenInCookie(response, tokenVo.getAccessToken(), "Access");


        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

    }


    private void setAccessTokenInHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, accessToken);
    }


    public void getCookieValue(HttpServletRequest request) {
        Map<String, List<String>> map = CookieUtil.getCookie(request);
        List<String> cookieNames = map.get("name");
        List<String> cookieValues = map.get("value");
        UsersResponseVo loginedUserVo = new UsersResponseVo();

        for (int i = 0; i < cookieNames.size(); i++) {
            String cookieName = cookieNames.get(i);
            String cookieValue = cookieValues.get(i);
            if (cookieName.equals("id")) {
                loginedUserVo.setId(Integer.parseInt(cookieValue));
            } else if (cookieName.equals("name")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setName(cookieValue);
            } else if (cookieName.equals("photo_path")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setPhoto_path(cookieValue);
            } else if (cookieName.equals("grade")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setGrade(cookieValue);
            } else if (cookieName.equals("userX")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setLongitude(Double.parseDouble(cookieValue));
            } else if (cookieName.equals("userY")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setLatitude(Double.parseDouble(cookieValue));
            } else if (cookieName.equals("weatherAddr")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setWeatherAddr(cookieValue);
            } else if (cookieName.equals("valueX")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setValueX(Integer.parseInt(cookieValue));
            } else if (cookieName.equals("valueY")) {
                log.info("cookieValue --> : {}", cookieValue);
                loginedUserVo.setValueY(Integer.parseInt(cookieValue));
            }
        }
        servletContext.setAttribute("loginedUserVo", loginedUserVo);
    }


    public void blackList(HttpServletRequest req, HttpServletResponse res) {
        ServletContext servletContext = req.getServletContext();
        servletContext.removeAttribute("loginedUserVo");
        CookieUtil.clearCookie(req, res);
    }


    public Boolean mailCheckDuplication(String email) {

        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));

        return !result;
    }


    public int modifyUserInfo(UsersRequestVo usersRequestVo, String oldFile, HttpServletResponse response) {
        UsersResponseVo loginedUserVo = usersRepository.userInfo(usersRequestVo.getId());
        int result = 0;
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));
        if (usersRequestVo.getPhotoFile() != null && !usersRequestVo.getPhotoFile().isEmpty()) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
            result = userDao.updateUserPhotoInfo(usersRequestVo);
            if (result > 0) {
                oldFile = "[" + oldFile + "]";
                System.out.println("예전파일은?" + oldFile);
                FileUpload.deleteFileList(oldFile);
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
            List<String> coordinate = weatherInfo.findCoordinate(newUserVo.getAddress());
            String valueX = coordinate.get(0);
            String valueY = coordinate.get(1);
            String weatherAddr = coordinate.get(2);
            newUserVo.setValueX(Integer.parseInt(valueX));
            newUserVo.setValueY(Integer.parseInt(valueY));
            newUserVo.setWeatherAddr(weatherAddr);
            boolean cookieResult = CookieUtil.saveCookies(response, newUserVo);
            if (cookieResult) {
                servletContext.setAttribute("loginedUserVo", newUserVo);

            }
            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            future.thenAccept(result_info -> {
                log.info("saveStoresInfo --> : {}", result_info);
            });
        }
        return result;
    }

    public UsersResponseVo userInfo(int user_id) {
        return usersRepository.userInfo(user_id);
    }

    public List<StoreResponseVo> myStoreList(int user_id) {
        return userDao.selectMyStoreList(user_id);
    }


    public List<BoardVo> getMyPostList(int user_id) {
        return userDao.selectMyPostList(user_id);
    }

    public List<CommentResponseVo> getMyCommentList(int user_id) {
        return userDao.selectMyCommentList(user_id);
    }

    public List<BoardLikeVo> getMyLikePost(int user_id) {
        return userDao.selectMyLikePostList(user_id);
    }

    public int countingPosts(int user_id) {
        return usersRepository.countingPosts(user_id);
    }

    public int countingComments(int user_id) {
        return usersRepository.countingComments(user_id);
    }

    public int countingLikes(int user_id) {
        return usersRepository.countingLikes(user_id);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        blackList(request, response);

        try {
            response.sendRedirect("index");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}