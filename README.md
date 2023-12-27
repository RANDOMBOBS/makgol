![배너]()
Project Timeline
`2022-06-24` ~  `2022-08-01` (6주)
<br>
<br>
# 막내야골라봐! (makgol)

<br>



### “‘👴:막내야 아무거나 골라봐~’ 오늘도 피할 수 없는 회사에서 최대 고민,  점심 메뉴 선택❗”
![막내야골라봐](https://github.com/RANDOMBOBS/makgol/assets/105048235/a4349240-6d65-4e58-b27a-9045fcb96755)
<br>

아무거나 먹자고 말씀하시면서 절대 아무거나 먹지 않는 상사**👴**에게 매일 고통 받고 있는

**모든 회사의 막내들 더 이상 고통 받지 마세요**❗

**막내야 골라봐** 가 맞춤 메뉴 추천으로 메뉴 고민 해결해 드립니다❗

점심 뭐 먹지? 메뉴 선정 고민 해결사❗ **막내야 골라봐** 입니다.

<br>
<br>

👩‍🦼 [막내야골라봐 바로가기](http://www.makgol.com/)

<br>

![Line 1](https://user-images.githubusercontent.com/90380269/181489532-4bbb5041-8de1-4ac9-89b2-9400e577ddd2.png)

<br>

## 막내야골라봐 (makgol)의 특징
### 1.점심식사 선택의 고민을 해결
 - 사용자의 **현재 위치**를 기반으로 주변 음식점을 **지도** 상에서 시각적으로 보여주어, 식사 선택에 대한 **고민을 빠르게 해결**할 수 있습니다.
### 2.다양한 필터 및 정렬 기능
 - 사용자는 음식 카테고리, 가격대, 평점 등, 다양한 기준을 활용하여 원하는 조건에 맞게 음식점을 필터링하고 정렬할 수 있습니다. 이를 통해 사용자의 취향과 상황에 맞게 최적의 선택이 가능합니다.
### 3.사용자 ~~편의성 강화 
 - 직관적이고~~ 사용자 친화적인 인터페이스를 통해, 신속하게 원하는 정보를 찾고 선택할 수 있도록 설계되었습니다.
### 4.리뷰 및 평가 시스템
 - 다른 사용자들의 리뷰와 평가를 확인하여 음식점의 품질을 미리 파악할 수 있습니다. 이를 통해 사용자들은 신뢰성 있는 정보를 기반으로 식사장소를 선택할 수 있습니다.

<br>

# Features

### 🏘 돌림판으로 내 고민 해결 
    
- 사용자가 **돌림판**을 돌리면서 점심메뉴의 범위를 좁힐수 있습니다.

<br>

### 🏘 실시간 날씨에 맞는 메뉴 추천

- 사용자가 입력한 주소의 **날씨**를 기반으로 날씨에 어울리는 메뉴를 추천받을 수 있습니다.


<br>

### 🏘 내 주변에 있는 식당
    
- 사용자의 **현재 위치**를 기반으로 식당의 위치를 **지도**로 한 눈에 확인할 수 있습니다.
    
<br>

### 🏘 식당의 상세 정보
    
- 해상 식당의 상세정보 페이지로 영업시간과 해당 식당의 **메뉴 또는 가격**을 확인 가능합니다.

<br>  

### 🔖 가고 싶은 가게를 좋아요!
    
- 기억하고 싶은 가게를 **좋아요** 할 수 있습니다.
    
<br>

### 🤔 다른 사람들의 리뷰는 어떻까?
    
- 해당식당의 **리뷰봐 별점**을 확인 가능합니다.
    
<br>    

### ☑️ 내가 직장에서의 겪은 경험을 들어줘!
    
- 직장에서 겪었던 경험을 공유하는 **하소연 커뮤니티**가 존재하며 **건의 게시판, 공지사항이** 존재합니다.
    
<br>    

    
<br>
<br>

![Line 1](https://user-images.githubusercontent.com/90380269/181489532-4bbb5041-8de1-4ac9-89b2-9400e577ddd2.png)

# Project


<br>

## Tech Stack

### Language

- JAVA, javascript


### Database
- MySQL

### Deploy

- **Github Actions** : CI/CD
- **AWS Code Deploy** : CI/CD
- **Nginx** : 무중단 배포
- AWS EC2
- AWS S3
- AWS RDS

### Tech

- Spring Boot
- **Spring Security** : 보안 설정을 위해 사용
- **JWT** : 사용자 인증/인가를 위해 사용
- **cors_token, check referer** : cors 대응을 위해 사용
- **Xss filter** : Xss 대응을 위해 사용
- **OAuth2.0** : 소셜 로그인을 위해 사용
- **Spring Batch** : 대용량 데이터 처리를 위해 사용
- **mybatis**
- **Selenium** : 상세 데이터를 수집하기 위해 사용
- **Thread** : 크롤링을 병렬처리하기 위해 사용
- **Redis**
- Swagger
- cookie
- **RestTemplate** : Public API와 Kakao API를 호출하기 위해 사용

### Data Pipeline
- Public API 
- Kakao Maps API


<br>

## Project Design

### Service Architecture
<img width="2228" alt="randombobs Architecture" src="https://github.com/RANDOMBOBS/makgol/assets/141618648/afd7b6be-e746-44a7-ad68-cea574528882">

<br>

### ERD
<img width="2228" alt="randombobs Architecture" src="https://github.com/RANDOMBOBS/makgol/assets/105048235/e86b416f-777f-479b-ad8b-ea3135b90ad0">


<br>

![Line 1](https://user-images.githubusercontent.com/90380269/181489532-4bbb5041-8de1-4ac9-89b2-9400e577ddd2.png)

# Team Randombobs

<br>

랜덤밥스 팀 소개!!

| [김한울🔰](https://github.com/Anna-Jin) | [김효진](https://github.com/kokoa322) | [이승훈](https://github.com/Idooru) | [김선현](https://github.com/kokoa322) | [김성희](https://github.com/JSoi) |                                                                                                            
| :---------------------------------: | :----------------------------------: | :-----------------------------: | :----------------------------------: | :-----------------------------: |
| <img src="https://github.com/RANDOMBOBS/makgol/assets/105048235/0ded6f53-9063-4808-a165-669b990d420e" alt="김한울" width="200px"/> |  <img src="https://github.com/RANDOMBOBS/makgol/assets/105048235/0f462196-5701-4575-8b8f-974610c268aa" alt="김효진" width="200px"/> | <img src=https://github.com/RANDOMBOBS/makgol/assets/105048235/7ec262f5-a626-4d8a-88a4-0fecccdf2627 alt="이승훈" width="200px" /> | <img src="https://github.com/RANDOMBOBS/.readme/assets/105048235/89123810-53a2-4865-926b-598bac0fb825" alt="김선현" width="150px" /> | <img src="https://github.com/RANDOMBOBS/makgol/assets/105048235/45edd2e8-c191-460b-bdf6-46fa0d06c964" alt="김성희" width="200px" /> |
| `Public API 미세먼지 정보` <br> `공지사항 게시판` <br> `랭킹별 업장, 식당 추천` <br> `카테코리 페이지` |  <br>`Public API 날씨정보 호출` <br> `로그인, 로그아웃`<br>`쿠키활용 로그인 유지`<br>`건의사항 페이지` <br> `마이페이지` <br> `관리자용 페이지` <br> `돌림판` | `Kakao Map API` <br> `업장리스트 페이지` <br> `상세페이지` <br> `회원가입 로그인 아이디 비밀번호 찾기 모달` | `데이터 수집, 가공` <br> `로그인, 로그아웃` <br>`회원가입, PW찾기`<br>`JWT`<br> `Spring security` <br> `oauth2 소셜로그인` | `공룡게임`<br>`사다리게임` <br>`하소연 게시판` <br>  |
|조정석(아이디어뱅크!)|김성실(최고의팀원!)|기술반장<br>(무엇이든 물어보세요!)|캡틴(길라잡이!)|열정맨(할수있어!)|
