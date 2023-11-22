<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="../util/create-url-for-detail-page.jsp"></jsp:include>
<script>
    const displayInitialShopList = ({shops, keyword}) => {
        const searchKeywordEle = $("#search_keyword");
        searchKeywordEle.text(keyword);

        shops.forEach((shop) => {
            const shopInfoListEle = $(".shop_info_list");
            const shopInfoItemEle = $("<div>").addClass("shop_info_item");

            const topItemEle = $("<div>").addClass("top_item");
            const placeNameEle = $("<a>")
                .attr("id", "place_name")
                .text(shop.place_name)
            const likeItemEle = $("<p>").attr("id", "likes").text("♥" + shop.likes)

            const topItemComposition = [placeNameEle, likeItemEle];
            topItemComposition.forEach((composition) => topItemEle.append(composition));

            const middleItemEle = $("<div>").addClass("middle_item");

            const addressNameEle = $("<p>").attr("id", "address_name").text(shop.address_name);
            const distanceEle = $("<p>").attr("id", "distance").text(shop.distance + "m");

            const middleItemComposition = [addressNameEle, distanceEle];
            middleItemComposition.forEach((composition) =>
                middleItemEle.append(composition),
            );

            const underItemEle = $("<div>").addClass("under_item");

            const phoneEle = $("<p>").attr("id", "phone").text(shop.phone);

            const detailEle = $("<a>")
                .attr("id", "detail")
                .text("상세 페이지")

            const url = createUrlForDetailPage(shop);

            placeNameEle.attr("href", url);
            detailEle.attr("href", url);

            const underItemComposition = [phoneEle, detailEle];
            underItemComposition.forEach((composition) =>
                underItemEle.append(composition),
            );

            const shopInfoComposition = [topItemEle, middleItemEle, underItemEle];

            shopInfoItemEle.mouseover(() => {
                const {kakao} = window;

                const container = $("#map")[0];
                const options = {
                    center: new kakao.maps.LatLng(shop.shopY, shop.shopX),
                    level: 4,
                };

                const map = new kakao.maps.Map(container, options);
                const myCoordinate = JSON.parse(localStorage.getItem("myCoordinate"));
                displayMyCoordinate(map, myCoordinate);
                displayShopsCoordinate(map, shops);

                const displaySelectedShopCoordinate = () => {
                    const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
                    const imageSize = new kakao.maps.Size(34, 38);
                    const imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

                    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

                    const markerPosition = new kakao.maps.LatLng(shop.shopY, shop.shopX); // 마커가 표시될 위치입니다

                    // 마커를 생성합니다
                    const marker = new kakao.maps.Marker({
                        position: markerPosition, image: markerImage, // 마커이미지 설정
                    });

                    // 마커가 지도 위에 표시되도록 설정합니다
                    marker.setMap(map);
                }
                
                displaySelectedShopCoordinate();
            })

            shopInfoComposition.forEach((composition) =>
                shopInfoItemEle.append(composition),
            );

            shopInfoListEle.append(shopInfoItemEle);
        });
    }
</script>