<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="./display-my-coordinate.jsp"></jsp:include>
<jsp:include page="./display-shop-coordinate.jsp"></jsp:include>
<script>
    const displayMap = (param) => {
        const {kakao} = window;
        const {shopX, shopY, myX, myY} = param;

        const middleX = (Number(shopX) + Number(myX)) / 2;
        const middleY = (Number(shopY) + Number(myY)) / 2;


        const container = document.querySelector("#map .item_info_body");
        const options = {
            center: new kakao.maps.LatLng(middleY, middleX), level: 4,
        };

        const map = new kakao.maps.Map(container, options);
        displayMyCoordinate(map, {myX, myY});
        displayShopCoordinate(map, {shopX, shopY});
    };
</script>