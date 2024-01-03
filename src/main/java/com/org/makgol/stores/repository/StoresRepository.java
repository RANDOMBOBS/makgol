package com.org.makgol.stores.repository;

import com.org.makgol.stores.dto.*;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoresRepository {
    StoreResponseVo findByIdPlaceUrl(String place_url);

    List<ResponseStoreListDto> findStoreList(Map<String, String> map);

    String findStoreIdWithPlaceName(String placeName);

    StoreDetailDto findStoreDetailWithId(int storeId);

    List<StoreMenuDto> findStoreMenuWithId(int storeId);

    List<StoreReviewDto> findStoreReviewWithId(int storeId);

    List<String> findStoreReviewImageWithId(int reviewId);

    List<UserInfoDto> findUserInfoWithId(int userId);

    void createReview(CreateReviewDto createReviewDto);

    void modifyReviewWithId(ModifyReviewDto modifyReviewDto);

    StoreLikesDto getLikesStatus(LikesDto likesDto);

    void increaseLikesWithId(LikesDto likesDto);

    void createStoreLikesRecord(LikesDto likesDto);

    void decreaseLikesWithId(LikesDto likesDto);

    void removeStoreLikesRecord(LikesDto likesDto);

    void uploadReviewImage(UploadReviewImageDto uploadReviewImageDto);

    void deleteReviewWithId(int reviewId);

    void deleteReviewImagesWithId(int reviewId);

    void saveCategoryMenu(Map<String, Object> map);

    void saveMenus(Map<String, Object> map);

    void saveStores(StoreRequestVo storeRequestVo);


    List<ResponseStoreListDto> findStoreListMenu(Map<String, String> map);
}