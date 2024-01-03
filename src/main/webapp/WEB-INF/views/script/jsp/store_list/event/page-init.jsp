<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/get-model-data.jsp"></jsp:include>
<jsp:include page="../backend/request-store-list.jsp"></jsp:include>
<jsp:include page="../ui/display-initial-shop-list.jsp"></jsp:include>
<jsp:include page="../ui/display-initial-map.jsp"></jsp:include>
<jsp:include page="./select-option.jsp"></jsp:include>
<jsp:include page="./dibs-shop.jsp"></jsp:include>
<jsp:include page="../ui/display-dibs-shop.jsp"></jsp:include>
<jsp:include page="../logic/disabled-dibs-button.jsp"></jsp:include>
<jsp:include page="../../store_util/is-shop-open.jsp"></jsp:include>
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
        selectOption(shops);
        dibsShop(shops);
        displayDipsShop();

        const dibsShops = JSON.parse(localStorage.getItem("dibsShops")) || []
        const dibsShopNames = dibsShops.map((shop) => shop.place_name);
        disabledDibsButton(dibsShopNames);

        $("input:radio[name='check_info']:radio[value='기본']").prop('checked', true)
    };

    pageInit();
</script>