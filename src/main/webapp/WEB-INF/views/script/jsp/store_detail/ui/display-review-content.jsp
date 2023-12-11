<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayReviewContent = (item) => {
        const reviewContentEle = $("<div>").addClass("review_content");
        const contentEle = $("<div>").addClass("content");

        contentEle.text(item.content);
        reviewContentEle.append(contentEle);

        return reviewContentEle;
    };
</script>