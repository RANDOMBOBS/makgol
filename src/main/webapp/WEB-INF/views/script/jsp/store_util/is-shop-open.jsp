<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const isShopOpen = (openTime) => {
        const now = new Date();
        const [start, end] = openTime.split("~");

        const currentStart = start.trim().slice(0, -3);
        const currentEnd = end.trim().slice(0, -3);

        const hours = now.getHours();
        return hours >= currentStart && hours < currentEnd;
    }
</script>