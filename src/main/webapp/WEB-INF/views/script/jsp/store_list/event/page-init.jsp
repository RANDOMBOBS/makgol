<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/get-model-data.jsp"></jsp:include>
<jsp:include page="../backend/request-store-list.jsp"></jsp:include>
<jsp:include page="../ui/display-initial-shop-list.jsp"></jsp:include>
<jsp:include page="../ui/display-initial-map.jsp"></jsp:include>
<script>
    const shopInfo = {
        shops: [],
        keyword: "",
    };

    const pageInit = async () => {
        const request = getModelData();

        const shops = await requestStoreList(request);

        shopInfo.shops = shops;
        shopInfo.keyword = request.keyword;

        const myCoordinate = {longitude: request.longitude, latitude: request.latitude};

        localStorage.setItem("myCoordinate", JSON.stringify(myCoordinate));

        displayInitialShopList(shopInfo);
        displayInitialMap(shops, myCoordinate);

        $(".shop_page button").eq(0).addClass("selected_button");
    };

    pageInit();
</script>