<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<jsp:include page="../util/get-url-param.jsp"></jsp:include>
<jsp:include page="../ui/display-image.jsp"></jsp:include>
<jsp:include page="../ui/display-detail.jsp"></jsp:include>
<jsp:include page="../ui/display-map.jsp"></jsp:include>
<jsp:include page="../ui/display-review.jsp"></jsp:include>
<script>
    const pageInit = () => {
        const param = getUrlParam();

        const displayFunctions = [
            displayImage,
            displayDetail,
            displayInitialMap,
            displayReview,
        ];

        displayFunctions.forEach((func) => func(param));
    };

    pageInit();
</script>