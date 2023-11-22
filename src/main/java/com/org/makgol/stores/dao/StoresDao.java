package com.org.makgol.stores.dao;


import com.org.makgol.stores.repository.StoresRepository;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StoresDao {

    private final JdbcTemplate jdbcTemplate;
    private final StoresRepository storesReposiory;

    public void insertStore(HashMap<String, Object> storeMap) throws Exception {
        System.out.println(storeMap.size() / 2);
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

            String sql = "SELECT id FROM stores WHERE place_url = ? ";
            int store_id = 0;

            //try { store_id = jdbcTemplate.queryForObject(sql, Integer.class, storeRequestVo.getPlace_url()); } catch (Exception e) {e.printStackTrace();}
            storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());


            if (storeResponseVo != null) {
                System.out.println("이미 존제 함. storeRequestVo.getPlace_url() --> :" + storeRequestVo.getPlace_url());

                //store_id = jdbcTemplate.queryForObject(sql, Integer.class, storeRequestVo.getPlace_url());
                storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());

                if (!storeRequestVo.getMenuName().equals("empty")) {
                    String insertStoresMenuSql = "INSERT INTO category_menu (store_id, category, menu_name, date) VALUES (?, ?, ?, now())";
                    jdbcTemplate.update(insertStoresMenuSql,
                            storeResponseVo.getId(),
                            storeRequestVo.getKeyword(),
                            storeRequestVo.getMenuName()
                    );
                }

                for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                    if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                        continue;
                    }
                    String insertStoresMenuSql = "INSERT INTO Menus (store_id, menu, price, date) \n"
                            + "VALUES (?, ?, ?, now())";

                    jdbcTemplate.update(insertStoresMenuSql,
                            storeResponseVo.getId(),
                            storeRequestMenuVoList.get(menuIndex).getMenu(),
                            storeRequestMenuVoList.get(menuIndex).getPrice()
                    );
                }

                continue;
            }

            String insertStoresSql = "INSERT INTO Stores (name, likes, longitude, latitude, address, load_address, category, opening_hours, phone, site, menu_update, place_url, update_date, distance) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //int result = 0;
            jdbcTemplate.update(insertStoresSql,
                    storeRequestVo.getName(), storeRequestVo.getLikes(), storeRequestVo.getLongitude(),
                    storeRequestVo.getLatitude(), storeRequestVo.getAddress(), storeRequestVo.getLoad_address(),
                    storeRequestVo.getCategory(), storeRequestVo.getOpening_hours(), storeRequestVo.getPhone(),
                    storeRequestVo.getSite(), storeRequestVo.getMenu_update(), storeRequestVo.getPlace_url(),
                    storeRequestVo.getUpdate_date(), storeRequestVo.getDistance());

            System.out.println("insert storeInfo --> : " + storeRequestVo.getPlace_url());


            //try { store_id = jdbcTemplate.queryForObject(sql, Integer.class, storeRequestVo.getPlace_url()); } catch (Exception e) {e.printStackTrace();}
            storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVo.getPlace_url());


            if (!storeRequestVo.getMenuName().equals("empty")) {
                String insertStoresMenuSql = "INSERT INTO category_menu (store_id, category, menu_name, date) VALUES (?, ?, ?, now())";
                jdbcTemplate.update(insertStoresMenuSql,
                        storeResponseVo.getId(),
                        storeRequestVo.getKeyword(),
                        storeRequestVo.getMenuName()
                );
            }

            for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                    continue;
                }
                String insertStoresMenuSql = "INSERT INTO Menus (store_id, menu, price, date) VALUES (?, ?, ?, now())";

                jdbcTemplate.update(insertStoresMenuSql,
                        storeResponseVo.getId(),
                        storeRequestMenuVoList.get(menuIndex).getMenu(),
                        storeRequestMenuVoList.get(menuIndex).getPrice()
                );
            }
        }
    }

    public int checkStore(List<StoreRequestVo> storeRequestVos) throws Exception {


        int count = 0;
        for (int i = storeRequestVos.size() - 1; i >= 0; i--) {
            String sql = "SELECT id FROM stores WHERE place_url = ? ";
            int store_id = 0;

            //try { store_id = jdbcTemplate.queryForObject(sql, Integer.class, storeRequestVos.get(i).getPlace_url()); } catch (Exception e) {}
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
