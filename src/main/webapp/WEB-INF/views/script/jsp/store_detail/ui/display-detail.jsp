<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayDetail = async (detail, {userId, shopId}) => {
        const ulEle = document.querySelector("#intro .item_info_body ul");
        const liEles = Array.from(ulEle.children);

        const openingHours = detail.opening_hours;

        const detailInfos = [
            detail.name,
            detail.phone,
            detail.category,
            detail.address,
            detail.likes,
            openingHours
        ];

        liEles.forEach((liEle, idx) => {
            const spanEle = liEle.querySelector("span:nth-child(2)");
            spanEle.innerText = detailInfos[idx];
        });

        if (openingHours) {
            const openStatus = isShopOpen(openingHours);
            const isOpenedEle = $("#is_opened");

            openStatus
                ? isOpenedEle.text("운영중").css({color: "red"})
                : isOpenedEle.text("영업종료").css({color: "gray"});

        }

        if (!userId) return;
        if (!await getLikesStatus({userId, shopId})) {
            $("#is_liked").css({
                visibility: "visible",
                color: "#e8a812",
                position: "relative",
                bottom: "45px",
                left: "95px"
            })
        }
    };
</script>