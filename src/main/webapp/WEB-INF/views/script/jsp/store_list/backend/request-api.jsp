<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const requestApi = async (payload) => {
        const {keyword, longitude, latitude, page} = payload

        const url = "http://localhost:8090/store/list_data?longitude=" + longitude + "&latitude=" + latitude + "&keyword=" + keyword + "&page=" + page;

        $.ajax({
            type: "GET",
            url,
            contentType: "application/json",
            success(data) {
                console.log("성공")
                console.log(data);
            }, error(err) {
                console.log("에러");
                console.error(err);
            }
        })

        const data = {
            "success": true,
            "message": "성공입니다.",
            "result": {
                "keyword": "한식",
                "documents": [
                    {
                        "address_name": "서울 강남구 역삼동 820-1",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 육류,고기 > 닭요리",
                        "distance": "72",
                        "id": "95713992",
                        "phone": "02-3452-3441",
                        "place_name": "장인닭갈비 강남점",
                        "place_url": "http://place.map.kakao.com/95713992",
                        "road_address_name": "서울 강남구 테헤란로1길 19",
                        "x": "127.027534918874",
                        "y": "37.4996136518153"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 819-4",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 찌개,전골",
                        "distance": "94",
                        "id": "1428481536",
                        "phone": "02-557-2662",
                        "place_name": "강남진해장",
                        "place_url": "http://place.map.kakao.com/1428481536",
                        "road_address_name": "서울 강남구 테헤란로5길 11",
                        "x": "127.02930814350843",
                        "y": "37.499584393000326"
                    },
                    {
                        "address_name": "서울 서초구 서초동 1316-29",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 한정식",
                        "distance": "313",
                        "id": "22716674",
                        "phone": "02-534-5300",
                        "place_name": "노랑저고리 강남점",
                        "place_url": "http://place.map.kakao.com/22716674",
                        "road_address_name": "서울 서초구 서초대로73길 9",
                        "x": "127.0253484614826",
                        "y": "37.49829326602763"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 619-5",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 육류,고기 > 곱창,막창",
                        "distance": "242",
                        "id": "168079537",
                        "phone": "02-556-2640",
                        "place_name": "60년전통신촌황소곱창 강남직영점",
                        "place_url": "http://place.map.kakao.com/168079537",
                        "road_address_name": "서울 강남구 강남대로100길 13",
                        "x": "127.027169317441",
                        "y": "37.501841023268"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 817-26",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식",
                        "distance": "86",
                        "id": "26431943",
                        "phone": "02-568-8592",
                        "place_name": "백억하누 강남본점",
                        "place_url": "http://place.map.kakao.com/26431943",
                        "road_address_name": "서울 강남구 테헤란로5길 29",
                        "x": "127.028815434254",
                        "y": "37.5005179565184"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 823-35",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 감자탕",
                        "distance": "462",
                        "id": "7983060",
                        "phone": "",
                        "place_name": "신동궁감자탕 역삼직영점",
                        "place_url": "http://place.map.kakao.com/7983060",
                        "road_address_name": "서울 강남구 테헤란로10길 21",
                        "x": "127.03290815160956",
                        "y": "37.49788594917416"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 823-12",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 육류,고기 > 삼겹살",
                        "distance": "395",
                        "id": "2011092566",
                        "phone": "1670-3592",
                        "place_name": "두껍삼 역삼직영점",
                        "place_url": "http://place.map.kakao.com/2011092566",
                        "road_address_name": "서울 강남구 테헤란로8길 17",
                        "x": "127.0320000503937",
                        "y": "37.49784565335923"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 826-20",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 육류,고기",
                        "distance": "518",
                        "id": "1503746075",
                        "phone": "02-508-0043",
                        "place_name": "창고43 강남점",
                        "place_url": "http://place.map.kakao.com/1503746075",
                        "road_address_name": "서울 강남구 강남대로 362",
                        "x": "127.02931097525367",
                        "y": "37.4952559571223"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 823-10",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 육류,고기",
                        "distance": "382",
                        "id": "741391811",
                        "phone": "02-3452-6373",
                        "place_name": "육전식당 4호점",
                        "place_url": "http://place.map.kakao.com/741391811",
                        "road_address_name": "서울 강남구 테헤란로8길 11-4",
                        "x": "127.03198091216792",
                        "y": "37.49804838498247"
                    },
                    {
                        "address_name": "서울 강남구 역삼동 830-9",
                        "category_group_code": "FD6",
                        "category_group_name": "음식점",
                        "category_name": "음식점 > 한식 > 순대",
                        "distance": "617",
                        "id": "16421356",
                        "phone": "02-501-2772",
                        "place_name": "농민백암순대 강남직영점",
                        "place_url": "http://place.map.kakao.com/16421356",
                        "road_address_name": "서울 강남구 역삼로3길 20-4",
                        "x": "127.03150463098274",
                        "y": "37.49491300989233"
                    }
                ]
            }
        }
        // console.log(data)
        return data;
    }

</script>