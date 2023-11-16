package com.org.makgol.users.service;

import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.exception.CustomException;
import com.org.makgol.exception.ErrorCode;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.KakaoMapSearch;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import com.org.makgol.util.mail.MailSendUtil;
import com.org.makgol.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {


    private final MailSendUtil mailSendUtil;
    private final UserDao userDao;
    private final RedisUtil redisUtil;
    private final KakaoMapSearch kakaoMapSearch;
    private final StoresDao storesDao;
    private final UsersRepository usersRepository;
    private final FileUpload fileUpload;

    public String userFindId(String userEmail){
        return userEmail;
    }

    //userFindPassword
    public String userFindPassword(String userEmail){

        if(userDao.findUserEmail(userEmail)) {

            int randomNumber= mailSendUtil.makeRandomNumber();
            String newPassword = String.valueOf(randomNumber);

            if(userDao.updatePassword(newPassword, userEmail)) {
                mailSendUtil.sendMail(randomNumber, userEmail);

            }
            return newPassword;

        } else {
            return "회원가입된 이메일이 아닙니다.";
        }

    }// userFindPassword_END



    //이메일 번호 송신
    public boolean checkEmail(String email) {
        int authNumber= mailSendUtil.makeRandomNumber();
        String key = String.valueOf(authNumber);
        if(mailSendUtil.sendMail(authNumber, email)){
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
        //사용자 패스워드 암호화
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if(usersRequestVo.getPhotoFile() != null){
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            usersRequestVo.setPhoto_path("/fileUpload/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        if(userDao.createUser(usersRequestVo)) {

            UsersResponseVo usersResponseVo = userDao.findXY(usersRequestVo);
            List<StoreRequestVo> storeRequestVoList = kakaoMapSearch.storeInfoSearch(usersResponseVo);

            try {
                //업장 중복 체크
                System.out.println("before storeRequestVoList --> : "+storeRequestVoList.size());
                System.out.println(storesDao.checkStore(storeRequestVoList));
                System.out.println("after storeRequestVoList --> : "+storeRequestVoList.size());

                HashMap<String, Object> storeMap = kakaoMapSearch.storeInfoRequest(storeRequestVoList);
                storesDao.insertStore(storeMap);
            } catch(Exception e) {}

            return true;
        }
        return false;
    }// joinUser_END

    public UsersRequestVo loginConfirm(UsersRequestVo usersRequestVo) {
        String email = usersRequestVo.getEmail();
        UsersRequestVo loginedInUsersRequestVo = userDao.selectUser(email);

        if(loginedInUsersRequestVo == null) { throw new CustomException(ErrorCode.NOT_FOUND_USER); }

        if (!BCrypt.checkpw(usersRequestVo.getPassword(), loginedInUsersRequestVo.getPassword())) { loginedInUsersRequestVo = null; }

        return loginedInUsersRequestVo;
    }

    public Boolean mailCheckDuplication(String email) {

        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));

        return !result;
    }

    public int modifyUserInfo(UsersRequestVo usersRequestVo, String oldFile, HttpSession session) {
        String oldFileName = oldFile.substring(oldFile.lastIndexOf("/")+1, oldFile.length());
        String currentDirectory = System.getProperty("user.dir");
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if(usersRequestVo.getPhotoFile() != null && !usersRequestVo.getPhotoFile().isEmpty()){
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            usersRequestVo.setPhoto_path("/fileUpload/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        int result = userDao.updateUserInfo(usersRequestVo);

        if (result > 0) {



            UsersRequestVo loginedUsersRequestVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
            usersRequestVo.setName(loginedUsersRequestVo.getName());
            usersRequestVo.setEmail(loginedUsersRequestVo.getEmail());
            session.setAttribute("loginedUsersRequestVo", usersRequestVo);
            if(!oldFileName.equals("user_default.jpeg")){
            String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + oldFileName;
            File oldfile = new File(deleteFile);
            oldfile.delete();
            }

            UsersResponseVo usersResponseVo = userDao.findXY(usersRequestVo);
            List<StoreRequestVo> storeRequestVoList = kakaoMapSearch.storeInfoSearch(usersResponseVo);

            try {
                //업장 중복 체크
                System.out.println("before storeRequestVoList --> : "+storeRequestVoList.size());
                System.out.println(storesDao.checkStore(storeRequestVoList));
                System.out.println("after storeRequestVoList --> : "+storeRequestVoList.size());

                HashMap<String, Object> storeMap = kakaoMapSearch.storeInfoRequest(storeRequestVoList);
                storesDao.insertStore(storeMap);
            } catch(Exception e) {}

            return result;
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

}



