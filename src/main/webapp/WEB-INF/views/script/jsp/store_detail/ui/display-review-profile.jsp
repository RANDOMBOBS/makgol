<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayReviewProfile = (item) => {
        const reviewProfileEle = $("<div>").addClass("review_profile");
        const userImageEle = $("<img>").addClass("user_image");
        const userNameEle = $("<span>").addClass("user_name");

        userImageEle.attr({src: "" +item.user_photo_path});
        userNameEle.text(item.name);

        reviewProfileEle.append(userImageEle);
        reviewProfileEle.append(userNameEle);

        return reviewProfileEle;
    };
</script>