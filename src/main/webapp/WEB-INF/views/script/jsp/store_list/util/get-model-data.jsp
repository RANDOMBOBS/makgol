<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const getModelData = (page) => {
        const longitude = "${x}";
        const latitude = "${y}";
        const keyword = "${keyword}";

        return {
            longitude,
            latitude,
            keyword,
            page,
        };
    };
</script>