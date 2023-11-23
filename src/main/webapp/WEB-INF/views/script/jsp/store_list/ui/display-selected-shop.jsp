<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displaySelectedShop = (shop, shops) => {
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
    }
</script>