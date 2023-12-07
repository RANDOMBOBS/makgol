<%@page import="io.opentelemetry.exporter.logging.SystemOutLogExporter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>막내야 골라봐 | 건의게시판 (SUGGESTION)</title>
    <link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../../include/header.jsp"></jsp:include>
<c:url value="/board/suggestion" var="suggestion_url"/>
<c:url value="/board/suggestion/modify" var="modify_url">
    <c:param name="b_id" value="${boardVo.id}"/>
    <c:param name="name" value="${boardVo.name}"/>
</c:url>

<c:url value="/board/suggestion/delete" var="delete_url">
    <c:param name="b_id" value="${boardVo.id}"/>
    <c:param name="images" value="${boardVo.images}"/>
</c:url>
<div id="board_detail">
    <div class="board_detail_top">
        <c:choose>
            <c:when test="${boardVo.category == 'suggestion'}">
                <h1 class="board_detail_title">건의사항</h1>
            </c:when>
            <c:when test="${boardVo.category == 'notice'}">
                <h1 class="board_detail_title">공지사항</h1>
            </c:when>
            <c:otherwise>
                <h1 class="board_detail_title">하소연게시판</h1>
            </c:otherwise>
        </c:choose>
        <div class="board_detail_button">
            <c:if test="${boardVo.user_id == loginedUserVo.getId()}">
                <a href="${modify_url}">수정</a>
                <a href="#" onclick="boardDelete()">삭제</a>
            </c:if>
            <a href="${suggestion_url}">목록</a>
        </div>
    </div>
    <table class="board_detail_table">
        <tr class="text">
            <td>제목</td>
            <td>${boardVo.title}</td>
        </tr>
        <tr class="text">
            <td>작성자</td>
            <td>${boardVo.name}</td>
        </tr>
        <tr class="text">
            <td>작성일</td>
            <td>${boardVo.date}</td>
        </tr>
        <tr class="content">
            <td colspan="2">
                ${boardVo.contents}</td>
        </tr>


        <c:if test="${not empty boardVo.images}">
            <c:forEach var="item" items="${boardVo.images}">
                <tr class="image">
                    <td colspan="2"><img src="http://localhost:8090${item}"></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>


    <div class="board_like">
        <label for="like">
            <input type="checkbox" id="like" style="display: none" data-b-id="${boardVo.id}" data-user-id="${loginedUserVo.id}" />
            <i class="fa-regular fa-thumbs-up"> ${boardVo.sympathy}</i>
        </label>
    </div>


<form name="create_comment_form" class="create_comment_form">
    <c:choose>
        <c:when test="${loginedUserVo != null}">
            <input type="text" name="nickname" placeholder="닉네임"/>
            <div class="create_comment_area">
                <input type="hidden" name="board_id" value="${boardVo.id}"/>
                <input type="hidden" name="user_id" value="${loginedUserVo.getId()}"/>
                <input type="hidden" name="grade" value="${loginedUserVo.getGrade()}">
                <textarea name="contents" placeholder="댓글을 입력해주세요."></textarea>
                <button type="button" onclick="createCommentForm()">등록</button>
            </div>
        </c:when>

        <c:otherwise>
            <input type="hidden" name="board_id" value="${boardVo.id}"/>
            <input type="text" name="nickname" placeholder="닉네임" disabled/>
            <textarea name="contents" placeholder="로그인 후 댓글 작성이 가능합니다." disabled></textarea>
        </c:otherwise>
    </c:choose>
</form>

<div class="boardCommentList"></div>
</div>

<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>


<script>
    if (user_id) {
        userLikeStatus(b_id, user_id);
    }


    comList();


    function boardDelete() {
        if (window.confirm('글을 삭제하시겠습니까?')) {
            window.location.href = "${delete_url}";
        }
    }


</script>


</body>
</html>
