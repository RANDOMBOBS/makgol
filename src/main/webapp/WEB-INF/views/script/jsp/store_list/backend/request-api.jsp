<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const requestApi = async (payload) => {
        const {keyword, longitude, latitude, page} = payload
        const url = "http://localhost:8090/store/list_data?longitude=" + longitude + "&latitude=" + latitude + "&keyword=" + keyword + "&page=" + page;

        const {axios} = window;
        const {data} = await axios.get(url);

        return data.result.map((item) => ({
            address_name: item.address,
            distance: item.distance,
            phone: item.phone,
            place_name: item.name,
            x: item.longitude,
            y: item.latitude,
            likes: item.likes,
            category_name: keyword,
            page
        }));

    };
</script>