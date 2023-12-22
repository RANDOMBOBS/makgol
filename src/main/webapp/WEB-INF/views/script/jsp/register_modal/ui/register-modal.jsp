<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const registerModal = () => {
        $(".register_modal .modal_head .close_button").click(() => {
            $(".modal_cover").css({display: "none"});
            $(".register_modal").css({display: "none"});
        });
    }
</script>