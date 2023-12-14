<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/resources/static/css/modal/index.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/static/css/modal/login_modal.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/static/css/modal/register_modal.css' />" rel="stylesheet" type="text/css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=46bc308803f4e404bdf4521f4af2f32e&libraries=services"></script>

</head>
<body>
<div class="modal_cover">
    <div class="login_modal">
        <div class="modal_head">
            <h1>로그인</h1>
            <button class="close_button">X</button>
        </div>
        <div class="modal_body">
            <div class="login_wrapper">
                <form action="/user/loginConfirm" id="login_form" method="post">
                    <label>
                        <input
                                autocomplete="off"
                                class="input_area"
                                id="login_email"
                                name="email"
                                placeholder="Email"
                                type="text"
                        />
                    </label>
                    <label>
                        <input
                                class="input_area"
                                id="login_password"
                                name="password"
                                placeholder="Password"
                                type="password"
                        />
                    </label>
                    <c:set var="xsrfToken" value="${cookie['XSRF-TOKEN'].value}"/>
                    <input type="hidden" name="_csrf" value="${xsrfToken}"/>
                    <div id="save_id">
                        <label for="remember-check">
                            <input id="remember-check" type="checkbox"/>
                            <span>아이디 저장하기 </span>
                        </label>
                    </div>
                    <input id="submit_login" type="submit" value="Login"/>
                </form>
            </div>
            <div class="user_option">
                <span>회원가입</span>
                <span>아이디 & 비밀번호 찾기</span>
            </div>
            <div class="social_login">
                <a id="naver_login" href="/oauth2/authorization/naver">네이버 로그인</a>
                <a id="kakao_login" href="/oauth2/authorization/kakao">카카오 로그인</a>
            </div>
        </div>
        <div class="modal_footer"></div>
    </div>
    <div class="register_modal">
        <div class="modal_head">
            <h1>회원가입</h1>
            <button class="close_button">X</button>
        </div>
        <div class="modal_body">
            <div class="register_wrapper">
                <form action="#" id="register_form" method="post">
                    <table>
                        <tr>
                            <div class="user_image_select">
                                <label id="photo_label" for="photo">
                                    <input style="display: none" type="file" id="photo" name="photo"
                                           onchange="readURL(this)"/>
                                </label>
                            </div>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">이메일</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_email"
                                            name="userEmail"
                                            placeholder="이메일을 입력해주세요"
                                            type="text"
                                            maxlength="30"
                                    />
                                </label>
                                <div class="option_button" style="display: none">
                                    <button id="email_validate">중복 확인</button>
                                </div>
                            </td>
                            <td id="email_input_error" class="input_error">이메일 형식을 지켜주세요!</td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">본인인증</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="auth_self"
                                            placeholder="인증번호 6자리를 입력해주세요"
                                            type="text"
                                            disabled
                                            maxlength="6"
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="send_number" style="display: none">번호 발송</button>
                                </div>
                                <button id="check_auth">인증 확인</button>
                                <input type="hidden" id="auth_status">
                            </td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">비밀번호</div>
                                <label>
                                    <input
                                            class="input_area"
                                            id="register_password"
                                            name="userPassword"
                                            placeholder="비밀번호를 입력해주세요"
                                            type="password"
                                            maxlength="25"
                                    />
                                </label>
                            </td>
                            <td id="password_input_error" class="input_error">문자 숫자를 포함시켜주세요!</td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">비밀번호 확인</div>
                                <label>
                                    <input
                                            class="input_area"
                                            id="register_password_confirm"
                                            name="userPasswordConfirm"
                                            placeholder="비밀번호를 다시 입력해주세요"
                                            type="password"
                                            maxlength="25"
                                    />
                                </label>
                                <input type="hidden" id="password_status">
                            </td>
                            <td id="re_password_input_error" class="input_error">비밀번호가 일치하지 않아요!</td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">이름</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_name"
                                            name="userName"
                                            placeholder="본인 이름을 입력해주세요"
                                            type="text"
                                            maxlength="4"
                                    />
                                </label>
                                <input type="hidden" id="name_status">
                            </td>
                            <td id="name_input_error" class="input_error">이름은 한글로만 써주세요!</td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">전화번호</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_phone"
                                            name="userPhone"
                                            placeholder="전화번호를 입력해주세요"
                                            type="text"
                                            maxlength="11"
                                    />
                                </label>
                                <input type="hidden" id="phone_status">
                            </td>
                            <td id="phone_input_error" class="input_error">-를 포함시키지 말아요!</td>
                        </tr>
                        <tr>
                            <td class="input_box">
                                <div class="key">주소</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_address"
                                            name="userAddress"
                                            placeholder="내 위치를 클릭하면 주소입력창이 표시됩니다"
                                            type="text"
                                            disabled
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="my_location">내 위치</button>
                                </div>
                                <input type="hidden" id="longitude">
                                <input type="hidden" id="latitude">
                            </td>
                        </tr>
                    </table>
                    <input id="submit_register" type="submit" value="Register"/>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const readURL = (input) => {
        console.log(input)
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $("#photo_label").css({
                    backgroundColor: "black",
                    backgroundImage: "url(" + e.target.result + ")",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "0 0",
                    backgroundSize: "cover"
                })
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    const loginModal = () => {
        const loginModalCloseButtonEle = $(".login_modal .modal_head .close_button");

        loginModalCloseButtonEle.click(() => {
            $(".modal_cover").css({display: "none"})
            $(".login_modal").css({display: "none"})
        })

        const registerModalButtonEle = $(".user_option span:nth-child(1)");

        registerModalButtonEle.click(() => {
            $(".login_modal").css({display: "none"});
            $(".register_modal").css({display: "block"});
        })
    };

    const registerModal = () => {
        $(".register_modal .modal_head .close_button").click(() => {
            $(".modal_cover").css({display: "none"});
            $(".register_modal").css({display: "none"});
        });

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


    loginModal();
    registerModal();
</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</body>
</html>