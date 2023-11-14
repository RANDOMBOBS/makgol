<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value='/resources/static/css/modal/index.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/static/css/modal/login_modal.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/static/css/modal/register_modal.css' />" rel="stylesheet" type="text/css"/>
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
                <form action="" id="login_form" method="post">
                    <label>
                        <input
                                autocomplete="off"
                                class="input_area"
                                id="login_email"
                                name="userEmail"
                                placeholder="Email"
                                type="text"
                        />
                    </label>
                    <label>
                        <input
                                class="input_area"
                                id="login_password"
                                name="userPassword"
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
                <form action="" id="register_form" method="post">
                    <table>
                        <tr>
                            <td>
                                <div class="key">이메일</div>
                                <label>
                                    <input
                                            autocomplete="off"
                                            class="input_area"
                                            id="register_email"
                                            name="userEmail"
                                            placeholder="email"
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
                                <div class="key">비밀번호</div>
                                <label>
                                    <input
                                            class="input_area"
                                            id="register_password"
                                            name="userPassword"
                                            placeholder="password"
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
                                            placeholder="password confirm"
                                            type="password"
                                    />
                                </label>
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
                                            placeholder="name"
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
                                            placeholder="address"
                                            type="text"
                                    />
                                </label>
                                <div class="option_button">
                                    <button id="my_location">내 위치</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <input id="submit_register" type="submit" value="Register"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>