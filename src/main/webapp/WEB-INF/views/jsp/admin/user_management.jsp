<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<link rel="stylesheet"
        	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        	crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />


</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>


	<h2>회원목록</h2>
	<div>
		<span>선택한 회원을</span> <select name="grade">
			<option value="준회원">준회원</option>
			<option value="정회원">정회원</option>
			<option value="우수회원">우수회원</option>
			<option value="관리자">관리자</option>
			<option value="블랙리스트">블랙리스트</option>
		</select> <span> 등급으로 변경합니다.</span>
		<button type="button" onclick="updateGrade()">변경하기</button>
	</div>
	<div class="allUserList"></div>

	<jsp:include page="../../script/jsp/user_management.jsp"></jsp:include>

	<script>
		userList()
	</script>

</body>
</html>
