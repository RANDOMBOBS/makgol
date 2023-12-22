<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../ui/register-modal.jsp"></jsp:include>
<jsp:include page="./read-url.jsp"></jsp:include>
<jsp:include page="../logic/register-logic.jsp"></jsp:include>
<jsp:include page="../ui/focus-item.jsp"></jsp:include>
<script>
    const registerModalInit = () => {
        registerModal();
        readURL()
        registerLogic();
        focusItem();
    }

    registerModalInit();
</script>