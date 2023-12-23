<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const dibsShop = (shops) => {
        $(".dibs").click((event) => {
            const dibsbuttonEle = $(event.target);
            const shopName = dibsbuttonEle.prev().text();
            const shop = shops.find((shop) => shop.place_name === shopName);

            const dibsShops = JSON.parse(localStorage.getItem("dibsShops")) || [];
            const prevShops = [...dibsShops, shop]

            if (prevShops.length > 10) {
                return alert("더 이상 식당을 찜할수 없습니다.")
            }

            localStorage.setItem("dibsShops", JSON.stringify(prevShops));

            alert("식당을 찜했습니다!")
            location.reload();
        });

    }
</script>