package com.org.makgol.users.service;

import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.vo.Category;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.util.KakaoMapSearch;
import com.org.makgol.util.mail.MailSendUtil;
import com.org.makgol.util.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
        mailSendUtil.sendMail(authNumber, email);

        return redisUtil.setDataExpire(key, email, 60 * 3L);

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

        if(userDao.createDao(usersRequestVo)) {
            HashMap<String, Object> storeMap = new HashMap<String, Object>();

            usersRequestVo = userDao.findXY(usersRequestVo);

            KakaoLocalRequestVo kakaoLocalRequestVo = new KakaoLocalRequestVo();

            kakaoLocalRequestVo.setY(String.valueOf(usersRequestVo.getLatitude()));
            kakaoLocalRequestVo.setX(String.valueOf(usersRequestVo.getLongitude()));


            //String[] foodCategories = Arrays.stream(Category.CategoryFood.values())
            //      .map(Enum::name)
            //    .toArray(String[]::new);

            // CategoryMenukorea의 값을 String 배열로 변환
            String[] CategoryKoreaStewMenu = Arrays.stream(Category.CategoryKoreaStewMenu.values())
                    .map(Enum::name)
                    .toArray(String[]::new);

            String[] CategoryKoreaRoastMenu = Arrays.stream(Category.CategoryKoreaRoastMenu.values())
                    .map(Enum::name)
                    .toArray(String[]::new);

            String[] CategoryKoreaRiceMenu = Arrays.stream(Category.CategoryKoreaRiceMenu.values())
                    .map(Enum::name)
                    .toArray(String[]::new);


            //kakaoMapSearch.search(foodCategories, kakaoLocalRequestVo);
            List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();
            storeRequestVoList = kakaoMapSearch.searchMenu(CategoryKoreaStewMenu, kakaoLocalRequestVo, storeRequestVoList);
            storeRequestVoList = kakaoMapSearch.searchMenu(CategoryKoreaRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
            storeRequestVoList = kakaoMapSearch.searchMenu(CategoryKoreaRiceMenu, kakaoLocalRequestVo, storeRequestVoList);

            System.out.println(storeRequestVoList.size());

            int i=0;
            for(StoreRequestVo storeRequestVo: storeRequestVoList) {
                i++;
                System.out.println("index"+ i +" --> : " +storeRequestVo.getPlace_url());
                System.out.println("index"+ i +" --> : " +storeRequestVo.getMenuName());
            }

            try {
                storeMap = kakaoMapSearch.restApiCrawller(storeRequestVoList);

                //업장 중복 체크
                System.out.println("before storeRequestVoList --> : "+storeRequestVoList.size());
                System.out.println(storesDao.checkStore(storeRequestVoList));
                System.out.println("after storeRequestVoList --> : "+storeRequestVoList.size());

                storesDao.insertStore(storeMap);
            } catch(Exception e) {}

            return true;
        }
        return false;
    }// joinUser_END
}
