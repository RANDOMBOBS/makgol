<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../ui/find-password-modal.jsp"></jsp:include>
<jsp:include page="../logic/find-password-logic.jsp"></jsp:include>
<script>
    const findPasswordModalInit = () => {
        findPasswordModal();
        findPasswordLogic();
    }

    findPasswordModalInit();
</script>