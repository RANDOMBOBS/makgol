<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const submitReview = async (storeId, userId = String(85)) => {
        const review = $("#text_review").val();
        let reviewImages = reviewImageMap.get("reviewImages");

        if (!review.length) return alert("최소 한글자 이상 써주세요!");

        const formData = new FormData();

        formData.append("userId", userId);
        formData.append("storeId", storeId);
        formData.append("review", review);

        if (reviewImages) {
            reviewImages.forEach((file) => formData.append("reviewImages", file))
            reviewImageMap.clear();
        }

        const url = "/store/review";
        const {axios} = window;
        const {data} = await axios.post(url, formData, {headers: {'Content-Type': 'multipart/form-data'}})

        if (data.success) {
            alert("리뷰를 작성하였습니다.");
            location.reload();
        }
    }
</script>