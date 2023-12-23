package com.org.makgol.util.kakaoMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.Crawller;
import com.org.makgol.util.kakaoMap.vo.Category;
import com.org.makgol.util.kakaoMap.vo.KakaoLocalRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMap{

    private final KakaoService kakaoService;
    private final StoresDao storesDao;

    public void addressSearch(String address, KakaoLocalRequestVo kakaoLocalRequestVo) throws Exception {

        kakaoLocalRequestVo.setKeyword(address);
        KakaoLocalResponseJSON kakaoResponseJSON = kakaoService.callKakaoLocalAPI(kakaoLocalRequestVo);
        // ShopInfo 리스트를 가져온다
        List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
        // logger.logResponseJson(kakaoResponseJSON);
        //HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
        //httpTransactionLogger.logResponseJson(kakaoResponseJSON);

        // ShopInfo를 StoreRequestVo로 매핑
        List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
                .collect(Collectors.toList());

    }

    public void search(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo) throws Exception {
        List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();

        // 음식 카테고리 매핑
        for (int i = 0; i < categories.length; i++) {

            kakaoLocalRequestVo.setKeyword(categories[i]);
            KakaoLocalResponseJSON kakaoResponseJSON = kakaoService.callKakaoLocalAPI(kakaoLocalRequestVo);
            // ShopInfo 리스트를 가져온다
            List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
            // logger.logResponseJson(kakaoResponseJSON);
            //HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
            //httpTransactionLogger.logResponseJson(kakaoResponseJSON);

            // ShopInfo를 StoreRequestVo로 매핑
            List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
                    .collect(Collectors.toList());

            storeRequestVoList.addAll(storeRequestVoListAdd);

            for (StoreRequestVo storeRequestVo : storeRequestVoList) {
                storeRequestVo.setKeyword(categories[i]);
            }
        }

        //restApiCrawller(storeRequestVoList);
    }

    public List<StoreRequestVo> searchMenu(String[] categories, KakaoLocalRequestVo kakaoLocalRequestVo, List<StoreRequestVo> storeRequestVoList) {

        // 음식 카테고리 매핑
        for (int i = 1; i < categories.length; i++) {

            kakaoLocalRequestVo.setKeyword(categories[i]);
            KakaoLocalResponseJSON kakaoResponseJSON = kakaoService.callKakaoLocalAPI(kakaoLocalRequestVo);
            // ShopInfo 리스트를 가져온다
            List<KakaoLocalResponseJSON.ShopInfo> shopInfoList = kakaoResponseJSON.documents;
            // logger.logResponseJson(kakaoResponseJSON);
            //HttpTransactionLogger httpTransactionLogger = new HttpTransactionLogger();
            //httpTransactionLogger.logResponseJson(kakaoResponseJSON);


            // ShopInfo를 StoreRequestVo로 매핑
            List<StoreRequestVo> storeRequestVoListAdd = shopInfoList.stream().map(KakaoLocalResponseJSON.ShopInfo::mapToStoreRequestVo)
                    .collect(Collectors.toList());


            int beforeAddSize = storeRequestVoList.size();
            storeRequestVoList.addAll(storeRequestVoListAdd);
            int afterAddSize = storeRequestVoList.size();


            for(int j=beforeAddSize; j < afterAddSize; j++) {
                storeRequestVoList.get(j).setKeyword(categories[0]);
                storeRequestVoList.get(j).setMenuName(categories[i]);
            }
        }
        return storeRequestVoList;
    }

    public HashMap<String, Object> restApiCrawller(List<StoreRequestVo> storeRequestVoList) throws Exception {
        Crawller crawller = new Crawller();
        HashMap<String, Object> hashMap = crawller.new_crawller(storeRequestVoList);
        storeInfoLog(hashMap);

        return hashMap;
    }

    public List<StoreRequestVo> storeInfoSearch(UsersResponseVo usersResponseVo){

        KakaoLocalRequestVo kakaoLocalRequestVo = new KakaoLocalRequestVo();
        kakaoLocalRequestVo.setY(String.valueOf(usersResponseVo.getLatitude()));
        kakaoLocalRequestVo.setX(String.valueOf(usersResponseVo.getLongitude()));

        //kakaoMapSearch.search(foodCategories, kakaoLocalRequestVo);
        List<StoreRequestVo> storeRequestVoList = new ArrayList<StoreRequestVo>();

        //String[] foodCategories = Arrays.stream(Category.CategoryFood.values()).map(Enum::name).toArray(String[]::new);

        // CategoryMenukorea의 값을 String 배열로 변환
        String[] CategoryKoreaStewMenu = Arrays.stream(Category.CategoryKoreaStewMenu.values())
                .map(Enum::name)
                .toArray(String[]::new);
        storeRequestVoList = searchMenu(CategoryKoreaStewMenu, kakaoLocalRequestVo, storeRequestVoList);


//        String[] CategoryKoreaRoastMenu = Arrays.stream(Category.CategoryKoreaRoastMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryKoreaRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryKoreaRiceMenu = Arrays.stream(Category.CategoryKoreaRiceMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryKoreaRiceMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryKoreaSnasickMenu = Arrays.stream(Category.CategoryKoreaSnasickMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryKoreaSnasickMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryKoreaNoodleMenu = Arrays.stream(Category.CategoryKoreaNoodleMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryKoreaNoodleMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryWesternNoodleMenu = Arrays.stream(Category.CategoryWesternNoodleMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryWesternNoodleMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryWesternRiceMenu = Arrays.stream(Category.CategoryWesternRiceMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryWesternRiceMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryWesternRoastMenu = Arrays.stream(Category.CategoryWesternRoastMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryWesternRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//        String[] CategoryWesternETCMenu = Arrays.stream(Category.CategoryWesternETCMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryWesternETCMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryChinaNoodleMenu = Arrays.stream(Category.CategoryChinaNoodleMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryChinaNoodleMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryChinaRiceMenu = Arrays.stream(Category.CategoryChinaRiceMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryChinaRiceMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryChinaETCMenu = Arrays.stream(Category.CategoryChinaETCMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryChinaETCMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryChinaFriedMenu = Arrays.stream(Category.CategoryChinaFriedMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryChinaFriedMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryChinaRoastMenu = Arrays.stream(Category.CategoryChinaRoastMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryChinaRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryJapanRoastMenu = Arrays.stream(Category.CategoryJapanRoastMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryJapanRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryJapanFrideMenu = Arrays.stream(Category.CategoryJapanFrideMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryJapanFrideMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryJapanRiceMenu = Arrays.stream(Category.CategoryJapanRiceMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryJapanRiceMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryJapanNoodleMenu = Arrays.stream(Category.CategoryJapanNoodleMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryJapanNoodleMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryGlobal_bMenu = Arrays.stream(Category.CategoryGlobal_bMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryGlobal_bMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryGlobal_tMenu = Arrays.stream(Category.CategoryGlobal_tMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryGlobal_tMenu, kakaoLocalRequestVo, storeRequestVoList);
//
//
//        String[] CategoryGlobal_mMenu = Arrays.stream(Category.CategoryGlobal_mMenu.values())
//                .map(Enum::name)
//                .toArray(String[]::new);
//        storeRequestVoList = searchMenu(CategoryGlobal_mMenu, kakaoLocalRequestVo, storeRequestVoList);


        int i=0;
        //확인하기
        for(StoreRequestVo storeRequestVo: storeRequestVoList) {
            i++;
            System.out.println("index"+ i +" --> : " +storeRequestVo.getPlace_url());
            System.out.println("index"+ i +" --> : " +storeRequestVo.getMenuName());
        }

        return storeRequestVoList;
    }

    public void storeInfoLog(HashMap<String, Object> hashMap) {
        System.out.println("after storeInfoSize --> : "+ hashMap.size()/2);
        for(int index=0; index<(hashMap.size()/2); index ++){
            StoreRequestVo storeRequestVo = (StoreRequestVo) hashMap.get("store_info_" + index);
            if(storeRequestVo == null){ continue; }
            log.info("hashIndex "+ index +": 이름 : "+ storeRequestVo.getName());
            log.info("hashIndex "+ index +": 주소 : "+ storeRequestVo.getAddress());
            log.info("hashIndex "+ index +": 도로명 : "+ storeRequestVo.getLoad_address());
            log.info("hashIndex "+ index +": 전화번호 : "+ storeRequestVo.getPhone());
            log.info("hashIndex "+ index +": 카테고리 : "+ storeRequestVo.getCategory());
            log.info("hashIndex "+ index +": 상세페이지 : "+ storeRequestVo.getPlace_url());
            log.info("hashIndex "+ index +": 업데이트 : "+ storeRequestVo.getUpdate_date());
            log.info("hashIndex "+ index +": 영업시간 : "+ storeRequestVo.getOpening_hours());
            log.info("hashIndex "+ index +": 메뉴 업데이트 : "+ storeRequestVo.getMenu_update());
            log.info("hashIndex "+ index +": 이미지 : "+ storeRequestVo.getPhoto());
            System.out.println();
        }
    }

    public HashMap<String, Object> storeInfoRequest(List<StoreRequestVo> storeRequestVoList) throws Exception {
        HashMap<String, Object> storeMap = restApiCrawller(storeRequestVoList);
        return storeMap;
    }
}
