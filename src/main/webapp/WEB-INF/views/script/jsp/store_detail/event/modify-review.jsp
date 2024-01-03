<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const modifyReview = (e) => {
        $(e.target).css({color: "blue"})
        const reviewId = $(e.target).parent().parent().parent().parent().siblings("input").val();
        const reviewContent = $(e.target).parent().parent().parent().parent().siblings(".review_content").children(".content");
        const reviewText = reviewContent.text();

        $(e.target).parent().siblings(".delete_button").prop("disabled", true);
        $("#text_review").prop("disabled", true)
        $("#submit_review").prop("disabled", true)
        $("#upload_image").prop("disabled", true)

        const reviewModifyEle = $("<textarea>");
        reviewModifyEle.val(reviewText);
        reviewModifyEle.addClass("content");

        reviewContent.replaceWith(reviewModifyEle);

        const reviewImageBoxEle = $(e.target).parent().parent().parent().siblings(".review_image_box");
        reviewImageBoxEle.click(() => {
            $(".modal_cover").css({display: "none"});
            $(".list_modal").css({display: "none"});

            openUploadModal(reviewId);
        })

        // 파란색 연필 버튼을 다시 눌렀을 때 실행
        $(e.target).click(async () => {
            const modifiedReview = reviewModifyEle.val();
            const modifiedReviewImages = reviewImageMap.get("reviewImages");

            if (!modifiedReview.length) return alert("최소 한글자 이상 써주세요!");

            const formData = new FormData();

            formData.append("review", modifiedReview);

            if (modifiedReviewImages) {
                reviewImages.forEach((file) => formData.append("reviewImages", file))
                reviewImageMap.clear();
            }

            const url = "/store/review_id/" + reviewId;
            const {axios} = window;

            try {
                await axios.put(url, formData, {headers: {'Content-Type': 'multipart/form-data'}});
                const reviewContentEle = $("<div>").addClass("content");
                reviewContentEle.text(reviewModifyEle.val());

                reviewModifyEle.replaceWith(reviewContentEle);

                alert("리뷰를 수정하였습니다.")
                location.reload();
            } catch (err) {
                console.error(err);
                alert("리뷰 수정 에러");
            }
        })
    }
</script>