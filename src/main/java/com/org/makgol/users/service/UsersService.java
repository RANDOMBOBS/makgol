package com.org.makgol.users.service;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.common.exception.CustomException;
import com.org.makgol.common.exception.ErrorCode;
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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.org.makgol.util.CompletableFuture.fetchDataAsync;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final MailSendUtil mailSendUtil;
    private final UserDao userDao;
    private final RedisUtil redisUtil;
    private final UsersRepository usersRepository;
    private final FileUpload fileUpload;
    private final WeatherInfo weatherInfo;
    private final ServletContext servletContext;


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

            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
            // 비동기 작업이 완료되면 결과를 출력
            future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
        }

        return true;
    }// joinUser_END




    public void loginConfirm(UsersRequestVo usersRequestVo, HttpServletResponse response) {
        String email = usersRequestVo.getEmail();
        UsersResponseVo loginedUserVo = userDao.selectUser(email);

        // 이메일과 일치하는 유저 정보가 있고, 비밀번호도 일치하면!
        if(loginedUserVo != null && BCrypt.checkpw(usersRequestVo.getPassword(), loginedUserVo.getPassword())){
            List<String> coordinate = weatherInfo.findCoordinate(loginedUserVo.getAddress());
            String valueX = coordinate.get(0);
            String valueY = coordinate.get(1);
            String weatherAddr = coordinate.get(2);

            CookieUtil.setCookie(response, "name", loginedUserVo.getName());
            CookieUtil.setCookie(response, "email", loginedUserVo.getEmail());
            CookieUtil.setCookie(response, "phone", loginedUserVo.getPhone());
            CookieUtil.setCookie(response, "photo", loginedUserVo.getPhoto());
            CookieUtil.setCookie(response, "photo_path", loginedUserVo.getPhoto_path());
            CookieUtil.setCookie(response, "grade", loginedUserVo.getGrade());
            CookieUtil.setCookie(response, "weatherAddr", weatherAddr);
            CookieUtil.setCookie(response, "valueX", valueX);
            CookieUtil.setCookie(response, "valueY", valueY);

        } else{
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

    }

    public boolean getCookieValue(HttpServletRequest request){
        Map<String, List<String>> map = CookieUtil.getCookie(request);
        List<String> cookieNames = map.get("name");
        List<String> cookieValues = map.get("value");
        UsersResponseVo loginedUserVo = new UsersResponseVo();

        for(int i=0; i<cookieNames.size(); i++){
            String cookieName = cookieNames.get(i);
            String cookieValue = cookieValues.get(i);

            if(cookieName.equals("name")){
                loginedUserVo.setName(cookieValue);
            } else if (cookieName.equals("email")) {
                loginedUserVo.setEmail(cookieValue);
            } else if (cookieName.equals("phone")) {
                loginedUserVo.setPhone(cookieValue);
            } else if (cookieName.equals("photo")) {
                loginedUserVo.setPhoto(cookieValue);
            } else if (cookieName.equals("photo_path")) {
                loginedUserVo.setPhoto_path(cookieValue);
            } else if (cookieName.equals("grade")) {
                loginedUserVo.setGrade(cookieValue);
            } else if (cookieName.equals("weatherAddr")) {
                loginedUserVo.setWeatherAddr(cookieValue);
            } else if (cookieName.equals("valueX")) {
                loginedUserVo.setValueX(Integer.parseInt(cookieValue));
            } else if (cookieName.equals("valueY")) {
                loginedUserVo.setValueY(Integer.parseInt(cookieValue));
            }
        }
        servletContext.setAttribute("loginedUserVo", loginedUserVo);
        return true;
    }


    public void blackList(HttpServletRequest req, HttpServletResponse res){
        System.out.println("블랙리스트");
        ServletContext servletContext = req.getServletContext();
        servletContext.removeAttribute("loginedUserVo");
        CookieUtil.clearCookie(req, res);
    }


    public Boolean mailCheckDuplication(String email) {

        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));

        return !result;
    }


//    public int modifyUserInfo(UsersRequestVo usersRequestVo, String oldFile, HttpSession session) {
//        System.out.println("바뀐회원정보는??"+usersRequestVo);
//        UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
//        System.out.println("세션 유저 정보?"+loginedUserVo);
//
//
//        int result =0;
//        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));
//        if (usersRequestVo.getPhotoFile() != null && !usersRequestVo.getPhotoFile().isEmpty()) {
//            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
//            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
//            usersRequestVo.setPhoto(fileInfo.getPhotoName());
//            result = userDao.updateUserPhotoInfo(usersRequestVo);
//            if(result > 0){
//                oldFile = "["+oldFile+"]";
//                System.out.println("예전파일은?" +oldFile);
//                FileUpload.deleteFileList(oldFile);
//            }
//        } else {
//            result = userDao.updateUserInfo(usersRequestVo);
//            usersRequestVo.setPhoto_path(loginedUserVo.getPhoto_path());
//            usersRequestVo.setPhoto(loginedUserVo.getPhoto());
//        }
//
//        if (result > 0) {
//            usersRequestVo.setName(loginedUserVo.getName());
//            usersRequestVo.setEmail(loginedUserVo.getEmail());
//            UsersResponseVo newUserVo = new UsersResponseVo();
//            newUserVo.modifyMapper(usersRequestVo);
//            System.out.println("새로 저장할 유저 정보?"+newUserVo);
//            List<String> coordinate = weatherInfo.findCoordinate(newUserVo.getAddress());
//            newUserVo.setCoordinate(coordinate);
//            session.setAttribute("loginedUserVo", newUserVo);
//
//            CompletableFuture<String> future = fetchDataAsync(usersRequestVo.getEmail());
//            // 비동기 작업이 완료되면 결과를 출력
//            future.thenAccept(result_info -> { log.info("saveStoresInfo --> : {}", result_info); });
//        }
//        return result;
//    }

    public List<StoreResponseVo> myStoreList(int user_id){
        return userDao.selectMyStoreList(user_id);
    }



    public List<BoardVo> getMyPostList(int user_id){
        return userDao.selectMyPostList(user_id);
    }

    public List<CommentResponseVo> getMyCommentList(int user_id){
        return userDao.selectMyCommentList(user_id);
    }

    public List<BoardLikeVo> getMyLikePost(int user_id){
        return userDao.selectMyLikePostList(user_id);
    }

    public int countingPosts(int user_id){
        return usersRepository.countingPosts(user_id);
    }
    public int countingComments(int user_id){
        return usersRepository.countingComments(user_id);
    }
    public int countingLikes(int user_id){
        return usersRepository.countingLikes(user_id);
    }
}



