<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../ui/display-other-shop-list.jsp"></jsp:include>
<script>
    const selectOption = (shops) => {
        $("input[name='check_info']").change(async () => {
            const option = $("input[name='check_info']:checked").val();

            if (option === "기본") {
                alert("기본으로 표시됩니다.");

                displayOtherShopList(shops);
            } else if (option === "거리순") {
                alert("거리가 가까운 순으로 표시됩니다.");

                const nearDistanceShop = shops.slice().sort((a, b) => a.distance - b.distance);
                displayOtherShopList(nearDistanceShop);
            } else if (option === "좋아요순") {
                alert("좋아요가 많은 순으로 표시합니다.");

                const moreLikeShop = shops.slice().sort((a, b) => b.likes - a.likes);
                displayOtherShopList(moreLikeShop);
            }
        })
    }
</script>