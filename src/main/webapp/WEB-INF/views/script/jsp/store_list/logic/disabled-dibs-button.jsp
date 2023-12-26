<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const disabledDibsButton = (dibsShopNames) => {
        const shopInfoListEle = $(".shop_info_list");
        const topItems = shopInfoListEle.children().children(".top_item").toArray();

        const dibsItems = topItems.filter((topItem) => {
            const shopName = $(topItem).children("#place_name").text();
            const completeShopNames = shopName.includes("...") ? shopName.replace("...", "") : shopName;
            return dibsShopNames.some(dibsShopName => dibsShopName.includes(completeShopNames));
        })

        const dibsButtons = dibsItems.map((dibsItem) => $(dibsItem).children(".dibs"));
        dibsButtons.forEach((button) => {
            button.attr("disabled", true).css({background: "gray", color: "white", cursor: "default"})
        });
    }
</script>
