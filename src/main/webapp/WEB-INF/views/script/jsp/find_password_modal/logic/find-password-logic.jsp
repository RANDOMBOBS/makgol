<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const findPasswordLogic = () => {
        const findPasswordEmailStatusEle = $("#find_password_email_status");
        const findPasswordEmailInputErrorEle = $("#find_password_email_input_error");

        $("#find_password_email").keyup((event) => {
            const email = event.target.value;
            const regEmail = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;

            if (!(regEmail.test(email))) {
                findPasswordEmailInputErrorEle.css({visibility: "unset"})
                findPasswordEmailStatusEle.val("fail")
            } else {
                findPasswordEmailInputErrorEle.css({visibility: "hidden"})
                findPasswordEmailStatusEle.val("success")
            }
        })

        $("#submit_find_password").click((e) => {
            e.preventDefault();

            const inputEmail = $("#find_password_email").val();
            if (!inputEmail) return alert("입력창에 공백이 포함되어있습니다.");

            const emailStatus = $("#find_password_email_status").val();
            if (emailStatus.includes("fail")) return alert("확인 절차가 제대로 이루어지지 않았습니다.")

            $.ajax({
                type: "POST",
                url: "/user/findPassword/?email=" + inputEmail,
                processData: false,
                contentType: false,
                success() {
                    alert("이메일로 임시 비밀번호를 보냈습니다!")
                    window.location.href = "/";
                }, error() {
                    alert("이메일이 존재하지 않습니다.")
                }
            })
        })
    };
</script>