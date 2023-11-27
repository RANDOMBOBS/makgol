<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="./display-my-coordinate.jsp"></jsp:include>
<jsp:include page="./display-shop-coordinate.jsp"></jsp:include>
<jsp:include page="../../store_util/get-distance.jsp"></jsp:include>
<script>
    const displayInitialMap = (param) => {
        const {kakao} = window;
        const {shopX, shopY, myX, myY} = param;

        const numberShopX = Number(shopX);
        const numberShopY = Number(shopY);
        const numberMyX = Number(myX);
        const numberMyY = Number(myY);

        const middleX = (numberMyX + numberShopX) / 2;
        const middleY = (numberMyY + numberShopY) / 2;

        const intDistance = getDistance(numberMyY, numberMyX, numberShopY, numberShopX);

        let level = 0;

        if (0 <= intDistance && intDistance < 100) {
            level = 1;
        } else if (100 <= intDistance && intDistance < 195) {
            level = 2;
        } else if (195 <= intDistance && intDistance < 450) {
            level = 3;
        } else if (450 <= intDistance && intDistance < 750) {
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
    };
</script>