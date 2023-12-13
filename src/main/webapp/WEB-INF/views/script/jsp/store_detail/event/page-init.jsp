<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/get-url-param.jsp"></jsp:include>
<jsp:include page="../ui/display-image.jsp"></jsp:include>
<jsp:include page="../ui/link-head-office.jsp"></jsp:include>
<jsp:include page="../ui/display-detail.jsp"></jsp:include>
<jsp:include page="../ui/display-menu.jsp"></jsp:include>
<jsp:include page="../ui/display-map.jsp"></jsp:include>
<jsp:include page="../ui/display-review.jsp"></jsp:include>
<jsp:include page="../ui/display-review-form.jsp"></jsp:include>
<jsp:include page="../backend/request-store-detail.jsp"></jsp:include>
<jsp:include page="../backend/request-store-menu.jsp"></jsp:include>
<jsp:include page="../backend/request-store-review.jsp"></jsp:include>
<jsp:include page="./increase-likes.jsp"></jsp:include>
<jsp:include page="./decrease-likes.jsp"></jsp:include>
<jsp:include page="./open-upload-modal.jsp"></jsp:include>
<jsp:include page="./open-list-modal.jsp"></jsp:include>
<jsp:include page="./submit-review.jsp"></jsp:include>
<jsp:include page="../util/get-likes-status.jsp"></jsp:include>
<script>
    const pageInit = async () => {
        const {shopId, shopX, shopY, myX, myY, distance} = getUrlParam();
        const [detail, menus, reviews] = await Promise.all([
            requestStoreDetail(shopId),
            requestStoreMenu(shopId),
            requestStoreReview(shopId)
        ]);
        const userId = "${loginedUserVo.id}";

        displayImage(detail.photo);
        linkHeadOffice(detail.site);
        displayDetail(detail);
        displayMenu(menus);
        displayInitialMap({shopX, shopY, myX, myY, distance});
        displayReview(reviews);
        displayReviewForm();

        $("#likes").click(async () => {
            if (await getLikesStatus(userId, shopId)) {
                await increaseLikes(userId, shopId)
            } else {
                await decreaseLikes(userId, shopId);
            }
        })

        $("#upload_image").click(() => openUploadModal());
        $(".review_image_box").click((e) => openListModal(e));
        $("#submit_review").click(() => submitReview(shopId));


    };

    pageInit();
</script>