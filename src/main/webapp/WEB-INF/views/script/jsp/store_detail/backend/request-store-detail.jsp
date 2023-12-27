<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const requestStoreDetail = async (id) => {
        const url = "/store/detail_data/store_id/" + id;
        const {axios} = window;
        const {data} = await axios.get(url);
        
        return data.result;
    }
</script>