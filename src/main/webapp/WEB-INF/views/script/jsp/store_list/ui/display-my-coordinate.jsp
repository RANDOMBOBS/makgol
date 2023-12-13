<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayMyCoordinate = (map, {longitude, latitude}) => {
        const {kakao} = window;
        const imageSrc = "/resources/static/image/default/red_ping.png"; // 마커이미지의 주소입니다
        const imageSize = new kakao.maps.Size(30, 47); // 마커이미지의 크기입니다

        const markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
        );

        const markerPosition = new kakao.maps.LatLng(latitude, longitude); // 마커가 표시될 위치입니다

        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
            position: markerPosition,
            image: markerImage, // 마커이미지 설정
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
    };

</script>
