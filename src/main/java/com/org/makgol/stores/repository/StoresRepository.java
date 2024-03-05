package com.org.makgol.stores.repository;

import com.org.makgol.stores.dto.*;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@CacheNamespace(flushInterval = 100000, size = 1024)
public interface StoresRepository {
    /**
     * place_url을 사용하여 가게 정보를 조회하는 메서드입니다.
     *
     * @param place_url 조회할 가게의 place_url
     * @return 조회된 가게 정보를 담은 StoreResponseVo 객체
     */
    StoreResponseVo findByIdPlaceUrl(String place_url);

    /**
     * 매개변수로 전달된 조건에 따라 가게 목록을 조회하는 메서드입니다.
     *
     * @param map 가게 목록 조회 조건을 담은 Map 객체
     * @return 조회된 가게 목록을 담은 ResponseStoreListDto 리스트
     */
    List<ResponseStoreListDto> findStoreList(Map<String, String> map);

    /**
     * placeName을 사용하여 가게의 ID를 조회하는 메서드입니다.
     *
     * @param placeName 조회할 가게의 placeName
     * @return 조회된 가게의 ID
     */
    String findStoreIdWithPlaceName(String placeName);

    /**
     * 가게 ID를 사용하여 가게의 세부 정보를 조회하는 메서드입니다.
     *
     * @param storeId 조회할 가게의 ID
     * @return 조회된 가게의 세부 정보를 담은 StoreDetailDto 객체
     */
    StoreDetailDto findStoreDetailWithId(int storeId);

    /**
     * 가게 ID를 사용하여 가게의 메뉴 정보를 조회하는 메서드입니다.
     *
     * @param storeId 조회할 가게의 ID
     * @return 조회된 가게의 메뉴 정보를 담은 StoreMenuDto 리스트
     */
    List<StoreMenuDto> findStoreMenuWithId(int storeId);

    /**
     * 가게 ID를 사용하여 가게의 리뷰 정보를 조회하는 메서드입니다.
     *
     * @param storeId 조회할 가게의 ID
     * @return 조회된 가게의 리뷰 정보를 담은 StoreReviewDto 리스트
     */
    List<StoreReviewDto> findStoreReviewWithId(int storeId);

    /**
     * 리뷰 ID를 사용하여 리뷰의 이미지 경로를 조회하는 메서드입니다.
     *
     * @param reviewId 조회할 리뷰의 ID
     * @return 조회된 리뷰의 이미지 경로를 담은 리스트
     */
    List<String> findStoreReviewImageWithId(int reviewId);

    /**
     * 사용자 ID를 사용하여 사용자 정보를 조회하는 메서드입니다.
     *
     * @param userId 조회할 사용자의 ID
     * @return 조회된 사용자 정보를 담은 UserInfoDto 리스트
     */
    List<UserInfoDto> findUserInfoWithId(int userId);

    /**
     * 새로운 리뷰를 생성하는 메서드입니다.
     *
     * @param createReviewDto 생성할 리뷰 정보를 담은 CreateReviewDto 객체
     */
    void createReview(CreateReviewDto createReviewDto);

    /**
     * 특정 리뷰를 수정하는 메서드입니다.
     *
     * @param modifyReviewDto 수정할 리뷰 정보를 담은 ModifyReviewDto 객체
     */
    void modifyReviewWithId(ModifyReviewDto modifyReviewDto);

    /**
     * 좋아요 상태를 조회하는 메서드입니다.
     *
     * @param likesDto 조회할 좋아요 정보를 담은 LikesDto 객체
     * @return 조회된 좋아요 상태를 담은 StoreLikesDto 객체
     */
    StoreLikesDto getLikesStatus(LikesDto likesDto);

    /**
     * 특정 가게에 대한 좋아요 수를 증가시키는 메서드입니다.
     *
     * @param likesDto 좋아요를 증가시킬 가게의 정보를 담은 LikesDto 객체
     */
    void increaseLikesWithId(LikesDto likesDto);

    /**
     * 좋아요를 기록하는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담은 LikesDto 객체
     */
    void createStoreLikesRecord(LikesDto likesDto);

    /**
     * 특정 가게에 대한 좋아요 수를 감소시키는 메서드입니다.
     *
     * @param likesDto 좋아요를 감소시킬 가게의 정보를 담은 LikesDto 객체
     */
    void decreaseLikesWithId(LikesDto likesDto);

    /**
     * 좋아요 기록을 제거하는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담은 LikesDto 객체
     */
    void removeStoreLikesRecord(LikesDto likesDto);

    /**
     * 리뷰 이미지를 업로드하는 메서드입니다.
     *
     * @param uploadReviewImageDto 업로드할 리뷰 이미지 정보를 담은 UploadReviewImageDto 객체
     */
    /**
     * 리뷰 이미지를 업로드하는 메서드입니다.
     *
     * @param uploadReviewImageDto 업로드할 리뷰 이미지 정보를 담은 UploadReviewImageDto 객체
     */
    void uploadReviewImage(UploadReviewImageDto uploadReviewImageDto);

    /**
     * 특정 리뷰를 삭제하는 메서드입니다.
     *
     * @param reviewId 삭제할 리뷰의 ID
     */
    void deleteReviewWithId(int reviewId);

    /**
     * 특정 리뷰에 속한 이미지들을 삭제하는 메서드입니다.
     *
     * @param reviewId 삭제할 리뷰의 ID
     */
    void deleteReviewImagesWithId(int reviewId);

    /**
     * 카테고리와 메뉴를 저장하는 메서드입니다.
     *
     * @param map 카테고리와 메뉴 정보를 담은 Map 객체
     */
    void saveCategoryMenu(Map<String, Object> map);

    /**
     * 메뉴 정보를 저장하는 메서드입니다.
     *
     * @param map 메뉴 정보를 담은 Map 객체
     */
    void saveMenus(Map<String, Object> map);

    /**
     * 가게 정보를 저장하는 메서드입니다.
     *
     * @param storeRequestVo 저장할 가게 정보를 담은 StoreRequestVo 객체
     */
    void saveStores(StoreRequestVo storeRequestVo);

    /**
     * 특정 조건에 따라 가게 목록을 조회하고, 메뉴 정보를 포함하여 반환하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 조회된 가게 목록과 메뉴 정보를 담은 ResponseStoreListDto 리스트
     */
    List<ResponseStoreListDto> findStoreListMenu(Map<String, String> map);
}