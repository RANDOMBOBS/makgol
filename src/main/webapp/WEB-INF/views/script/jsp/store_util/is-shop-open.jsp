<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const isShopOpen = (openTime) => {
        const now = new Date();
        const [start, end] = openTime.split("~");

        const startTrim = start.trim();
        const endTrim = end.trim();

        const startHour = parseInt(startTrim.slice(0, -3));
        const endHour = parseInt(endTrim.slice(0, -3));

        const startMinute = parseInt(startTrim.slice(3, 5));
        const endMinute = parseInt(endTrim.slice(3, 5));

        const nowHour = now.getHours();
        const nowMinute = now.getMinutes();

        const nowTotalMinutes = nowHour * 60 + nowMinute;
        const startTimeTotalMinutes = startHour * 60 + startMinute;
        const endTimeTotalMinutes = endHour * 60 + endMinute;

        return nowTotalMinutes >= startTimeTotalMinutes && nowTotalMinutes <= endTimeTotalMinutes;
    }
</script>