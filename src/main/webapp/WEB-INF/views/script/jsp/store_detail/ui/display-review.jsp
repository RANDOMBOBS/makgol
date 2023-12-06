<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayReview = (reviews) => {
        const reviewBodyEle = document.querySelector("#review .item_info_body");

        if (!review.length) {
            const h3Ele = document.createElement("h3");
            h3Ele.style.color = "#99958b";
            h3Ele.innerText = "아직 리뷰가 없습니다.";

            reviewBodyEle.style.background = "#cfcbc2";
            reviewBodyEle.style.display = "flex";
            reviewBodyEle.style.justifyContent = "center";
            reviewBodyEle.style.alignItems = "center";
            reviewBodyEle.appendChild(h3Ele);
        } else {
            alert("asd");
        }
    };
</script>