package com.org.makgol.users.service;

import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.util.Crawller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final StoresDao storesDao;


    public void usersJoin(List<StoreRequestVo> storeRequestVoList) throws Exception {

        //업장 중복 체크
        System.out.println("before storeRequestVoList --> : "+storeRequestVoList.size());
        System.out.println(storesDao.checkStore(storeRequestVoList));
        System.out.println("after storeRequestVoList --> : "+storeRequestVoList.size());

        Crawller crawller = new Crawller();
        HashMap<String, Object> hashMap = crawller.new_crawller(storeRequestVoList);
        System.out.println("after storeInfoSize --> : "+ hashMap.size()/2);

        for(int index=0; index<(hashMap.size()/2); index ++){
            StoreRequestVo storeRequestVo = (StoreRequestVo) hashMap.get("store_info_" + index);
            if(storeRequestVo == null){ continue; }
            System.out.println("thread "+ index +": 이름 : "+ storeRequestVo.getName());
            System.out.println("thread "+ index +": 주소 : "+ storeRequestVo.getAddress());
            System.out.println("thread "+ index +": 도로명 : "+ storeRequestVo.getLoad_address());
            System.out.println("thread "+ index +": 전화번호 : "+ storeRequestVo.getPhone());
            System.out.println("thread "+ index +": 카테고리 : "+ storeRequestVo.getCategory());
            System.out.println("thread "+ index +": 상세페이지 : "+ storeRequestVo.getPlace_url());
            System.out.println("thread "+ index +": 업데이트 : "+ storeRequestVo.getUpdate_date());
            System.out.println("thread "+ index +": 영업시간 : "+ storeRequestVo.getOpening_hours());
            System.out.println("thread "+ index +": 메뉴 업데이트 : "+ storeRequestVo.getMenu_update());
            System.out.println();
        }

        storesDao.insertStore(hashMap);
        System.out.println("storesDao.insertStore(hashMap);");
    }
}
