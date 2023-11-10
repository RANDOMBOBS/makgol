package com.org.makgol.stores.repository;

import com.org.makgol.stores.vo.StoreResponseVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoresReposiory {
    StoreResponseVo findByIdPlaceUrl(String place_url);
}
