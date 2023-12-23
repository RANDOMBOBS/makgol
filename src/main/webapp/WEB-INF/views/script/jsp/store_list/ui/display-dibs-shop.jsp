<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../logic/delete-dibs-shop-item.jsp"></jsp:include>
<script>
    const displayDipsShop = () => {
        const dibsShops = JSON.parse(localStorage.getItem("dibsShops")) || [];
        const dibsShopListEle = $(".dibs_shop_list");

        if (!dibsShops.length) {
            const h3Ele = $("<h3>");
            h3Ele.css({color: "#99958b"}).text("아직 찜한 식당이 없습니다.");


            dibsShopListEle.css({
                background: "#cccccc",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
            });

            dibsShopListEle.append(h3Ele);
        }

        const dibsShopHeadEle = $(".dibs_shop_head");
        const dibsShopCountEle = $("<div>").addClass("dibs_shop_count")
        dibsShopCountEle.text("8 / " + dibsShops.length);

        dibsShopHeadEle.append(dibsShopCountEle);


        dibsShops.forEach((shop, idx) => {
            const dibsShopListEle = $(".dibs_shop_list");

            const dibsShopItemEle = $("<div>").addClass("dibs_shop_item");
            const shopNameEle = $("<a>").addClass("shop_name");
            const url = createUrlForDetailPage(shop);
            shopNameEle.text(shop.place_name);
            shopNameEle.attr("href", url);
            const deleteButtonEle = $("<button>").addClass("delete_button");
            deleteButtonEle.text("X")
            deleteButtonEle.click(deleteDibsShopItem);
            dibsShopItemEle.append(shopNameEle);
            dibsShopItemEle.append(deleteButtonEle);
            dibsShopListEle.append(dibsShopItemEle);

            dibsShopItemEle.off("mouseover").mouseover(() => displaySelectedShop(shop))
            dibsShopItemEle.off("mouseout").mouseout(() => removeSelectedShop(shop))
        })
    };
</script>