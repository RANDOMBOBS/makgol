<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayImage = (param) => {
        const pictureBodyEle = document.querySelector("#picture .item_info_body");

        if (!param.placeImage) {
            const h3Ele = document.createElement("h3");
            h3Ele.style.color = "#99958b";
            h3Ele.innerText = "아직 사진이 없습니다.";

            pictureBodyEle.style.background = "#cfcbc2";
            pictureBodyEle.style.display = "flex";
            pictureBodyEle.style.justifyContent = "center";
            pictureBodyEle.style.alignItems = "center";
            pictureBodyEle.appendChild(h3Ele);
        } else {
            alert("dsd");
        }
    };

</script>