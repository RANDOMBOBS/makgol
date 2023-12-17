<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const increaseLikes = async ({userId, shopId}) => {
        const url = "/store/likes?user_id=" + userId + "&shop_id=" + shopId;
        const {axios} = window;

        try {
            await axios.put(url)

            alert("좋아요를 추가하였습니다.");
            location.reload();
        } catch (err) {
            console.error(err)
            alert("로그인 해주세요.")
        }
    };
</script>
