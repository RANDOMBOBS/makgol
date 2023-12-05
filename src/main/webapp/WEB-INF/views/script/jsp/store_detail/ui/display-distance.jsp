<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayDistance = (map, {myX, myY, shopX, shopY, middleX, middleY},distance) => {
        const {kakao} = window;
        const linePath = [
            new kakao.maps.LatLng(myY, myX),
            new kakao.maps.LatLng(shopY, shopX)
        ]

        const polyline = new kakao.maps.Polyline({
            path: linePath, // 선을 구성하는 좌표배열 입니다
            strokeWeight: 3, // 선의 두께 입니다
            strokeColor: '#FFAE00', // 선의 색깔입니다
            strokeOpacity: 0.5, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'dashed' // 선의 스타일입니다
        });

// 지도에 선을 표시합니다
        polyline.setMap(map);

        const center = new kakao.maps.LatLng(middleY, middleX);

        const overlay = new kakao.maps.CustomOverlay({
            content: '<div id="distance">' + '업장 거리: ' + distance + ' m</div>',
            map,
            position: center
        });

        // 오버레이 표시
        overlay.setMap(map);
    }


</script>