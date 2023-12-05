<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const mouse = (shop, shopInfoItemEle) => {
        shopInfoItemEle.off("mouseover").mouseover(() => displaySelectedShop(shop));
        shopInfoItemEle.off("mouseout").mouseout(() => removeSelectedShop(shop));
    }
</script>