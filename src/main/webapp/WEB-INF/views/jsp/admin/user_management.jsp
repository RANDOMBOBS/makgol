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
<style>
img {
	border-radius: 50%;
	width: 50px;
	height: 50px;
	border: 1px solid;
}
</style>
</head>
<body>
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
	<jsp:include page="script/jsp/user_management.jsp"></jsp:include>

	<script>
		userList()
	</script>

</body>
</html>
