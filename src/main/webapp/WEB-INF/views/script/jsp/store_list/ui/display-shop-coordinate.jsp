<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayShopsCoordinate = (map, shops) => {
        const {kakao} = window;
        const imageSrc = "http://localhost:8090/resources/static/image/default/sky_blue_ping.png";
        const imageSize = new kakao.maps.Size(24, 36);

        const markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
        )

        shops.forEach((shop) => {
            const {longitude, latitude} = shop;

            const markerPosition = new kakao.maps.LatLng(latitude, longitude);

            const marker = new kakao.maps.Marker({
                position: markerPosition,
                image: markerImage
            });

            marker.setMap(map);
        });
    };

</script>