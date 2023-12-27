<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const findPasswordModal = () => {
        $(".find_password_modal .modal_head .close_button").click(() => {
            $(".modal_cover").css({display: "none"});
            $(".find_password_modal").css({display: "none"})
        })
    }
</script>