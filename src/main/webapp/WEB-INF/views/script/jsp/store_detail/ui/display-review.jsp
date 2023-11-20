<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const displayReview = (param) => {
        const reviewBodyEle = document.querySelector("#review .item_info_body");

        if (!param.review) {
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