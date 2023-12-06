<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const getUrlParam = () => {
        const urlParams = new URLSearchParams(window.location.search);

        return {
            shopId: urlParams.get("shop_id"),
            shopX: urlParams.get("shop_x"),
            shopY: urlParams.get("shop_y"),
            myX: urlParams.get("my_x"),
            myY: urlParams.get("my_y"),
            distance: urlParams.get("distance")
        };
    };
</script>