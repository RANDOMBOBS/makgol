<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayOtherShopList = (shops) => {
        shops.forEach((shop, idx) => {
            const shopInfoListEle = document.querySelector(".shop_info_list");
            const shops = Array.from(shopInfoListEle.children);

            const placeNameEle = shops[idx].querySelector("#place_name");
            const addressNameEle = shops[idx].querySelector("#address_name");
            const distanceEle = shops[idx].querySelector("#distance");
            const phoneEle = shops[idx].querySelector("#phone");
            const detailEle = shops[idx].querySelector("#detail");
            const likesEle = shops[idx].querySelector("#likes");

            placeNameEle.innerText = shop.place_name;

            if (shop.place_name.length >= 14) {
                placeNameEle.innerText = shop.place_name.substring(0, 14) + "...";
            }

            addressNameEle.innerText = shop.address_name;
            distanceEle.innerText = shop.distance + "m";
            phoneEle.innerText = shop.phone;
            detailEle.innerText = "상세 페이지";
            likesEle.innerText = "♥" + shop.likes;
            const url = createUrlForDetailPage(shop);
            placeNameEle.setAttribute("href", url);
            detailEle.setAttribute("href", url);

            const shopInfoItemEle = $(shopInfoListEle.children.item(idx));
            shopInfoItemEle.mouseover(() => displaySelectedShop(shop));
            shopInfoItemEle.mouseout(() => removeSelectedShop(shop));
        });
    };

</script>