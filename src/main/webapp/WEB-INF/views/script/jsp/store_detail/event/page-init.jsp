<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/get-url-param.jsp"></jsp:include>
<jsp:include page="../ui/display-image.jsp"></jsp:include>
<jsp:include page="../ui/display-detail.jsp"></jsp:include>
<jsp:include page="../ui/display-menu.jsp"></jsp:include>
<jsp:include page="../ui/display-map.jsp"></jsp:include>
<jsp:include page="../ui/display-review.jsp"></jsp:include>
<jsp:include page="../backend/request-store-menu.jsp"></jsp:include>
<jsp:include page="../backend/request-store-review.jsp"></jsp:include>
<script>
    const pageInit = async () => {
        const param = getUrlParam();
        const displayFunctions = [
            displayImage,
            displayDetail,
            displayInitialMap,
            displayReview,
        ];

        displayFunctions.forEach((func) => func(param));

        const [menus, reviews] = await Promise.all([requestStoreMenu(param.placeName), requestStoreReview()]);
        displayMenu(menus);
        displayReview(reviews);
    };

    pageInit();
</script>