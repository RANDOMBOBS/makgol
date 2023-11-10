<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="../include/header.jsp"></jsp:include>

<c:set var="emailInfo" value="${loginedUsersRequestVo.email.split('@')}"/>
<c:set var="emailId" value="${emailInfo[0]}"/>
<c:set var="emailDomain" value="${emailInfo[1]}"/>

<div class="form-group email-form">
  <label>회원정보 수정</label>
  <form action="<c:url value='/user/modifyUserConfirm'/>" method="post" name="modify_user_info" enctype="multipart/form-data">
    <div class="input-group">
      <input type="text" class="form-control" name="userEmail1" id="userEmail1" value="${emailId}" readonly disabled>
      <select class="form-control" name="userEmail2" id="userEmail2" readonly disabled>
        <option value="@naver.com" <c:if test="${emailDomain == 'naver.com'}">selected</c:if>>@naver.com</option>
        <option value="@daum.net" <c:if test="${emailDomain == 'daum.net'}">selected</c:if>>@daum.net</option>
        <option value="@gmail.com" <c:if test="${emailDomain == 'gmail.com'}">selected</c:if>>@gmail.com</option>
        <option value="@hanmail.com" <c:if test="${emailDomain == 'hanmail.com'}">selected</c:if>>@hanmail.com</option>
        <option value="@yahoo.co.kr" <c:if test="${emailDomain == 'yahoo.co.kr'}">selected</c:if>>@yahoo.co.kr</option>
      </select>
    </div>
    <input placeholder="이름" id="name" name="name" value="${loginedUsersRequestVo.name}" readonly disabled><br />
    <input type="password" placeholder="비밀번호" id="password" name="password"><br />
    <input type="password" placeholder="비밀번호 확인" id="passwordCheck" name="passwordCheck">
    <button type="button" id="passwordCheckBtn" name="passwordCheckBtn">비밀번호 확인</button><br />
    <input placeholder="전화번호" id="phone" name="phone" value="${loginedUsersRequestVo.phone}"><br />
    <input type="file" id="photoFile" name="photoFile">
    <input type="hidden" name="id" value="${loginedUsersRequestVo.id}">
    <button type="button" onclick="ModifyUserInfo()">회원정보수정</button>
  </form>
</div>

<script>
  let pwCheck = true;
 jQ("#passwordCheckBtn").on("click", function () {
   console.log("Button clicked");
   let pw = jQ("#password").val();
   let pw2 = jQ("#passwordCheck").val();
   if (pw !== pw2) {
     alert("비밀번호가 같지 않습니다.");
   } else {
     pwCheck = false;
     alert("비밀번호가 일치합니다.");
   }
 });

  function ModifyUserInfo() {
    let form = document.modify_user_info;
    if (form.password.value === "") {
      alert("비밀번호를 입력해주세요");
      form.password.focus();
    } else if (form.passwordCheck.value === "") {
      alert("비밀번호 확인을 입력해주세요");
      form.passwordCheck.focus();
    } else if (pwCheck) {
      alert("비밀번호 확인 버튼을 눌러주세요");
    } else if (form.phone.value === "") {
      alert("전화번호를 입력해주세요");
      form.phone.focus();
    } else {
      form.submit();
    }
  }
</script>
</body>
</html>
