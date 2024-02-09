package com.org.makgol.stores.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.makgol.stores.dao.StoresDao;
import com.org.makgol.stores.dto.*;
import com.org.makgol.stores.repository.StoresRepository;
import com.org.makgol.stores.type.KakaoLocalResponseJSON;
import com.org.makgol.stores.vo.KakaoLocalRequestVo;
import com.org.makgol.stores.vo.StoreRequestMenuVo;
import com.org.makgol.stores.vo.StoreRequestVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import com.org.makgol.util.kakaoMap.KakaoMap;
import com.org.makgol.util.redis.CacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class StoreService {
    @Autowired
    private CacheService cacheService;

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final StoresRepository storesRepository;
    private final UsersRepository usersRepository;
    private final KakaoMap kakaoMapSearch;
    private final StoresDao storesDao;
    private final FileUpload fileUpload;

    /**
     * 업장 목록을 조회하는 메서드입니다.
     * 카테고리 및 위치 정보에 따라 일반 목록 또는 메뉴 포함 목록을 반환합니다.
     *
     * @param requestStoreListDto 업장 목록 조회에 필요한 정보를 담고 있는 객체
     * @return 업장 목록 또는 메뉴 포함 목록
     */
    public List<ResponseStoreListDto> findStoreListData(RequestStoreListDto requestStoreListDto) {
        // 캐시 키 생성
        String cacheKey = generateCacheKey(requestStoreListDto);

        // 캐시된 데이터 가져오기
        List<ResponseStoreListDto> cachedResult = (List<ResponseStoreListDto>) cacheService.getCachedQueryResult(cacheKey);

        if (cachedResult != null) {
            // 캐시된 데이터가 존재하는 경우
            return cachedResult;
        } else {
            // 캐시된 데이터가 없는 경우
            // 실제로 DB에서 데이터를 조회하고 결과를 캐시에 저장하는 로직 수행
            List<ResponseStoreListDto> result = null;
            try {
                Map<String, String> map = new HashMap<>();
                String keyword = requestStoreListDto.getKeyword();
                String longitude = requestStoreListDto.getLongitude();
                String latitude = requestStoreListDto.getLatitude();
                map.put("category", keyword);
                map.put("longitude", longitude);
                map.put("latitude", latitude);

                if("한식".equals(keyword)
                        || "양식".equals(keyword)
                        || "일식".equals(keyword)
                        || "분식".equals(keyword)
                        || "중식".equals(keyword)
                        || "기타".equals(keyword)){

                    log.info("category --> {} :", keyword);
                    log.info("longitude --> {} :", longitude);
                    log.info("latitude--> {} :", latitude);
                    log.info("result = storesRepository.findStoreList(map);");
                    result = storesRepository.findStoreList(map);

                } else {
                    log.info("category --> {} :", keyword);
                    log.info("longitude --> {} :", longitude);
                    log.info("latitude--> {} :", latitude);
                    log.info("result = storesRepository.findStoreListMenu(map);");
                    result = storesRepository.findStoreListMenu(map);
                }

                // 캐시 저장
                cacheService.cacheQueryResult(cacheKey, result, 10);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    private String generateCacheKey(RequestStoreListDto requestStoreListDto) {
        return "storeList:" + requestStoreListDto.getKeyword() + ":" + requestStoreListDto.getLongitude() + ":" + requestStoreListDto.getLatitude();
    }

    /**
     * 업장명을 기반으로 업장의 식별자를 조회하는 메서드입니다.
     *
     * @param name 조회할 업장명
     * @return 업장명에 해당하는 업장의 식별자
     */
    public String findStoreIdWithPlaceName(String name) {
        String id = "";
        try {
            id = storesRepository.findStoreIdWithPlaceName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 업장 식별자에 해당하는 상세 정보를 조회하는 메서드입니다.
     *
     * @param storeId 조회할 업장의 식별자
     * @return 업장 식별자에 해당하는 상세 정보
     */
    public StoreDetailDto findStoreDetailWithId(int storeId) {
        StoreDetailDto result = null;
        try {
            result = storesRepository.findStoreDetailWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 업장 식별자에 해당하는 메뉴 목록을 조회하는 메서드입니다.
     *
     * @param storeId 조회할 업장의 식별자
     * @return 업장 식별자에 해당하는 메뉴 목록
     */
    public List<StoreMenuDto> findStoreMenuWithId(int storeId) {
        List<StoreMenuDto> result = null;
        try {
            result = storesRepository.findStoreMenuWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 업장 식별자에 해당하는 리뷰 목록을 조회하는 메서드입니다.
     *
     * @param storeId 조회할 업장의 식별자
     * @return 업장 식별자에 해당하는 리뷰 목록
     */
    public List<StoreReviewDto> findStoreReviewWithId(int storeId) {
        List<StoreReviewDto> result = null;
        try {
            result = storesRepository.findStoreReviewWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 리뷰 식별자에 해당하는 리뷰 이미지 경로 목록을 조회하는 메서드입니다.
     *
     * @param reviewId 조회할 리뷰의 식별자
     * @return 리뷰 식별자에 해당하는 리뷰 이미지 경로 목록
     */
    public List<String> findStoreReviewImageWithId(int reviewId) {
        List<String> result = null;
        try {
            result = storesRepository.findStoreReviewImageWithId(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 사용자 식별자에 해당하는 사용자 정보 목록을 조회하는 메서드입니다.
     *
     * @param userId 조회할 사용자의 식별자
     * @return 사용자 식별자에 해당하는 사용자 정보 목록
     */
    public List<UserInfoDto> findUserInfoWithId(int userId) {
        List<UserInfoDto> result = null;
        try {
            result = storesRepository.findUserInfoWithId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 리뷰를 생성하는 메서드입니다.
     *
     * @param createReviewDto 생성할 리뷰 정보를 담고 있는 객체
     */
    public void createReview(CreateReviewDto createReviewDto) {
        // 파일 업로드를 통해 얻은 파일 정보 리스트
        List<FileInfo> fileInfoList = fileUpload.fileListUpload(createReviewDto.getReviewImages());

        try {
            // 리뷰 생성
            storesRepository.createReview(createReviewDto);

            // 새로 업로드된 이미지 정보 업데이트
            fileInfoList.forEach((fileInfo -> {
                UploadReviewImageDto uploadReviewImageDto = new UploadReviewImageDto(createReviewDto.getId(), fileInfo.getPhotoName(), fileInfo.getPhotoPath());
                storesRepository.uploadReviewImage(uploadReviewImageDto);
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 좋아요 상태를 확인하는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담고 있는 객체
     * @return 좋아요 상태를 나타내는 boolean 값 (true: 좋아요 가능, false: 이미 좋아요한 상태)
     */
    public boolean getLikesStatus(LikesDto likesDto) {
        try {
            StoreLikesDto result = storesRepository.getLikesStatus(likesDto);

            return result == null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 좋아요 수를 증가시키고 좋아요 기록을 생성하는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담고 있는 객체
     */
    public void increaseLikesWithId(LikesDto likesDto) {
        try {
            storesRepository.increaseLikesWithId(likesDto);
            storesRepository.createStoreLikesRecord(likesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 좋아요 수를 감소시키고 좋아요 기록을 삭제하는 메서드입니다.
     *
     * @param likesDto 좋아요 정보를 담고 있는 객체
     */
    public void decreaseLikesWithId(LikesDto likesDto) {
        try {
            storesRepository.decreaseLikesWithId(likesDto);
            storesRepository.removeStoreLikesRecord(likesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 리뷰를 수정하고 수정된 이미지 정보를 업데이트하는 메서드입니다.
     *
     * @param modifyReviewDto 수정할 리뷰 정보를 담고 있는 객체
     */
    public void modifyReviewWithId(ModifyReviewDto modifyReviewDto) {
        // 파일 업로드를 통해 얻은 파일 정보 리스트
        List<FileInfo> fileInfoList = fileUpload.fileListUpload(modifyReviewDto.getReviewImages());

        try {
            // 기존 리뷰 이미지 삭제
            storesRepository.deleteReviewImagesWithId(modifyReviewDto.getReviewId());

            // 리뷰 수정
            storesRepository.modifyReviewWithId(modifyReviewDto);

            // 새로 업로드된 이미지 정보 업데이트
            fileInfoList.forEach((fileInfo -> {
                UploadReviewImageDto uploadReviewImageDto = new UploadReviewImageDto(modifyReviewDto.getReviewId(), fileInfo.getPhotoName(), fileInfo.getPhotoPath());
                storesRepository.uploadReviewImage(uploadReviewImageDto);
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 리뷰를 삭제하는 메서드입니다.
     *
     * @param reviewId 삭제할 리뷰의 식별자
     */
    public void deleteReviewWithId(int reviewId) {
        try {
            storesRepository.deleteReviewWithId(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 카카오 로컬 API를 호출하여 가게 정보를 검색하는 메서드입니다.
     *
     * @param searchRequestVo 검색 요청 정보를 담고 있는 객체
     * @return 카카오 로컬 API 응답을 담은 객체
     */
    public KakaoLocalResponseJSON callKakaoLocalAPI(KakaoLocalRequestVo searchRequestVo) {
        String x = searchRequestVo.getX();
        String y = searchRequestVo.getY();
        String keyword = searchRequestVo.getKeyword();
        int radius = searchRequestVo.getRadius();
        int size = searchRequestVo.getSize();
        int page = searchRequestVo.getPage();

        // 카카오 로컬 API 호출을 위한 URI 생성
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://dapi.kakao.com/v2/local/search/keyword.json")
                .queryParam("y", y)
                .queryParam("x", x)
                .queryParam("query", keyword)
                .queryParam("radius", radius)
                .queryParam("size", size)
                .queryParam("page", page)
                .queryParam("category_group_code", "FD6")
                .build();

        // HTTP 요청 헤더 설정
        headers.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

        HttpEntity<String> request = new HttpEntity<>(headers);

        // 카카오 로컬 API 호출 및 응답 반환
        return restTemplate.exchange(uri.toString(), HttpMethod.GET, request, KakaoLocalResponseJSON.class).getBody();
    }

    /**
     * 가게 정보를 크롤링하여 메뉴를 가져오는 메서드입니다.
     *
     * @param storeRequestVoList 크롤링할 가게 정보 목록
     * @throws JsonProcessingException JSON 처리 예외가 발생할 경우
     */
    public void getMenu(List<StoreRequestVo> storeRequestVoList) throws JsonProcessingException {
        // 외부 API에 전송할 URL 설정
        String url = "http://www.makgol.com" + "/api/v1/crawl/kakaoStoreCrwall";

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 본문에 List<StoreRequestVo> 객체 추가
        HttpEntity<List<StoreRequestVo>> request = new HttpEntity<>(storeRequestVoList, headers);

        // 외부 API로 HTTP POST 요청 전송
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<HashMap<String, Object>>() {
                }
        );
    }



    // 응답 처리
//		if (resStoreMap.getStatusCode() == HttpStatus.OK) {
//			for (int index = 0; index < storeMap.size()/2; index++) {
//		    	System.out.println("====================="+index+"=====================");
//
//		        System.err.println("-----------------" + "No." + (index + 1) + " store_info------------------------");
//		        System.out.println("식당 이름  -->  :" + storeRequestVo.getName());
//		        System.out.println("식당 주소 -->  :" + storeRequestVo.getAddress());
//		        System.out.println("식당 카테고리 -->  :" + storeRequestVo.getCategory());
//		        System.out.println("식당 좋아요 -->  :" + storeRequestVo.getLikes());
//		        System.out.println("식당 도로명주소  -->  :" + storeRequestVo.getLoad_address());
//		        System.out.println("식당 경도  -->  :" + storeRequestVo.getLongitude());
//		        System.out.println("식당 위도  -->  :" + storeRequestVo.getLatitude());
//		        System.out.println("식당 전화번호  -->  :" + storeRequestVo.getPhone());
//		        System.out.println("식당 상세페이지  -->  :" + storeRequestVo.getPlace_url());
//		        System.out.println("식당 운영시간  -->  :" + storeRequestVo.getOpening_hours());
//		        System.out.println("식당 사이트  -->  :" + storeRequestVo.getSite());
//		        System.out.println("식당 업데이트 날짜  -->  :" + storeRequestVo.getUpdate_date());
//		        System.out.println("식당 메뉴 업데이트 날짜  -->  :" + storeRequestVo.getMenu_update());
//
//		        for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
//		            System.out.println("메뉴 :  " + storeRequestMenuVoList.get(menuIndex).getMenu() + ",   가격  :  " + storeRequestMenuVoList.get(menuIndex).getPrice());
//		        }
//		    }
//		} else {
//		    System.out.println("서버 응답 오류: " + resStoreMap.getStatusCode());
//		}


    /**
     * 사용자 이메일을 기반으로 Kakao Map에서 가게 정보를 검색하고 저장하는 프로세스를 수행하는 메서드입니다.
     *
     * @param email 사용자 이메일
     * @return 저장 프로세스가 성공하면 true, 그렇지 않으면 false 반환
     */
    public boolean saveStoresProcess(String email) {
        log.info("saveStoresProcess");

        // 사용자 정보 조회
        UsersResponseVo usersResponseVo = usersRepository.findUserByEmail(email);

        // Kakao Map에서 가게 정보 검색
        List<StoreRequestVo> storeRequestVoList = kakaoMapSearch.storeInfoSearch(usersResponseVo);

        try {
            // 업장 중복 체크
            log.info("before storeRequestVoList --> : {}", storeRequestVoList.size());
            log.info("duplication count --> : {} ", storesDao.checkStore(storeRequestVoList));
            log.info("after storeRequestVoList --> : {}", storeRequestVoList.size());

            // 가게 정보 요청 및 저장
            HashMap<String, Object> storeMap = kakaoMapSearch.storeInfoRequest(storeRequestVoList);
            StoreResponseVo storeResponseVo;

            for (int index = 0; index < storeMap.size() / 2; index++) {
                System.out.println("------" + index + "-------");

                StoreRequestVo storeRequestVo = (StoreRequestVo) storeMap.get("store_info_" + index);
                List<StoreRequestMenuVo> storeRequestMenuVoList = (List<StoreRequestMenuVo>) storeMap.get("store_menu_" + index);

                // store에 정보가 없을 경우
                if (storeRequestVo == null) {
                    System.out.println("storeRequestVo == null");
                    continue;
                }

                // 이미 존재하는 가게인 경우
                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());
                if (storeResponseVo != null) {
                    log.info("이미 존재함. storeRequestVo.getPlace_url() --> : {} ", storeRequestVo.getPlace_url());

                    // 가게에 카테고리 및 메뉴 정보 저장
                    if (!storeRequestVo.getMenuName().equals("empty")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("store_id", storeResponseVo.getId());
                        map.put("category", storeRequestVo.getCategory());
                        map.put("menu_name", storeRequestVo.getKeyword());

                        storesRepository.saveCategoryMenu(map);
                    }

                    for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                        if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                            continue;
                        }

                        Map<String, Object> map = new HashMap<>();
                        map.put("store_id", storeResponseVo.getId());
                        map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                        map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                        storesRepository.saveMenus(map);
                    }
                    continue;
                }

                // 가게 정보 저장
                storesRepository.saveStores(storeRequestVo);
                log.info("insert storeInfo --> : {}", storeRequestVo.getPlace_url());

                // 저장된 가게 정보 조회
                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());

                // 가게에 카테고리 및 메뉴 정보 저장
                if (!storeRequestVo.getMenuName().equals("empty")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("category", storeRequestVo.getCategory());
                    map.put("menu_name", storeRequestVo.getKeyword());
                    storesRepository.saveCategoryMenu(map);
                }

                for (int menuIndex = 0; menuIndex < storeRequestMenuVoList.size(); menuIndex++) {
                    if (storeRequestMenuVoList.get(menuIndex).getMenu() == null) {
                        continue;
                    }

                    Map<String, Object> map = new HashMap<>();
                    map.put("store_id", storeResponseVo.getId());
                    map.put("menu", storeRequestMenuVoList.get(menuIndex).getMenu());
                    map.put("price", storeRequestMenuVoList.get(menuIndex).getPrice());
                    storesRepository.saveMenus(map);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
