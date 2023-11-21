<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const displayShopCoordinate = (map, {shopX, shopY}) => {
        const {kakao} = window;

        const markerPosition = new kakao.maps.LatLng(shopY, shopX);

        const marker = new kakao.maps.Marker({position: markerPosition});
        marker.setMap(map);

    };
</script>