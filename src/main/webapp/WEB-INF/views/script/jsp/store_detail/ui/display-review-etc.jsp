<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayReviewEtc = (item) => {
        const reviewEtcEle = $("<div>").addClass("review_etc");

        const reviewImageBoxEle = $("<div>").addClass("review_image_box");
        const etcBoxEle = $("<div>").addClass("etc");
        const dateBoxEle = $("<div>").addClass("date_box");
        const optionBoxEle = $("<div>").addClass("option_box");

        const reviewImages = item.review_photo_path;

        const [date, time] = item.date.split(" ")

        dateBoxEle.append($("<div>").text(date));
        dateBoxEle.append($("<div>").text(time));

        const modifyIconEle = $("<i>").css({fontSize: "16px", marginRight: "5px"});
        const deleteIconEle = $("<i>").css({fontSize: "16px", marginRight: "5px"});

        modifyIconEle.addClass("fa-solid fa-pencil");
        deleteIconEle.addClass("fa-solid fa-trash");

        const modifyButtonEle = $("<button>").addClass("button modify_button");
        const deleteButtonEle = $("<button>").addClass("button delete_button");

        modifyButtonEle.click(()=>{alert("수정")});
        deleteButtonEle.click(()=>{alert("삭제")});

        modifyButtonEle.append(modifyIconEle);
        deleteButtonEle.append(deleteIconEle);

        optionBoxEle.append(modifyButtonEle);
        optionBoxEle.append(deleteButtonEle);

        etcBoxEle.append(dateBoxEle);
        etcBoxEle.append(optionBoxEle);

        reviewEtcEle.append(etcBoxEle);

        if (reviewImages.length >= 1) {
            const reviewImageEle = $("<img>").addClass("review_image");
            reviewImageEle.attr({src:"http://localhost:8090" + reviewImages[0]});
            reviewImageBoxEle.append(reviewImageEle);
            reviewEtcEle.append(reviewImageBoxEle);
        }

        return reviewEtcEle;
    };
</script>