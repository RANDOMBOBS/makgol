<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const getDistance = (numberMyY, numberMyX, numberShopY, numberShopX) => {
        const degreesToRadians = (degrees) => {
            return degrees * (Math.PI / 180);
        };

        const earthRadius = 6371; // 지구 반경 (단위: km)
        const dLat = degreesToRadians(numberShopY - numberMyY);
        const dLon = degreesToRadians(numberShopX - numberMyX);

        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(degreesToRadians(numberMyY)) * Math.cos(degreesToRadians(numberShopY)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 미터로 변환
        return Number.parseInt(earthRadius * c * 1000);
    }
</script>
