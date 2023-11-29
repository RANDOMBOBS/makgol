<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../data-structure/hashmap.jsp"></jsp:include>
<script>
    const initMap = (coordinate) => {
        const {longitude, latitude} = coordinate;
        const {kakao} = window;

        const container = $("#map")[0];
        const options = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 4,
        };

        const kakaoMap = new kakao.maps.Map(container, options);
        hashMap.set("kakaomap", kakaoMap);
        return kakaoMap;
    }
</script>