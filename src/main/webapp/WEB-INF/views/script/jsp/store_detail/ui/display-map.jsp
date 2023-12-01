<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./display-my-coordinate.jsp"></jsp:include>
<jsp:include page="./display-shop-coordinate.jsp"></jsp:include>
<jsp:include page="./display-distance.jsp"></jsp:include>
<script>
    const displayInitialMap = (param) => {
        const {kakao} = window;
        const {shopX, shopY, myX, myY, distance} = param;

        const numberMyX = Number(myX);
        const numberMyY = Number(myY);
        const numberShopX = Number(shopX);
        const numberShopY = Number(shopY);

        const middleX = (numberMyX + numberShopX) / 2;
        const middleY = (numberMyY + numberShopY) / 2;

        let level = 0;

        if (0 <= distance && distance < 100) {
            level = 1;
        } else if (100 <= distance && distance < 195) {
            level = 2;
        } else if (195 <= distance && distance < 450) {
            level = 3;
        } else if (450 <= distance && distance < 750) {
            level = 4;
        } else {
            level = 5;
        }

        const container = document.querySelector("#map .item_info_body");
        const options = {
            center: new kakao.maps.LatLng(middleY, middleX), level,
        };

        const map = new kakao.maps.Map(container, options);
        displayMyCoordinate(map, {myX, myY});
        displayShopCoordinate(map, {shopX, shopY});
        displayDistance(map, {myX, myY, shopX, shopY, middleX, middleY}, distance);
    };
</script>