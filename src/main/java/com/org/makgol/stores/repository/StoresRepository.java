package com.org.makgol.stores.repository;

import com.org.makgol.stores.dto.ResponseStoreListDto;
import com.org.makgol.stores.dto.StoreMenuDto;
import com.org.makgol.stores.vo.StoreResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoresRepository {
    StoreResponseVo findByIdPlaceUrl(String place_url);

    List<ResponseStoreListDto> findStoreList(Map<String, String> map);
    String findStoreIdWithPlaceName(String placeName);
    List<StoreMenuDto> findStoreMenuWithId(String storeId);
}
