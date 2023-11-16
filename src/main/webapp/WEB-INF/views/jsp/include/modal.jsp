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
            <h1>Log In</h1>
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
                <button id="naver_login">네이버 로그인</button>
                <button id="kakao_login">카카오 로그인</button>
            </div>
        </div>
        <div class="modal_footer"></div>
    </div>
    <div class="register_modal">
        <div class="modal_head">
            <h1>Register</h1>
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
                            <td>
                                <div class="key">이메일</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_email"
                                            name="userEmail"
                                            placeholder="이메일을 입력해주세요"
                                            type="text"
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="email_validate">중복 확인</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="key">본인인증</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="auth_self"
                                            placeholder="인증번호 6자리를 입력해주세요"
                                            type="text"
                                            disabled
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="send_number" style="display: none">번호 발송</button>
                                </div>
                                <button id="check_auth">인증 확인</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="key">비밀번호</div>
                                <label>
                                    <input
                                            class="input_area"
                                            id="register_password"
                                            name="userPassword"
                                            placeholder="비밀번호를 입력해주세요"
                                            type="password"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="key">비밀번호 확인</div>
                                <label>
                                    <input
                                            class="input_area"
                                            id="register_password_confirm"
                                            name="userPasswordConfirm"
                                            placeholder="비밀번호를 다시 입력해주세요"
                                            type="password"
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="password_validate">일치 확인</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="key">이름</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_name"
                                            name="userName"
                                            placeholder="본인 이름을 입력해주세요"
                                            type="text"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="key">전화번호</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_phone"
                                            name="userPhone"
                                            placeholder="전화번호를 입력해주세요"
                                            type="text"
                                    />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
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
                                <button id="watch_map">지도 보기</button>
                                <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
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
            const modalCoverEle = $(".modal_cover");
            const registerModalEle = $(".register_modal");

            $(".login_modal").css({display: "none"});

            modalCoverEle.css({display: "block"});
            registerModalEle.css({display: "block"});
        })
    };

    const registerModal = () => {
        const registerModalCloseButtonEle = $(".register_modal .modal_head .close_button")

        registerModalCloseButtonEle.click(() => {
            $(".modal_cover").css({display: "none"});
            $(".register_modal").css({display: "none"});
        });

        const registerEmailInputEle = $("#register_email");
        registerEmailInputEle.focus(() => {
            $("#send_number").css({display: "none"});
        })

        const emailValidateButtonEle = $("#email_validate");
        emailValidateButtonEle.click((e) => {
            e.preventDefault();

            const reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

            const email = $('#register_email').val(); // 이메일 주소값 얻어오기!
            if (!reg_email.test(email)) {
                $('#email').focus();
                alert("이메일 형식에 맞게 입력해주세요");
                return;
            }

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
                }
            })
        });

        const sendNumberButtonEle = $("#send_number");
        sendNumberButtonEle.click((e) => {
            e.preventDefault();

            const email = $('#register_email').val();
            const authSelfInputEle = $("#auth_self");
            alert("인증번호가 전송되었습니다.")

            authSelfInputEle.attr({disabled: false});
            $("#check_auth").css({display: "block"});

            $.ajax({
                type: "POST",
                url: "<c:url value='/user/mailCheck'/>",
                data: JSON.stringify({email}),
                contentType: "application/json",
                dataType: "json",
                success(data) {
                    alert("인증번호가 전송되었습니다.");
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

            $.ajax({
                type: "POST",
                url: '<c:url value ="/user/authNumberCheck"/>',
                data: JSON.stringify({email, auth_number: randomNumber}),
                contentType: "application/json",
                dataType: "json",
                success(data) {
                    alert("인증을 성공하였습니다.")
                    checkAuthButtonEle.css({display: "none"})
                }, error(err) {
                    console.log(err)
                    alert("서버 오류입니다.")
                }
            })
        });

        const passwordValidateButtonEle = $("#password_validate");
        passwordValidateButtonEle.click((e) => {
            e.preventDefault();
            const password = $("#register_password").val();
            const passwordConfirm = $("#register_password_confirm").val();

            if (password === passwordConfirm) {
                alert("비밀번호가 일치합니다.")
            } else {
                alert("비밀번호가 일치하지 않습니다.")
            }
        });

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

        const myLocationButtonEle = $("#my_location");
        myLocationButtonEle.click((e) => {
            e.preventDefault();
            new daum.Postcode({
                oncomplete(data) {
                    $("#register_address").val(data.address);
                }
            }).open();
        });

        const submitRegisterButtonEle = $("#submit_register");
        submitRegisterButtonEle.click((e) => {
            e.preventDefault();

            const inputEmail = $("#register_email").val();
            const inputAuthSelf = $("#auth_self").val();
            const inputPassword = $("#register_password").val();
            const inputPasswordConfirm = $("#register_password_confirm").val();
            const inputName = $("#register_name").val();
            const inputPhone = $("#register_phone").val();
            const inputAddress = $("#register_address").val();

            const validateItems = [inputEmail, inputAuthSelf, inputPassword, inputPasswordConfirm, inputName, inputPhone, inputAddress]
            const hasNull = validateItems.map((item) => !item ? null : item).includes(null);

            if (hasNull) return alert("입력창에 공백이 포함되어있습니다.")


            alert("submit");
        })
    };

    loginModal();
    registerModal();
</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</body>
</html>