<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const getModelData = (page) => {
        const myX = "${x}";
        const myY = "${y}";
        const keyword = "${keyword}";
        
        return {
            myX,
            myY,
            keyword,
            page,
        };
    };
</script>