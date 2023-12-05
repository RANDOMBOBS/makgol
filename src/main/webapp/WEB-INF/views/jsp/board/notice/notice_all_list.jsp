<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<style>

.noticeLikeBtn{
    width:200px;
}
.fa-solid{
    font-size:100px;
}
.icon {
  position: absolute;
  top: calc(50% - 64px);
  left: calc(50% - 64px);
  width: 128px;
  height: 128px;
  cursor: pointer;
  color: rgba(76, 76, 76, .9);
  transform: scale(1.1);
  transition: all .1s ease-out, color .3s;
  z-index: 5;
  vertical-align: middle;
  text-align: center;
}

.icon:hover {
  color: rgba(76, 76, 76, 1);
  transition: all .1s ease-out;
}

.icon:active {
  transform: scale(0.9);
  transition: all .1s ease-out;
}

.icon i {
  margin-top: 32px;
  font-size: 64px;
  pointer-events: none;
}

.unselectable {
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    outline: 0;
}

</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>공감수</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${boardVo}" varStatus="status">
			<tr>
				<td>${fn:length(boardVo)-(status.index)}</td>
				<td><c:url value='/board/detailNotice' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' />
					</c:url> <a href="${detail_url}">${item.title}</a></td>
				<td>${item.sympathy}</td>
				<td>${item.grade}</td>
				<td>${item.date}</td>
				<td>${item.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 글쓰기 버튼 클릭시 페이지 이동 -->
<%
   UsersRequestVo loginedAdminVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
   if (loginedAdminVo != null) {
%>
    <c:if test="${loginedUsersRequestVo.grade == '관리자'}">
            <a href="/board/noticeCreateForm">글쓰기</a>
    </c:if>
<%
   }
%>
