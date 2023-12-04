<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/get-url-param.jsp"></jsp:include>
<jsp:include page="../ui/display-image.jsp"></jsp:include>
<jsp:include page="../ui/link-head-office.jsp"></jsp:include>
<jsp:include page="../ui/display-detail.jsp"></jsp:include>
<jsp:include page="../ui/display-menu.jsp"></jsp:include>
<jsp:include page="../ui/display-map.jsp"></jsp:include>
<jsp:include page="../ui/display-review.jsp"></jsp:include>
<jsp:include page="../backend/request-store-detail.jsp"></jsp:include>
<jsp:include page="../backend/request-store-menu.jsp"></jsp:include>
<jsp:include page="../backend/request-store-review.jsp"></jsp:include>
<jsp:include page="./increase-likes.jsp"></jsp:include>
<script>
    const pageInit = async () => {
        const {shopId, shopX, shopY, myX, myY, distance} = getUrlParam();
        const [detail, menus, reviews] = await Promise.all([
            requestStoreDetail(shopId),
            requestStoreMenu(shopId),
            requestStoreReview(shopId)
        ]);

        displayImage(detail.photo);
        linkHeadOffice(detail.site);
        displayDetail(detail);
        displayMenu(menus);
        displayInitialMap({shopX, shopY, myX, myY, distance});
        displayReview(reviews);

        $("#likes").click(() => increaseLikes(shopId));
    };

    pageInit();
</script>