<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayDetail = (param) => {
        const ulEle = document.querySelector("#intro .item_info_body ul");
        const liEles = Array.from(ulEle.children);
        const detailInfos = [
            param.placeName,
            param.phone,
            param.categoryName,
            param.addressName
        ];
        
        liEles.forEach((liEle, idx) => {
            const spanEle = liEle.querySelector("span:nth-child(2)");
            spanEle.innerText = detailInfos[idx];
        });

        const distanceEle = document.querySelector("#distance");
        distanceEle.nextSibling.textContent = getDistance(Number(param.myY), Number(param.myX), Number(param.shopY), Number(param.shopX)) + "m";
    };

</script>