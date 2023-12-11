<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../backend/request-review-image.jsp"></jsp:include>
<script>
    const list = async (e) => {
        const reviewId = $(e.target).parent().parent().siblings("input").val()
        const reviewImages = await requestReviewImage(reviewId);

        const reviewImageListEle = $(".review_image_list");

        reviewImages.forEach((imgUrl) => {
            const reviewExpendImageEle = $("<div>").addClass("expend_image");
            const imgEle = $("<img>");
            imgEle.attr({src: "http://localhost:8090" + imgUrl});
            reviewExpendImageEle.append(imgEle);
            reviewImageListEle.append(reviewExpendImageEle);
        })
    };
</script>
