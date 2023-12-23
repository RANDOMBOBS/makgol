<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const disabledDibsButton = (dibsShopNames) => {
        const shopInfoListEle = $(".shop_info_list");
        const topItems = shopInfoListEle.children().children(".top_item").toArray();
        const dibsTopItems = topItems.filter((topItem) => dibsShopNames.includes($(topItem).children("#place_name").text()));
        const dibsButtons = dibsTopItems.map((dibsTopItem) => $(dibsTopItem).children(".dibs"));
        dibsButtons.forEach((button) => {
            button.attr("disabled", true).css({background: "gray", color: "white", cursor: "default"})
        });
    }
</script>
