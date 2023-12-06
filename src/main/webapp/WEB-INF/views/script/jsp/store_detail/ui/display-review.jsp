<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./display-review-profile.jsp"></jsp:include>
<jsp:include page="./display-review-content.jsp"></jsp:include>
<jsp:include page="./display-review-etc.jsp"></jsp:include>
<script>
    const displayReview = (reviews) => {
        const reviewListEle = $("#review_list");
        console.log(reviews);

        if (!reviews.length) {
            const h3Ele = $("<h3>");
            h3Ele.css({color: "#99958b"}).text("아직 리뷰가 없습니다.");

            reviewListEle.css({
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center"
            })

            reviewListEle.append(h3Ele)
        } else {
            reviews.forEach((item) => {
                const liEle = $("<li>").addClass("review_item");

                const reviewProfileEle = displayReviewProfile(item);
                const reviewContentEle = displayReviewContent(item);
                const reviewEtcEle = displayReviewEtc(item);

                liEle.append(reviewProfileEle);
                liEle.append(reviewContentEle);
                liEle.append(reviewEtcEle);

                reviewListEle.append(liEle);
            })
        }
    };
</script>