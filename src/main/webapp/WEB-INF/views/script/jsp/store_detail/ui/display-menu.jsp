<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayMenu = (menus) => {
        const menuListEle = $("#menu_list");

        menus.forEach((item) => {
            const liEle = $("<li>");
            const menuNameELe = $("<span>").addClass("menu_name");
            const priceEle = $("<span>").addClass("price");

            menuNameELe.text(item.menu);

            const price = item.price ? item.price + "₩" : "";

            priceEle.text(price)

            liEle.append(menuNameELe);
            liEle.append(priceEle);

            menuListEle.append(liEle);
        })
    }
</script>