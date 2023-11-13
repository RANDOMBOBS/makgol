<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>공감</th>
		</tr>
	</thead>
	<tbody>
		<!-- 각 게시판 ( 공지사항 ) 리스트를 보여줌 -->
		<c:forEach var="item" items="${boardVo}" varStatus="status">
			<tr>
				<!-- 공지사항 게시글 번호 목록 갯수따라 초기화 -->
				<td>${fn:length(boardVo)-(status.index)}</td>
				<!-- 게시글 제목 클릭시 게시글(b_id)가지고 게시글 정보로 이동 -->
				<td><c:url value='/board/detailNotice' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' /> 
					</c:url><a href="${detail_url}">${item.title}</a></td>
				<!-- 공지사항 게시판 작성자 (관리자) -->
				<td>${item.name}</td>
				<!-- 공지사항 게시판 날짜 -->
				<td>${item.date}</td>
				<!-- 공지사항 게시판 조회수 -->
				<td>${item.hit}</td>
				<!-- 공지사항 게시판 좋아요수 -->
				<td>${item.sympathy}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 글쓰기 버튼 클릭시 페이지 이동 -->
<c:url value='/board/noticeCreateForm' var='notice_create_url'>
	<c:param name='name' value='${item.name}' />
</c:url>
<a href="${notice_create_url}">글쓰기</a>
