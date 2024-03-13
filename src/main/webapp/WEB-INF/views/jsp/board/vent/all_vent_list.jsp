<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@page import="com.org.makgol.boards.vo.PageVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Date" %>
<%--<c:set var="pgn" value="${pageGroup}" />--%>
<%--<c:set var="page" value="${pageNum}" />--%>
<%--<c:set var="amount" value="${scale}" />--%>
<%--<c:set var="pageNums" value="10" />--%>
<%--<c:set var="total_page" value="${Math.ceil(totArticles/amount)}" />--%>
<%--<c:set var="tpgn" value="${Math.ceil(total_page/amount)}" />--%>
<%--<c:set var="start" value="${(pageNum-1)*pageNums }" />--%>
<%--<c:set var="last" value="${pageNum*amount }" />--%>

<c:set var="number" value="${pageVo.totArticles - pageVo.start}" />



<%
	Date currentDate = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String today = dateFormat.format(currentDate);
%>


<table>
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>공감</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${reviewListAll}">
		<tr>
			<td>${number }</td>
			<td>
				<c:url value='/board/vent/detail' var='detail_url'>
					<c:param name='id' value='${item.id}' />
				</c:url>
				<a href="${detail_url}">${item.title}</a>
			</td>
			<td>${item.name}</td>

			<c:choose>
				<c:when test="${fn:contains(item.date, today)}">
					<td>${item.date.substring(11,16)}</td>
				</c:when>
				<c:otherwise>
					<td>${item.date.substring(0,10)}</td>
				</c:otherwise>
			</c:choose>

			<td>${item.hit}</td>
			<td>${item.sympathy}</td>
		</tr>
		<c:set var="number" value="${number=number-1 }" />
	</c:forEach>
	</tbody>
</table>

<!-- 페이징 -->
<div class="paging" style="text-align: center; width: 60%; margin:0 auto" >
	<div class="cl2" style="text-align: center; margin-top: 30px;">

		<a href="javascript:;" onclick="pageGrouping(1, 1, 0, 0)" class="firstpage  pbtn">처음으로</a>
		<c:if test="${pageVo.pgn != 1}">
			<a href="javascript:;" onclick="pageGrouping(${pageVo.pgn}, 1, 0, 0)" class="prevpage  pbtn">
				이전
			</a>
		</c:if>


		<c:forEach var="i" begin="${(pageVo.pgn-1) * pageVo.pageNums + 1}" end="${(pageVo.pgn * pageVo.pageNums < pageVo.total_page) ? pageVo.pgn*pageVo.pageNums : pageVo.total_page}">
			<c:choose>
				<c:when test="${pageVo.page == i }">
					<a href="javascript:;">
						<span class="pagenum currentpage">${i}</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:;" onclick="allBoardList(${pageVo.pgn}, ${i})">
						<span class="pagenum">${i}</span>
					</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${pageVo.pgn != pageVo.tpgn}">
			<a href="javascript:;" onclick="pageGrouping(${pageVo.pgn}, 2, ${pageVo.tpgn}, ${pageVo.total_page})" class="nextpage  pbtn">
				다음
			</a>
		</c:if>
		<c:if test="${pageVo.page<pageVo.total_page }">
			<a href="javascript:;" onclick="pageGrouping(${pageVo.tpgn}, 2, ${pageVo.tpgn}, ${pageVo.total_page})"  class="lastpage  pbtn">
				끝으로
			</a>
		</c:if>
	</div>
</div>

<c:if test="${login == true}">
	    <a href="<c:url value='/board/suggestion/create'/>"><i class="fa-regular fa-pen-to-square"></i> 글쓰기</a><br>
	<br>
</c:if>