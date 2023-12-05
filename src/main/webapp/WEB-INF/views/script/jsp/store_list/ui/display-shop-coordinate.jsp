<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayShopsCoordinate = (map, shops) => {
        const {kakao} = window;

        shops.forEach((shop) => {
            const shopX = shop.x;
            const shopY = shop.y;

            const markerPosition = new kakao.maps.LatLng(shopY, shopX);

            const marker = new kakao.maps.Marker({position: markerPosition});
            marker.setMap(map);
        });
    };

</script>