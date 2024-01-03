<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../ui/display-other-shop-list.jsp"></jsp:include>
<script>
    const selectOption = (shops) => {
        $("input[name='check_info']").change(async () => {
            const option = $("input[name='check_info']:checked").val();

            if (option === "기본") {
                displayOtherShopList(shops);
            } else if (option === "거리순") {
                const nearDistanceShop = shops.slice().sort((before, after) => before.distance - after.distance);
                displayOtherShopList(nearDistanceShop);
            } else if (option === "좋아요순") {
                const moreLikeShop = shops.slice().sort((before, after) => after.likes - before.likes);
                displayOtherShopList(moreLikeShop);
            } else if (option === "영업순") {
                // const openShop = $(".shop_info_list").children().slice()
                //     .sort((before, after) => {
                //         const beforeOpenStatus = $(before).children(".middle_item").children("#is_opened").text();
                //         const afterOpenStatus = $(after).children(".middle_item").children("#is_opened").text()
                //         const order = {"영업중": 0, "영업종료": 1};
                //
                //         return order[beforeOpenStatus] - order[afterOpenStatus];
                //     })
                //     .get()
                //     .map((item) => {
                //         const shopName = $(item).children(".top_item").children("#place_name").text();
                //         const completeShopNames = shopName.includes("...") ? shopName.replace("...", "") : shopName;
                //         return shops.find((shop) => shop.place_name.includes(completeShopNames));
                //     })

                const openShop = $(".shop_info_list").children().slice().toArray()
                    .sort((before, after) => {
                        const beforeOpenStatus = $(before).find(".middle_item #is_opened").text().toLowerCase();
                        const afterOpenStatus = $(after).find(".middle_item #is_opened").text().toLowerCase();

                        const order = {"영업중": 0, "영업종료": 1, undefined: 2};

                        return order[beforeOpenStatus] - order[afterOpenStatus];
                    })
                    .map((item) => {
                        const shopName = $(item).find(".top_item #place_name").text();
                        const completeShopNames = shopName.includes("...") ? shopName.replace("...", "") : shopName;
                        return shops.find((shop) => shop.place_name.includes(completeShopNames));
                    });

                displayOtherShopList(openShop);
            }
        })
    }
</script>