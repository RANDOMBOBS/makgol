package com.org.makgol.users.service;

import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.global.exception.CustomException;
import com.org.makgol.global.exception.ErrorCode;
import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.repository.StoresRepository;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import com.org.makgol.util.kakaoMap.KakaoMap;
import com.org.makgol.util.mail.MailSendUtil;
import com.org.makgol.util.redis.RedisUtil;
import com.org.makgol.util.service.WeatherInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {


    private final MailSendUtil mailSendUtil;
    private final UserDao userDao;
    private final RedisUtil redisUtil;
    private final KakaoMap kakaoMapSearch;
    private final StoresDao storesDao;
    private final UsersRepository usersRepository;
    private final FileUpload fileUpload;
    private final WeatherInfo weatherInfo;
    private final StoresRepository storesRepository;

    public String userFindId(String userEmail) {
        return userEmail;
    }

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
        //사용자 패스워드 암호화
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if (usersRequestVo.getPhotoFile() != null) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            usersRequestVo.setPhoto_path("/resources/static/image/default/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        if (usersRepository.insertUser(usersRequestVo)) {
            saveStoresProcess(usersRequestVo.getEmail());

            return true;
        }
        return false;
    }// joinUser_END

    public UsersResponseVo loginConfirm(UsersRequestVo usersRequestVo) {
        String email = usersRequestVo.getEmail();
        UsersResponseVo loginedUserVo = userDao.selectUser(email);

        // 만약 로그인을 못했다면?
        if(loginedUserVo == null){
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        } else{
            List<Integer> coordinate = weatherInfo.findCoordinate(loginedUserVo.getAddress());
            loginedUserVo.setCoordinate(coordinate);
        }

        if (!BCrypt.checkpw(usersRequestVo.getPassword(), loginedUserVo.getPassword())) {
            loginedUserVo = null;
        }
        return loginedUserVo;
    }

    public Boolean mailCheckDuplication(String email) {

        Boolean result = email.equals(usersRepository.duplicationUserEmail(email));

        return !result;
    }

    public int modifyUserInfo(UsersRequestVo usersRequestVo, String oldFile, HttpSession session) {
        System.out.println("바뀐회원정보는??"+usersRequestVo);
        String oldFileName = oldFile.substring(oldFile.lastIndexOf("/") + 1);
        String currentDirectory = System.getProperty("user.dir");
        usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));

        if (usersRequestVo.getPhotoFile() != null && !usersRequestVo.getPhotoFile().isEmpty()) {
            FileInfo fileInfo = fileUpload.fileUpload(usersRequestVo.getPhotoFile());
            usersRequestVo.setPhoto_path(fileInfo.getPhotoPath());
            usersRequestVo.setPhoto(fileInfo.getPhotoName());
        } else {
            usersRequestVo.setPhoto_path("/resources/static/image/default/user_default.jpeg");
            usersRequestVo.setPhoto("user_default.jpeg");
        }

        System.out.println("유저정보는???????"+usersRequestVo);

        int result = userDao.updateUserInfo(usersRequestVo);

        if (result > 0) {
            UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
            usersRequestVo.setName(loginedUserVo.getName());
            usersRequestVo.setEmail(loginedUserVo.getEmail());
            UsersResponseVo newUserVo = new UsersResponseVo();
            newUserVo.modifyMapper(usersRequestVo);
            List<Integer> coordinate = weatherInfo.findCoordinate(newUserVo.getAddress());
            newUserVo.setCoordinate(coordinate);
            session.setAttribute("loginedUserVo", newUserVo);
            String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + oldFileName;
            File oldfile = new File(deleteFile);
            oldfile.delete();

            saveStoresProcess(usersRequestVo.getEmail());
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


    public void saveStoresProcess(String email){
        UsersResponseVo usersResponseVo = usersRepository.findUserByEmail(email);
        List<StoreRequestVo> storeRequestVoList = kakaoMapSearch.storeInfoSearch(usersResponseVo);

        try {
            //업장 중복 체크
            log.info("before storeRequestVoList --> : {}", storeRequestVoList.size());
            log.info("duplication count --> : {} ", storesDao.checkStore(storeRequestVoList));
            log.info("after storeRequestVoList --> : {}", storeRequestVoList.size());

            HashMap<String, Object> storeMap = kakaoMapSearch.storeInfoRequest(storeRequestVoList);

            //store 업장 데이터 넣기
            StoreResponseVo storeResponseVo;
            for (int index = 0; index < storeMap.size() / 2; index++) {
                System.out.println("------" + index + "-------");

                StoreRequestVo storeRequestVo = (StoreRequestVo) storeMap.get("store_info_" + index);
                List<StoreRequestMenuVo> storeRequestMenuVoList = (List<StoreRequestMenuVo>) storeMap.get("store_menu_" + index);

                //store에 정보가 없을 경우
                if (storeRequestVo == null) {
                    System.out.println("storeRequestVo == null");
                    continue;
                }

                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());


                if (storeResponseVo != null) {
                    log.info("이미 존제 함. storeRequestVo.getPlace_url() --> : {} ", storeRequestVo.getPlace_url());

                    storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());

                    if (!storeRequestVo.getMenuName().equals("empty")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("store_id", storeResponseVo.getId());
                        map.put("category", storeRequestVo.getKeyword());
                        map.put("menu_name", storeRequestVo.getKeyword());

                        storesRepository.saveCategoryMenu(map);
                    }

                    for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                        if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                            continue;
                        }

                        Map<String, Object> map = new HashMap<>();
                        map.put("store_id", storeResponseVo.getId());
                        map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                        map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                        storesRepository.saveMenus(map);
                    }
                    continue;
                }

                storesRepository.saveStores(storeRequestVo);
                log.info("insert storeInfo --> : {}", storeRequestVo.getPlace_url());

                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());


                if (!storeRequestVo.getMenuName().equals("empty")) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("category", storeRequestVo.getKeyword());
                    map.put("menu_name", storeRequestVo.getKeyword());
                    storesRepository.saveCategoryMenu(map);

                }

                for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                    if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                        continue;
                    }

                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                    map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                    storesRepository.saveMenus(map);
                }
            }

        } catch (Exception e) {}
    }
}



