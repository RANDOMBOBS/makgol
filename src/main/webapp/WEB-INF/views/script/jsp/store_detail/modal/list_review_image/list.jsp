<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../backend/request-review-image.jsp"></jsp:include>
<link href="<c:url value='/resources/static/css/slick.css' />" rel="stylesheet"
      type="text/css"/>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>
    const list = async (e) => {
        $.noConflict();
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

        reviewImageListEle.slick({
            slidesToShow: 1,
            slidesToScroll: reviewImages.length,
            dotsClass: "custom-dots",
            dots: true,
            autoplay: true,
            speed: 1000,
            prevArrow:
                "<i style='width: 200px; height: 200px' class='fa-solid fa-circle-arrow-left'></i>",
            nextArrow:
                "<i style='width: 200px; height: 200px' class='fa-solid fa-circle-arrow-right'></i>",
        })

    };
</script>
