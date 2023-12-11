<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const requestReviewImage = async (id) => {
        const url = "http://localhost:8090/store/review_image/review_id/" + id;
        const {axios} = window;
        const {data} = await axios.get(url);
        
        return data.result;
    }
</script>