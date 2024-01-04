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

    /**
     * 주어진 사용자 이메일로 비밀번호를 찾아 임시 비밀번호를 생성하고 이메일로 전송합니다.
     *
     * @param userEmail 찾고자 하는 사용자의 이메일
     * @return 비밀번호 찾기 성공 여부 (true: 성공, false: 실패)
     */
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
    }

    /**
     * 주어진 이메일로 인증 번호를 생성하여 이메일로 전송하고, 인증 번호를 일정 시간 동안 유지합니다.
     *
     * @param email 이메일 주소
     * @return 이메일 전송 성공 여부 (true: 성공, false: 실패)
     */
    public boolean checkEmail(String email) {
        int authNumber = mailSendUtil.makeRandomNumber();
        String key = String.valueOf(authNumber);
        if (mailSendUtil.sendMail(authNumber, email, true)) {
            return redisUtil.setDataExpire(key, email, 60 * 3L);
        }
        return false;
    }

    /**
     * 주어진 인증 번호와 이메일이 일치하는지 확인합니다.
     *
     * @param auth_number 확인하고자 하는 인증 번호
     * @param email       사용자 이메일
     * @return 인증 번호 일치 여부 (true: 일치, false: 불일치)
     */
    public boolean checkNumber(int auth_number, String email) {
        String key = String.valueOf(auth_number);
        Boolean result = (email.equals(redisUtil.getData(key)));
        return result;
    }

    /**
     * 주어진 사용자 정보를 이용하여 회원 가입을 처리합니다.
     *
     * @param usersRequestVo 사용자 정보를 담고 있는 객체
     * @return 회원 가입 성공 여부 (true: 성공, false: 실패)
     */
    public Boolean joinUser(UsersRequestVo usersRequestVo) {
        // 사용자 비밀번호 암호화
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        // 사용자 프로필 사진 처리
        if (usersRequestVo.getPhotoFile() != null) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            // 프로필 사진이 없는 경우 기본 이미지 경로 설정
            usersRequestVo.setPhoto_path(domainAddress + "/resources/static/image/default/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        // 사용자 정보 저장
        if (usersRepository.saveUser(usersRequestVo)) {
            // 비동기 작업 예시 (주석 해제 시 활용 가능)
            // CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            // future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
        }
        return true;
    }


    /**
     * 사용자 로그인을 확인하고, 성공 시 쿠키에 사용자 정보와 액세스 토큰을 설정합니다.
     * 실패 시 사용자를 찾을 수 없는 경우 CustomException을 던집니다.
     *
     * @param usersRequestVo 로그인 시 필요한 사용자 정보를 담은 객체
     * @param response        HTTP 응답 객체
     * @throws CustomException ErrorCode.NOT_FOUND_USER에 대한 예외 처리
     */
    public void loginConfirm(UsersRequestVo usersRequestVo, HttpServletResponse response) {
        String email = usersRequestVo.getEmail();
        UsersResponseVo loginedUserVo = usersRepository.findUserByEmail(email);

        if (loginedUserVo != null && BCrypt.checkpw(usersRequestVo.getPassword(), loginedUserVo.getPassword())) {
            // 로그인 성공 시 사용자의 좌표 및 날씨 정보 설정
            List<String> coordinate = weatherInfo.findCoordinate(loginedUserVo.getAddress());
            String valueX = coordinate.get(0);
            String valueY = coordinate.get(1);
            String weatherAddr = coordinate.get(2);
            loginedUserVo.setValueX(Integer.parseInt(valueX));
            loginedUserVo.setValueY(Integer.parseInt(valueY));
            loginedUserVo.setWeatherAddr(weatherAddr);

            // 쿠키에 사용자 정보 및 액세스 토큰 설정
            CookieUtil.saveCookies(response, loginedUserVo);

            // 기존 리프레쉬 토큰을 취소하고 새로운 액세스 토큰을 설정하여 클라이언트에 전송
            jwtUtil.saveTokenUpdate(email, JwtUtil.REVOKED);
            TokenVo tokenVo = jwtUtil.createSettingToken(email);
            jwtUtil.setTokenInCookie(response, tokenVo.getAccessToken(), "Access");

        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
    }

    /**
     * HTTP 응답 헤더에 액세스 토큰을 설정합니다.
     *
     * @param response    HTTP 응답 객체
     * @param accessToken 설정할 액세스 토큰
     */
    private void setAccessTokenInHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, accessToken);
    }

    /**
     * HTTP 요청에서 쿠키를 추출하여 사용자 정보를 반환합니다.
     *
     * @param request HTTP 요청 객체
     * @return 추출된 사용자 정보 객체
     */
    public UsersResponseVo getCookieValue(HttpServletRequest request) {
        Map<String, List<String>> map = CookieUtil.getCookie(request);
        List<String> cookieNames = map.get("name");
        List<String> cookieValues = map.get("value");
        UsersResponseVo loginedUserVo = new UsersResponseVo();

        for (int i = 0; i < cookieNames.size(); i++) {
            String cookieName = cookieNames.get(i);
            String cookieValue = cookieValues.get(i);
            // 쿠키의 이름에 따라 사용자 정보 설정
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
        return loginedUserVo;
    }

    /**
     * 사용자 로그아웃을 처리하고, 세션 및 쿠키를 초기화합니다.
     *
     * @param req      HTTP 요청 객체
     * @param res      HTTP 응답 객체
     */
    public void blackList(HttpServletRequest req, HttpServletResponse res) {
        ServletContext servletContext = req.getServletContext();
        servletContext.removeAttribute("loginedUserVo");
        CookieUtil.clearCookie(req, res);
    }

    /**
     * 주어진 이메일이 이미 가입되어 있는지 확인합니다.
     *
     * @param email 확인하고자 하는 이메일 주소
     * @return 가입 중복 여부 (true: 중복 아님, false: 중복)
     */
    public Boolean mailCheckDuplication(String email) {
        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));
        return !result;
    }



    /**
     * 사용자 정보를 수정하고, 변경된 정보로 쿠키를 갱신합니다.
     * 프로필 이미지가 변경된 경우 예전 파일을 삭제합니다.
     *
     * @param usersRequestVo 수정할 사용자 정보를 담은 객체
     * @param oldFile        이전 프로필 이미지 파일 경로
     * @param response       HTTP 응답 객체
     * @return 수정 결과 (0: 실패, 1: 성공)
     */
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
            // 수정된 정보로 사용자 객체를 생성하여 쿠키를 갱신
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

            // 쿠키를 저장하고 세션에 사용자 정보를 설정
            boolean cookieResult = CookieUtil.saveCookies(response, newUserVo);
            if (cookieResult) {
                servletContext.setAttribute("loginedUserVo", newUserVo);
            }

            // 사용자 정보를 비동기적으로 업데이트
            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            future.thenAccept(result_info -> {
                log.info("saveStoresInfo --> : {}", result_info);
            });
        }
        return result;
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자의 정보를 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 사용자 정보 객체
     */
    public UsersResponseVo userInfo(int user_id) {
        return usersRepository.userInfo(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 등록한 가게 목록을 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 가게 목록
     */
    public List<StoreResponseVo> myStoreList(int user_id) {
        return userDao.selectMyStoreList(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 작성한 게시물 목록을 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 게시물 목록
     */
    public List<BoardVo> getMyPostList(int user_id) {
        return userDao.selectMyPostList(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 작성한 댓글 목록을 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 댓글 목록
     */
    public List<CommentResponseVo> getMyCommentList(int user_id) {
        return userDao.selectMyCommentList(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 좋아요한 게시물 목록을 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 좋아요한 게시물 목록
     */
    public List<BoardLikeVo> getMyLikePost(int user_id) {
        return userDao.selectMyLikePostList(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 작성한 게시물의 수를 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 게시물 수
     */
    public int countingPosts(int user_id) {
        return usersRepository.countingPosts(user_id);
    }

    /**
     * 주어진 사용자 ID에 해당하는 사용자가 작성한 댓글의 수를 반환합니다.
     *
     * @param user_id 조회할 사용자의 ID
     * @return 댓글 수
     */
    public int countingComments(int user_id) {
        return usersRepository.countingComments(user_id);
    }


    /**
     * 사용자 로그아웃 시 호출되는 메서드입니다. 로그아웃 시 사용자 정보를 블랙리스트에 추가하고, 클라이언트를 초기 페이지로 리다이렉션합니다.
     *
     * @param request          HTTP 요청 객체
     * @param response         HTTP 응답 객체
     * @param authentication   현재 인증된 사용자의 정보
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 사용자 정보를 블랙리스트에 추가하고 쿠키를 제거
        blackList(request, response);

        try {
            // 초기 페이지로 리다이렉션
            response.sendRedirect("index");
        } catch (IOException e) {
            // 예외 발생 시 런타임 예외로 처리
            throw new RuntimeException(e);
        }
    }
}