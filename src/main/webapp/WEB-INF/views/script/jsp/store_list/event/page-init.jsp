<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="../util/get-model-data.jsp"></jsp:include>
<jsp:include page="../backend/request-api.jsp"></jsp:include>
<jsp:include page="../ui/display-initial-shop-list.jsp"></jsp:include>
<jsp:include page="../ui/display-map.jsp"></jsp:include>
<jsp:include page="../ui/display-selected-button.jsp"></jsp:include>
<script>
    const shopInfo = {
        shops: [],
        keyword: "",
    };

    const pageInit = async () => {
        const page = "1";
        localStorage.setItem("page", page);

        const request = getModelData(page);

        const data = await requestApi(request);
        const shops = data.result.documents;

        shopInfo.shops = shops;
        shopInfo.keyword = request.keyword;

        const myCoordinate = {myX: request.myX, myY: request.myY};

        localStorage.setItem("myCoordinate", JSON.stringify(myCoordinate));

        displayInitialShopList(shopInfo);
        displayMap(shops, myCoordinate);

        $(".shop_page button").eq(0).addClass("selected_button");
    };

    pageInit();
</script>