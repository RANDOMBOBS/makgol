<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const focusItem = () => {
        const registerInputEles = document.querySelectorAll("#register_form .input_area");
        registerInputEles.forEach((ele) => {
            const inputBorderEle = ele.parentNode.parentNode;

            ele.addEventListener("focus", () => {
                inputBorderEle.style.border = "2px solid #c7a669";
            });

            ele.addEventListener("blur", () => {
                inputBorderEle.style.border = "2px solid #ffe286";
            });
        });
    }
</script>