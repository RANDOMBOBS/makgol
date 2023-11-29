<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../util/create-url-for-detail-page.jsp"></jsp:include>
<jsp:include page="./display-selected-shop.jsp"></jsp:include>
<jsp:include page="./remove-selected-shop.jsp"></jsp:include>
<jsp:include page="../event/mouse.jsp"></jsp:include>
<script>
    const displayInitialShopList = ({shops, keyword}) => {
        const searchKeywordEle = $("#search_keyword");
        searchKeywordEle.text(keyword);

        shops.forEach((shop) => {
            const shopInfoListEle = $(".shop_info_list");
            const shopInfoItemEle = $("<div>").addClass("shop_info_item");

            const topItemEle = $("<div>").addClass("top_item");
            const placeNameEle = $("<a>")
                .attr("id", "place_name")
                .text(shop.place_name)

            shop.place_name.length >= 14 && placeNameEle.text(shop.place_name.substring(0, 14) + "...");

            const likeItemEle = $("<p>").attr("id", "likes").text("♥" + shop.likes)

            const topItemComposition = [placeNameEle, likeItemEle];
            topItemComposition.forEach((composition) => topItemEle.append(composition));

            const middleItemEle = $("<div>").addClass("middle_item");

            const addressNameEle = $("<p>").attr("id", "address_name").text(shop.address_name);
            const distanceEle = $("<p>").attr("id", "distance").text(shop.distance + "m");

            const middleItemComposition = [addressNameEle, distanceEle];
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