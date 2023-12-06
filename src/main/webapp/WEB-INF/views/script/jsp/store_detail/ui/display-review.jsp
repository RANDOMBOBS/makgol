<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./display-review-profile.jsp"></jsp:include>
<jsp:include page="./display-review-content.jsp"></jsp:include>
<jsp:include page="./display-review-etc.jsp"></jsp:include>
<script>
    const displayReview = (reviews) => {
        const reviewListEle = document.querySelector("#review_list");

        if (!reviews.length) {
            const h3Ele = document.createElement("h3");
            h3Ele.style.color = "#99958b";
            h3Ele.innerText = "아직 리뷰가 없습니다.";

            reviewListEle.style.background = "#cfcbc2";
            reviewListEle.style.display = "flex";
            reviewListEle.style.flexDirection = "column";
            reviewListEle.style.justifyContent = "center";
            reviewListEle.style.alignItems = "center";
            reviewListEle.appendChild(h3Ele);
        } else {
            const reviewListEle = $("#review_list");

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