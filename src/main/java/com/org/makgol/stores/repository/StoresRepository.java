package com.org.makgol.stores.repository;

import com.org.makgol.stores.dto.ResponseStoreListDto;
import com.org.makgol.stores.vo.StoreResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoresRepository {
    StoreResponseVo findByIdPlaceUrl(String place_url);

    List<ResponseStoreListDto> findStoreList(String keyword);
}
