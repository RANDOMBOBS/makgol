<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayOtherShopList = (shops) => {
        shops.forEach((shop, idx) => {
            const shopInfoListEle = $(".shop_info_list");
            const shops = shopInfoListEle.children();

            const placeNameEle = $(shops[idx]).find("#place_name");
            const addressNameEle = $(shops[idx]).find("#address_name");
            const distanceEle = $(shops[idx]).find("#distance");
            const phoneEle = $(shops[idx]).find("#phone");
            const detailEle = $(shops[idx]).find("#detail");
            const likesEle = $(shops[idx]).find("#likes");
            const isOpenedEle = $(shops[idx]).find("#is_opened");

            placeNameEle.text(shop.place_name);

            if (shop.place_name.length >= 14) {
                placeNameEle.text(shop.place_name.substring(0, 14) + "...");
            }

            addressNameEle.text(shop.address_name);
            distanceEle.text(shop.distance + "m");
            phoneEle.text(shop.phone);
            detailEle.text("상세 페이지");
            likesEle.text("♥" + shop.likes);

            if (shop.opening_hours) {
                const openStatus = isShopOpen(shop.opening_hours);

                openStatus
                    ? isOpenedEle.text("영업중").css({color: "red"})
                    : isOpenedEle.text("영업종료").css({color: "gray"});
            }

            const url = createUrlForDetailPage(shop);

            placeNameEle.attr('href', url);
            detailEle.attr('href', url);

            const shopInfoItemEle = $(shops[idx]);
            mouse(shop, shopInfoItemEle);
        });
    };

</script>


<%--const shopInfoListEle = $(".shop_info_list");--%>
<%--const shops = shopInfoListEle.children();--%>

<%--const placeNameEle = shops[idx].querySelector("#place_name");--%>
<%--const addressNameEle = shops[idx].querySelector("#address_name");--%>
<%--const distanceEle = shops[idx].querySelector("#distance");--%>
<%--const phoneEle = shops[idx].querySelector("#phone");--%>
<%--const detailEle = shops[idx].querySelector("#detail");--%>
<%--const likesEle = shops[idx].querySelector("#likes");--%>