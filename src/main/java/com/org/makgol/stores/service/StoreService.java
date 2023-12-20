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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final StoresRepository storesRepository;
    private final UsersRepository usersRepository;
    private final KakaoMap kakaoMapSearch;
    private final StoresDao storesDao;
    private final FileUpload fileUpload;

    public List<ResponseStoreListDto> findStoreListData(RequestStoreListDto requestStoreListDto) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String findStoreIdWithPlaceName(String name) {
        String id = "";
        try {
            id = storesRepository.findStoreIdWithPlaceName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public StoreDetailDto findStoreDetailWithId(int storeId) {
        StoreDetailDto result = null;
        try {
            result = storesRepository.findStoreDetailWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<StoreMenuDto> findStoreMenuWithId(int storeId) {
        List<StoreMenuDto> result = null;
        try {
            result = storesRepository.findStoreMenuWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<StoreReviewDto> findStoreReviewWithId(int storeId) {
        List<StoreReviewDto> result = null;
        try {
            result = storesRepository.findStoreReviewWithId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> findStoreReviewImageWithId(int reviewId) {
        List<String> result = null;
        try {
            result = storesRepository.findStoreReviewImageWithId(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<UserInfoDto> findUserInfoWithId(int userId) {
        List<UserInfoDto> result = null;
        try {
            result = storesRepository.findUserInfoWithId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createReview(CreateReviewDto createReviewDto) {
        List<FileInfo> fileInfoList = fileUpload.fileListUpload(createReviewDto.getReviewImages());
        try {
            storesRepository.createReview(createReviewDto);
            fileInfoList.forEach((fileInfo -> {
                UploadReviewImageDto uploadReviewImageDto = new UploadReviewImageDto(createReviewDto.getId(), fileInfo.getPhotoName(), fileInfo.getPhotoPath());
                storesRepository.uploadReviewImage(uploadReviewImageDto);
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getLikesStatus(LikesDto likesDto) {
        try {
            StoreLikesDto result = storesRepository.getLikesStatus(likesDto);

            return result == null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void increaseLikesWithId(LikesDto likesDto) {
        try {
            storesRepository.increaseLikesWithId(likesDto);
            storesRepository.createStoreLikesRecord(likesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decreaseLikesWithId(LikesDto likesDto) {
        try {
            storesRepository.decreaseLikesWithId(likesDto);
            storesRepository.removeStoreLikesRecord(likesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyReviewWithId(ModifyReviewDto modifyReviewDto) {
        try {
            storesRepository.modifyReviewWithId(modifyReviewDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReviewWithId(int reviewId) {
        try {
            storesRepository.deleteReviewWithId(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KakaoLocalResponseJSON callKakaoLocalAPI(KakaoLocalRequestVo searchRequestVo) {
        String x = searchRequestVo.getX();
        String y = searchRequestVo.getY();
        String keyword = searchRequestVo.getKeyword();
        int radius = searchRequestVo.getRadius();
        int size = searchRequestVo.getSize();
        int page = searchRequestVo.getPage();

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

        headers.set("Authorization", "KakaoAK e2a97497252d13a304751d99a85ea67c");

        HttpEntity<String> request = new HttpEntity<>(headers);


        return restTemplate.exchange(uri.toString(), HttpMethod.GET, request, KakaoLocalResponseJSON.class).getBody();
    }


    public void getMenu(List<StoreRequestVo> storeRequestVoList) throws JsonProcessingException {


        RestTemplate restTemplate = new RestTemplate();
        String url = "http://3.35.48.76"+"/api/v1/crawl/kakaoStoreCrwall";

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 본문에 List<StoreRequestVo> 객체 추가
        HttpEntity<List<StoreRequestVo>> request = new HttpEntity<>(storeRequestVoList, headers);

        // 서버로 HTTP GET 요청 보내기
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<HashMap<String, Object>>() {
                }
        );


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
    }


    public boolean saveStoresProcess(String email) {
        log.info("saveStoresProcess");
        UsersResponseVo usersResponseVo = usersRepository.findUserByEmail(email);
        List<StoreRequestVo> storeRequestVoList = kakaoMapSearch.storeInfoSearch(usersResponseVo);

        try {
            //업장 중복 체크
            log.info("before storeRequestVoList --> : {}", storeRequestVoList.size());
            log.info("duplication count --> : {} ", storesDao.checkStore(storeRequestVoList));
            log.info("after storeRequestVoList --> : {}", storeRequestVoList.size());

            HashMap<String, Object> storeMap = kakaoMapSearch.storeInfoRequest(storeRequestVoList);

            //store 업장 데이터 넣기
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

                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());


                if (storeResponseVo != null) {
                    log.info("이미 존제 함. storeRequestVo.getPlace_url() --> : {} ", storeRequestVo.getPlace_url());

                    storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());

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

                storesRepository.saveStores(storeRequestVo);
                log.info("insert storeInfo --> : {}", storeRequestVo.getPlace_url());

                storeResponseVo = storesRepository.findByIdPlaceUrl(storeRequestVo.getPlace_url());


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
