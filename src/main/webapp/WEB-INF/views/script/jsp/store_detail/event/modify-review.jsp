<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const modifyReview = (e) => {
        $(e.target).css({color: "blue"})
        const reviewId = $(e.target).parent().parent().parent().parent().siblings("input").val();
        const reviewContent = $(e.target).parent().parent().parent().parent().siblings(".review_content").children(".content");
        const reviewText = reviewContent.text();

        const deleteButtonEle = $(e.target).parent().siblings(".delete_button")
        deleteButtonEle.prop("disabled", true);

        const reviewModifyElement = $("<textarea>");
        reviewModifyElement.val(reviewText);
        reviewModifyElement.addClass("content");

        reviewContent.replaceWith(reviewModifyElement);

        $(e.target).click(async () => {
            const modifiedReview = reviewModifyElement.val();

            const url = "/store/review_id/" + reviewId;
            const {axios} = window;

            try {
                await axios.put(url, {review: modifiedReview});
                const reviewContentEle = $("<div>").addClass("content");
                reviewContentEle.text(reviewModifyElement.val());

                reviewModifyElement.replaceWith(reviewContentEle);

                alert("리뷰를 수정하였습니다.")
                location.reload();
            } catch (err) {
                console.error(err);
                alert("리뷰 수정 에러");
            }
        })
    }
</script>