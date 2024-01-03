<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const requestStoreMenu = async (id) => {
        const url = "/store/menu_data/store_id/" + id;
        const {axios} = window;
        
        try {
            const {data} = await axios.get(url);
            return data.result;
        } catch (err) {
            console.error(err)
        }
    }
</script>