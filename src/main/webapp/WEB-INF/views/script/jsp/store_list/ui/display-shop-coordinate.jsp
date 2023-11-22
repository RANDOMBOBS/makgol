<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayShopsCoordinate = (map, shops) => {
        const {kakao} = window;

        shops.forEach((shop) => {
            const {shopX, shopY} = shop;

            const markerPosition = new kakao.maps.LatLng(shopY, shopX);

            const marker = new kakao.maps.Marker({position: markerPosition});
            marker.setMap(map);

            const iwContent = "<div class='marker'>" + shop.place_name + "</div>";

            const infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
            });

            kakao.maps.event.addListener(marker, 'mouseover', function () {
                infowindow.open(map, marker);
            });

            kakao.maps.event.addListener(marker, "mouseout", function () {
                infowindow.close();
            })
        });
    };

</script>