<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const removeSelectedShop = (shop) => {
        const initialedKakaomap = hashMap.get("kakaomap");
        const shopCoordinate = new kakao.maps.LatLng(shop.latitude, shop.longitude);

        const existingMarker = new kakao.maps.Marker({
            position: shopCoordinate,
            map: initialedKakaomap
        });

        const imageSrc = "http://localhost:8090/resources/static/image/default/sky_blue_ping.png"
        const imageSize = new kakao.maps.Size(24, 36);

        const newMarkerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
        );

        existingMarker.setImage(newMarkerImage);
    }
</script>