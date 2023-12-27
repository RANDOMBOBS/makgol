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

        let currentPassword;
        const findPasswordPasswordInputErrorEle = $("#find_password_password_input_error");
        $("#new_password").keyup((event) => {
            const password = event.target.value;
            const regPassword = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/;

            if (!(regPassword.test(password))) {
                findPasswordPasswordInputErrorEle.css({visibility: "unset"})
            } else {
                findPasswordPasswordInputErrorEle.css({visibility: "hidden"})
                currentPassword = password;
            }
        })

        const findPasswordRePasswordInputErrorEle = $("#find_password_re_password_input_error");
        const findPasswordPasswordStatusEle = $("#find_password_password_status");
        $("#new_password_confirm").keyup((event) => {
            const rePassword = event.target.value;

            if (currentPassword !== rePassword) {
                findPasswordRePasswordInputErrorEle.css({visibility: "unset"})
                findPasswordPasswordStatusEle.val("fail")
            } else {
                findPasswordRePasswordInputErrorEle.css({visibility: "hidden"})
                findPasswordPasswordStatusEle.val("success")
            }
        })

        $("#submit_find_password").click((e) => {
            e.preventDefault();

            const inputEmail = $("#find_password_email").val();
            const inputPassword = $("#new_password").val();
            const inputPasswordConfirm = $("#new_password_confirm").val();

            const validateItems = [inputEmail, inputPassword, inputPasswordConfirm]
            const hasNull = validateItems.map((item) => !item ? null : item).includes(null);

            if (hasNull) return alert("입력창에 공백이 포함되어있습니다.");

            const emailStatus = $("#find_password_email_status").val();
            const passwordStatus = $("#find_password_password_status").val();
            const statusItems = [emailStatus, passwordStatus];
            const hasFail = statusItems.includes("fail");

            if (hasFail) return alert("확인 절차가 제대로 이루어지지 않았습니다.")

            $.ajax({
                type: "POST",
                url: "/user/reset-password",
                data: {email: inputEmail, password: inputPassword},
                processData: false,
                contentType: false,
                success() {
                    alert("비밀번호가 변경되었습니다!")
                    window.location.href = "/";
                }, error() {
                    alert("이메일이 존재하지 않습니다.")
                }
            })
        })
    };
</script>