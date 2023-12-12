<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const deleteReview = async (e) => {
        const reviewId = $(e.target).parent().parent().parent().parent().siblings("input").val()

        const url = "/store/review_id/" + reviewId;
        const {axios} = window;

        try {
            await axios.delete(url);
            alert("리뷰를 삭제하였습니다.");
            location.reload();
        } catch (err) {
            alert("리뷰 삭제 에러")
        }

    }
</script>