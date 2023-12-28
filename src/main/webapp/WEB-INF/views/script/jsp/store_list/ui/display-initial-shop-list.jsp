<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/create-url-for-detail-page.jsp"></jsp:include>
<jsp:include page="./display-selected-shop.jsp"></jsp:include>
<jsp:include page="./remove-selected-shop.jsp"></jsp:include>
<jsp:include page="../event/mouse.jsp"></jsp:include>
<script>
    const displayInitialShopList = ({shops, keyword}) => {
        const searchKeywordEle = $("#search_keyword");
        searchKeywordEle.text(keyword);
        const shopInfoListEle = $(".shop_info_list");

        if (!shops.length) {
            const h3Ele1 = $("<h3>");
            h3Ele1.css({color: "#99958b"}).text("주변에 " + keyword + " 카테고리에");
            const h3Ele2 = $("<h3>");
            h3Ele2.css({color: "#99958b"}).text("해당하는 업장이 존재하지 않습니다.");


            shopInfoListEle.css({
                background: "#cccccc",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
            });

            shopInfoListEle.append(h3Ele1, h3Ele2);
        }

        const userId = "${loginedUserVo.id}";

        shops.forEach((shop) => {
            const shopInfoItemEle = $("<div>").addClass("shop_info_item");

            const topItemEle = $("<div>").addClass("top_item");
            const placeNameEle = $("<a>")
                .attr("id", "place_name")
                .text(shop.place_name)

            shop.place_name.length >= 14 && placeNameEle.text(shop.place_name.substring(0, 14) + "...");

            let dibsEle;
            if (userId) {
                dibsEle = $("<button>").addClass("dibs")
                dibsEle.text("찜");
            }

            const likeItemEle = $("<p>").attr("id", "likes").text("♥" + shop.likes)

            const topItemComposition = [placeNameEle, dibsEle, likeItemEle];
            topItemComposition.forEach((composition) => topItemEle.append(composition));


            const middleItemEle = $("<div>").addClass("middle_item");

            const addressNameEle = $("<p>").attr("id", "address_name").text(shop.address_name);
            const distanceEle = $("<p>").attr("id", "distance").text(shop.distance + "m");
            const isOpenedEle = $("<p>").attr("id", "is_opened");

            if (shop.opening_hours) {
                const openStatus = isShopOpen(shop.opening_hours);

                openStatus
                    ? isOpenedEle.text("영업중").css({color: "red"})
                    : isOpenedEle.text("영업종료").css({color: "gray"});
            }

            const middleItemComposition = [addressNameEle, isOpenedEle, distanceEle];
            middleItemComposition.forEach((composition) =>
                middleItemEle.append(composition),
            );

            const underItemEle = $("<div>").addClass("under_item");

            const phoneEle = $("<p>").attr("id", "phone").text(shop.phone);

            const detailEle = $("<a>")
                .attr("id", "detail")
                .text("상세 페이지")

            const url = createUrlForDetailPage(shop);

            placeNameEle.attr("href", url);
            detailEle.attr("href", url);

            const underItemComposition = [phoneEle, detailEle];
            underItemComposition.forEach((composition) =>
                underItemEle.append(composition),
            );

            const shopInfoComposition = [topItemEle, middleItemEle, underItemEle];

            shopInfoComposition.forEach((composition) =>
                shopInfoItemEle.append(composition),
            );

            shopInfoListEle.append(shopInfoItemEle);
            mouse(shop, shopInfoItemEle);
        });
    }
</script>