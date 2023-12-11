<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const requestStoreMenu = async (id) => {
        const url = "/store/menu_data/store_id/" + id;
        const {axios} = window;
        const {data} = await axios.get(url);

        return data.result;
    }
</script>