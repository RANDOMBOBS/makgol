<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../store_util/get-distance.jsp"></jsp:include>
<script>
    const requestStoreList = async (payload) => {
        const {keyword, longitude, latitude} = payload
        const url = "http://localhost:8090/store/list_data?longitude=" + longitude + "&latitude=" + latitude + "&keyword=" + keyword;

        const {axios} = window;
        const {data} = await axios.get(url);

        return data.result.map((item) => {
            const numberMyX = Number(longitude);
            const numberMyY = Number(latitude);
            const numberShopX = Number(item.longitude);
            const numberShopY = Number(item.latitude);

            const intDistance = getDistance(numberMyY, numberMyX, numberShopY, numberShopX);

            return {
                address_name: item.address,
                distance: intDistance,
                phone: item.phone,
                place_name: item.name,
                longitude: item.longitude,
                latitude: item.latitude,
                likes: item.likes,
                category_name: keyword,
            }
        });


    };
</script>

