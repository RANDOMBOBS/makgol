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
                                    <input style="display: none" type="file" id="photo" name="photo"/>
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
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<jsp:include page="../../script/jsp/register_modal/event/register-modal-init.jsp"></jsp:include>
<jsp:include page="../../script/jsp/login_modal/event/login-modal-init.jsp"></jsp:include>
</body>
</html>