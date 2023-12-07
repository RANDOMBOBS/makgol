<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../modal/upload_review_image/upload.jsp"></jsp:include>
<script>
    const openUploadModal = () => {
        $(".modal_cover").css({display: "block"});
        $(".upload_modal").css({display: "flex"});
        $(".list_modal").css({display: "none"});

        upload();

        const uploadImageCloseButtonEle = $(".upload_modal .close_button");

        uploadImageCloseButtonEle.click(() => {
            $(".modal_cover").css({display: "none"});
            $(".upload_modal").css({display: "none"});
        })
    };
</script>