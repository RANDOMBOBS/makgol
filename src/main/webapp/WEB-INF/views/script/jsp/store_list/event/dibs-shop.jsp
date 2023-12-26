<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const dibsShop = (shops) => {
        $(".dibs").click((event) => {
            const dibsbuttonEle = $(event.target);
            const shopName = dibsbuttonEle.prev().text();
            const completeShopName = shopName.includes("...") ? shopName.replace("...", "") : shopName;
            const shop = shops.find((shop) => shop.place_name.includes(completeShopName));
            const dibsShops = JSON.parse(localStorage.getItem("dibsShops")) || [];
            const prevShops = [...dibsShops, shop]

            if (prevShops.length > 8) {
                return alert("더 이상 식당을 찜할수 없습니다.")
            }

            localStorage.setItem("dibsShops", JSON.stringify(prevShops));
            location.reload();
        });

    }
</script>