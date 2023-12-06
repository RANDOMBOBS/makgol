<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="init-map.jsp"></jsp:include>
<jsp:include page="./display-my-coordinate.jsp"></jsp:include>
<jsp:include page="./display-shop-coordinate.jsp"></jsp:include>
<script>
    const displayInitialMap = (shops, myCoordinate) => {
        const map = initMap(myCoordinate);
        displayMyCoordinate(map, myCoordinate);
        displayShopsCoordinate(map, shops);
    };
</script>