<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const loginModal = () => {
        $(".login_modal .modal_head .close_button").click(() => {
            $(".modal_cover").css({display: "none"})
            $(".login_modal").css({display: "none"})
        })
        
        $(".user_option span:nth-child(1)").click(() => {
            $(".login_modal").css({display: "none"});
            $(".register_modal").css({display: "block"});
        })
    };
</script>