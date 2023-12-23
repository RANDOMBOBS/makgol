<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const deleteDibsShopItem = (event) => {
        const shopName = $(event.target).prev().text();
        const dibsShops = JSON.parse(localStorage.getItem("dibsShops")) || [];
        const restDibsShops = dibsShops.filter((shop) => shop.place_name != shopName)

        localStorage.setItem("dibsShops", JSON.stringify(restDibsShops));

        alert("찜을 제거했습니다!")
        location.reload();
    }
</script>
