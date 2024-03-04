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

    private final StoresRepository storesReposiory;

    /**
     * 중복된 가게를 체크하고 제거하는 메서드입니다.
     *
     * @param storeRequestVos 중복 체크를 수행할 가게 정보를 담은 VO 리스트
     * @return 중복된 가게의 수
     * @throws Exception 중복 체크 중 발생한 예외
     */
    public int checkStore(List<StoreRequestVo> storeRequestVos) throws Exception {
        // 중복된 가게의 수를 저장할 변수 초기화
        int count = 0;

        // 리스트를 뒤에서부터 순회하여 중복 체크 수행
        for (int i = storeRequestVos.size() - 1; i >= 0; i--) {
            try {
                // 가게의 place_url을 사용하여 저장소에서 해당 가게 정보를 조회
                StoreResponseVo storeResponseVo = storesReposiory.findByIdPlaceUrl(storeRequestVos.get(i).getPlace_url());

                // 조회된 가게 정보가 존재하면 중복으로 간주하고 리스트에서 제거
                if (storeResponseVo != null) {
                    storeRequestVos.remove(i);
                    count++;
                }
            } catch (Exception e) {
                // 예외 발생 시 출력하고 계속 진행
                e.printStackTrace();
            }
        }
        // 중복된 가게의 수 반환
        return count;
    }
}
