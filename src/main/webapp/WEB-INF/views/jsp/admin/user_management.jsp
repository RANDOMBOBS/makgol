<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
  <link href="<c:url value='/resources/static/css/admin.css' />" rel="stylesheet" type="text/css" />

</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>


    <div id="user_management">
      <h1 class="management_page_title">회원목록</h1>
      <div class="user_list">
        <div class="change_role">
          <span>선택한 회원을 </span>
          <select name="grade">
            <option value="준회원">준회원</option>
            <option value="정회원">정회원</option>
            <option value="우수회원">우수회원</option>
            <option value="관리자">관리자</option>
            <option value="블랙리스트">블랙리스트</option>
          </select>
          <span> 등급으로 변경합니다.</span>
          <button type="button" onclick="updateGrade()">변경하기</button>
        </div>

	  <div class="all_user_list"></div>
     </div>
	<jsp:include page="../../script/jsp/user_management.jsp"></jsp:include>

	<script>
		userList();
	</script>

</body>
</html>
