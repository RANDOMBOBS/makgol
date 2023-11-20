<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="../ui/display-other-shop-list.jsp"></jsp:include>
<script>
    const optionEle = $("#option");

    const swap = async function () {
        const option = $(this).val()
        const changedPage = JSON.parse(localStorage.getItem("changedPage"));

        const request = getModelData(changedPage);
        const data = await requestApi(request);
        const shops = data.result.documents;

        if (option === "distance") {
            alert("거리가 가까운 순으로 표시됩니다.");

            const nearDistanceShop = shops.slice().sort((a, b) => a.distance - b.distance);
            console.log(nearDistanceShop);
            displayOtherShopList(changedPage, nearDistanceShop);
        } else if (option === "like") {
            alert("좋아요가 많은 순으로 표시합니다.");
        }
    };

    optionEle.change(swap);
</script>