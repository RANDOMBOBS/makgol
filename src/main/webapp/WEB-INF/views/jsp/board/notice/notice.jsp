<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>공지사항</h3>
			</div>
			<div class="book_list">
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
								<td>${fn:length(boardVo)-(status.index)}</td>
								<td>
								<c:url value='/board/detailNotice' var='detail_url'>
									<c:param name='b_id' value='${item.b_id}'/>
								</c:url><a href="${detail_url}" >${item.title}</a></td>
								<td>${item.name}</td>
								<td>${item.date}</td>
								<td>${item.hit}</td>
								<td>${item.sympathy}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 글쓰기 버튼 클릭시 페이지 이동 -->
				<a href="<c:url value='/board/noticeCreateForm'/>">글쓰기</a>			
		</div>
		
	</section>
	
</body>
</html>