<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayDetail = (detail) => {
        const ulEle = document.querySelector("#intro .item_info_body ul");
        const liEles = Array.from(ulEle.children);
        const detailInfos = [
            detail.name,
            detail.phone,
            detail.category,
            detail.address,
            detail.likes
        ];
        
        liEles.forEach((liEle, idx) => {
            const spanEle = liEle.querySelector("span:nth-child(2)");
            spanEle.innerText = detailInfos[idx];
        });
    };

</script>