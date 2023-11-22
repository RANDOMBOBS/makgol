<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="../ui/display-other-shop-list.jsp"></jsp:include>
<script>
    $(".shop_page button").click(async function () {
        const selectEle = $("#option");
        selectEle.children(":first").prop("selected", true);

        const page = $(this).val();
        localStorage.setItem("changedPage", page);

        const request = getModelData(page);
        const shops = await requestApi(request);

        const myCoordinate = JSON.parse(localStorage.getItem("myCoordinate"));

        // console.log($(this));

        displayOtherShopList(page, shops);
        displayMap(shops, myCoordinate);
        displaySelectedButton($(this));
    });
</script>
