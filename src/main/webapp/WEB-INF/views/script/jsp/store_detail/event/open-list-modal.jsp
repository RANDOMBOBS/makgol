<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../modal/list_review_image/list.jsp"></jsp:include>
<script>
    const openListModal = (e) => {
        $(".modal_cover").css({display: "block"});
        $(".list_modal").css({display: "flex"});

        list(e);

        const listImageCloseButtonEle = $(".list_modal .close_button");

        listImageCloseButtonEle.click(() => {
            const reviewImageListEle = $(".review_image_list");
            reviewImageListEle.empty();
            $(".modal_cover").css({display: "none"});
            $(".upload_modal").css({display: "none"});
        })
    };
</script>