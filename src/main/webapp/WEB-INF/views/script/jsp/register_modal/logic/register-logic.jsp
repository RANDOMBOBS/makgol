<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const registerLogic = () => {
        const registerEmailEle = $("#register_email");
        const emailStatusEle = $("#email_status");

        registerEmailEle.keyup((event) => {
            const email = event.target.value;
            const reg_email = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;


            if (!(reg_email.test(email))) {
                $("#email_input_error").css({visibility: "unset"})
                $("#email_validate").parent(".option_button").css({display: "none"})
            } else {
                $("#email_input_error").css({visibility: "hidden"})
                $("#email_validate").parent(".option_button").css({display: "flex"})
            }
        })

        registerEmailEle.focusin(() => {
            const authSelfEle = $("#auth_self");
            authSelfEle.val("");
            authSelfEle.attr({disabled: true});
            $("#send_number").css({display: "none"})
        })

        $("#email_validate").click((e) => {
            e.preventDefault();

            const email = registerEmailEle.val();

            $.ajax({
                type: "POST",
                url: '<c:url value ="/user/mailCheckDuplication"/>',
                data: {email},
                dataType: "json",
                success(data) {
                    alert("사용가능한 이메일입니다.")
                    $("#send_number").css({display: "block"});
                },
                error(err) {
                    if (err.status >= 500) {
                        alert("서버 에러")
                        return;
                    }
                    alert("중복된 이메일입니다.")
                    emailStatusEle.val("fail")
                }
            })
        });

        $("#send_number").click((e) => {
            e.preventDefault();

            const email = $('#register_email').val();
            const authSelfInputEle = $("#auth_self");

            alert("인증번호가 전송되었습니다. 잠시만 기다려주세요.");

            $.ajax({
                type: "POST",
                url: "<c:url value='/user/mailCheck'/>",
                data: JSON.stringify({email}),
                contentType: "application/json",
                dataType: "json",
                success(data) {
                    authSelfInputEle.attr({disabled: false});
                    $("#check_auth").css({display: "block"});
                }, error(err) {
                    alert("서버 오류입니다.")
                }
            })
        })

        const checkAuthButtonEle = $("#check_auth");
        checkAuthButtonEle.click((e) => {
                e.preventDefault();
                const email = $("#register_email").val();
                const randomNumber = $("#auth_self").val();
                const authStatusEle = $("#auth_status");

                $.ajax({
                    type: "POST",
                    url: '<c:url value ="/user/authNumberCheck"/>',
                    data: JSON.stringify({email, auth_number: randomNumber}),
                    contentType: "application/json",
                    dataType: "json",
                    success(data) {
                        alert("인증을 성공하였습니다.")
                        checkAuthButtonEle.css({display: "none"})
                        authStatusEle.val("success");
                    }, error(err) {
                        authStatusEle.val("fail");
                        console.error(err)
                        if (err.status >= 500) {
                            return alert("서버 에러");
                        }
                        alert("유효하지 않은 인증번호입니다.")
                    }

                })
            }
        )

        // const reg_pw1 = /^[a-z0-9_-]{6,18}$/; // 단순 6~18자리
        // const reg_pw2 = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{6,24}/; // 문자와 특수문자 조합의 6~24 자리
        // const reg_pw3 = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/; // 문자, 숫자 1개이상 포함, 8자리 이상
        let currentPassword;
        $("#register_password").keyup((event) => {
            const password = event.target.value;
            const regPassword = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/;

            if (!(regPassword.test(password))) {
                $("#password_input_error").css({visibility: "unset"})
            } else {
                $("#password_input_error").css({visibility: "hidden"})
                currentPassword = password;
            }
        })

        $("#register_password_confirm").keyup((event) => {
            const rePassword = event.target.value;

            if (currentPassword !== rePassword) {
                $("#re_password_input_error").css({visibility: "unset"})
                $("#password_status").val("fail")
            } else {
                $("#re_password_input_error").css({visibility: "hidden"})
                $("#password_status").val("success")
            }
        })

        $("#register_name").keyup((event) => {
            const name = event.target.value;
            const regName = /^[가-힣]+$/;

            if (!regName.test(name)) {
                $("#name_input_error").css({visibility: "unset"})
                $("#name_status").val("fail");
            } else {
                $("#name_input_error").css({visibility: "hidden"})
                $("#name_status").val("success")
            }
        })

        $("#register_phone").keyup((event) => {
            const phone = event.target.value;
            const regPhone = /^[0-9]{8,13}$/;

            if (!regPhone.test(phone)) {
                $("#phone_input_error").css({visibility: "unset"})
                $("#phone_status").val("fail")
            } else {
                $("#phone_input_error").css({visibility: "hidden"})
                $("#phone_status").val("success")
            }
        })


        const myLocationButtonEle = $("#my_location");
        myLocationButtonEle.click((e) => {
            e.preventDefault();

            const {daum: {Postcode}} = window;
            const {daum: {maps: {services: {Geocoder}}}} = window;
            const {daum: {maps: {services: {Status: {OK}}}}} = window;

            new Postcode({
                oncomplete(data) {
                    $("#register_address").val(data.address);
                    const {addressSearch} = new Geocoder();
                    addressSearch(data.address, (result, status) => {
                        if (status === OK) {
                            const {x, y} = result[0];
                            $("#longitude").val(x);
                            $("#latitude").val(y);
                        }
                    })
                }
            }).open();
        });

        const submitRegisterButtonEle = $("#submit_register");
        submitRegisterButtonEle.click((e) => {
            e.preventDefault();

            const formData = new FormData();

            const inputEmail = $("#register_email").val();
            const inputAuthSelf = $("#auth_self").val();
            const inputPassword = $("#register_password").val();
            const inputPasswordConfirm = $("#register_password_confirm").val();
            const inputName = $("#register_name").val();
            const inputPhone = $("#register_phone").val();
            const inputAddress = $("#register_address").val();

            const validateItems = [inputAuthSelf, inputPassword, inputPasswordConfirm, inputName, inputPhone, inputAddress]
            const hasNull = validateItems.map((item) => !item ? null : item).includes(null);


            if (hasNull) return alert("입력창에 공백이 포함되어있습니다.");

            const authStatus = $("#auth_status").val();
            const passwordStatus = $("#password_status").val();
            const nameStatus = $("#name_status").val();
            const phoneStatus = $("#phone_status").val();

            const statusItems = [authStatus, passwordStatus, nameStatus, phoneStatus];
            const hasFail = statusItems.includes("fail");

            if (hasFail) return alert("확인 절차가 제대로 이루어지지 않았습니다.")

            const photo = jQuery("#photo")[0].files[0];
            const longitude = jQuery("#longitude").val();
            const latitude = jQuery("#latitude").val();

            formData.append("email", inputEmail);
            formData.append("password", inputPassword);
            formData.append("name", inputName);
            formData.append("phone", inputPhone);
            formData.append("address", inputAddress);
            formData.append("longitude", longitude);
            formData.append("latitude", latitude);

            if (photo != null) {
                formData.append("photoFile", photo);
            }

            $.ajax({
                type: "POST",
                url: "/user/join",
                data: formData,
                processData: false,
                contentType: false,
                success(data) {
                    alert("회원가입 되었습니다!");
                    window.location.href = "";
                }, error(err) {
                    alert("서버 에러");
                }
            });
        })
    }
</script>