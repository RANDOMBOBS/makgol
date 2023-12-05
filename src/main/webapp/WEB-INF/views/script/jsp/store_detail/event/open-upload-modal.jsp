<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const openUploadModal = () => {
        $(".modal_cover").css({display: "block"});
        $(".upload_modal").css({display: "flex"});

        const uploadImageCloseButtonEle = $(".upload_modal .close_button");

        uploadImageCloseButtonEle.click(() => {
            $(".modal_cover").css({display: "none"});
            $(".upload_modal").css({display: "none"});

            upload();
        })
    };
</script>