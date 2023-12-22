<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../ui/login-modal.jsp"></jsp:include>
<jsp:include page="../logic/save-email.jsp"></jsp:include>
<script>
    const loginModalInit = () => {
        loginModal();
        saveEmail();
    }

    loginModalInit();
</script>