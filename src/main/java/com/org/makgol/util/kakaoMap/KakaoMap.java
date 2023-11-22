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
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

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


        //String[] foodCategories = Arrays.stream(Category.CategoryFood.values()).map(Enum::name).toArray(String[]::new);

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
        storeRequestVoList = searchMenu(CategoryKoreaStewMenu, kakaoLocalRequestVo, storeRequestVoList);
        storeRequestVoList = searchMenu(CategoryKoreaRoastMenu, kakaoLocalRequestVo, storeRequestVoList);
        storeRequestVoList = searchMenu(CategoryKoreaRiceMenu, kakaoLocalRequestVo, storeRequestVoList);

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
            System.out.println("hashIndex "+ index +": 이름 : "+ storeRequestVo.getName());
            System.out.println("hashIndex "+ index +": 주소 : "+ storeRequestVo.getAddress());
            System.out.println("hashIndex "+ index +": 도로명 : "+ storeRequestVo.getLoad_address());
            System.out.println("hashIndex "+ index +": 전화번호 : "+ storeRequestVo.getPhone());
            System.out.println("hashIndex "+ index +": 카테고리 : "+ storeRequestVo.getCategory());
            System.out.println("hashIndex "+ index +": 상세페이지 : "+ storeRequestVo.getPlace_url());
            System.out.println("hashIndex "+ index +": 업데이트 : "+ storeRequestVo.getUpdate_date());
            System.out.println("hashIndex "+ index +": 영업시간 : "+ storeRequestVo.getOpening_hours());
            System.out.println("hashIndex "+ index +": 메뉴 업데이트 : "+ storeRequestVo.getMenu_update());
            System.out.println();
        }
    }

    public HashMap<String, Object> storeInfoRequest(List<StoreRequestVo> storeRequestVoList) throws Exception {
        HashMap<String, Object> storeMap = restApiCrawller(storeRequestVoList);
        return storeMap;
    }
}
