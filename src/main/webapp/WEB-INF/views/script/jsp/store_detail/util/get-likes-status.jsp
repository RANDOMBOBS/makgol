<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const getLikesStatus = async (userId, shopId) => {
        const url = "/store/likes?user_id=" + userId + "&shop_id=" + shopId;
        const {axios} = window;

        try {
            const {data} = await axios.get(url)
            return data.result
        } catch (err) {
            console.error(err);
        }
    }
</script>