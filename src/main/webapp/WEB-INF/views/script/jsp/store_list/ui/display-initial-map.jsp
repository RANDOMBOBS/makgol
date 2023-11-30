<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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