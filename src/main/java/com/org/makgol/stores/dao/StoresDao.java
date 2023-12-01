package com.org.makgol.stores.dao;


import com.org.makgol.stores.repository.StoresRepository;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class StoresDao {

    private final JdbcTemplate jdbcTemplate;
    private final StoresRepository storesReposiory;

    public void insertStore(HashMap<String, Object> storeMap) throws Exception {
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

            storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());


            if (storeResponseVo != null) {
                log.info("이미 존제 함. storeRequestVo.getPlace_url() --> : {} ", storeRequestVo.getPlace_url());

                storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());

                if (!storeRequestVo.getMenuName().equals("empty")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("category", storeRequestVo.getKeyword());
                    map.put("menu_name", storeRequestVo.getKeyword());

                    storesReposiory.saveCategoryMenu(map);
                }

                for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                    if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                        continue;
                    }

                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                    map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                    storesReposiory.saveMenus(map);
                }
                continue;
            }

            storesReposiory.saveStores(storeRequestVo);
            log.info("insert storeInfo --> : {}", storeRequestVo.getPlace_url());

            storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());


            if (!storeRequestVo.getMenuName().equals("empty")) {

                Map<String, Object> map = new HashMap<>();
                map.put("store_id", storeResponseVo.getId());
                map.put("category", storeRequestVo.getKeyword());
                map.put("menu_name", storeRequestVo.getKeyword());
                storesReposiory.saveCategoryMenu(map);

            }

            for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                    continue;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("store_id", storeResponseVo.getId());
                map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                storesReposiory.saveMenus(map);
            }
        }
    }

    public int checkStore(List<StoreRequestVo> storeRequestVos) throws Exception {


        int count = 0;
        for (int i = storeRequestVos.size() - 1; i >= 0; i--) {
            try {
                StoreResponseVo storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVos.get(i).getPlace_url());

                if (storeResponseVo != null) {
                    storeRequestVos.remove(i);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
