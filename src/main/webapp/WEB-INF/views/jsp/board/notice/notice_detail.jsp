<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
  </style>
    <meta charset="UTF-8" />
    <link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />

  </head>
  <body>

    <c:url value="/board/modifyNotice" var="modify_url">
      <c:param name="b_id" value="${boardVo.b_id}" />
    </c:url>
    <c:url value="/board/deleteNotice" var="delete_url">
      <c:param name="b_id" value="${boardVo.b_id}" />
    </c:url>

    <jsp:include page="../../include/header.jsp"></jsp:include>

    <section>
      <div id="board_detail">
        <div class="board_detail_top">
            <c:choose>
              <c:when test="${boardVo.category == 'notice'}">
                <h1 class="board_detail_title">공지사항</h1>
              </c:when>
            </c:choose>
        <div class="board_detail_button">
                <c:if test="${loginedUserVo.grade == '관리자'}">
                  <a href="${modify_url}">수정</a>
                  <a href="#javascript:;" onclick="deleteBoard(${boardVo.b_id},'${boardVo.title}')">삭제</a>
                </c:if>
                <a href="/board/notice">목록</a>
        </div>
        </div>
              <table class="board_detail_table">
                <tr class="text">
                  <td class="title">제목</td>
                  <td class="title_">${boardVo.title}</td>
                </tr>
                <tr class="text">
                  <td class="title">작성자</td>
                  <td class="grade">${boardVo.grade}</td>
                </tr>
                <tr class="text">
                  <td class="title">작성일</td>
                  <td class="date">${boardVo.date}</td>
                </tr>
                <tr class="content">
                  <td colspan="2" class="content_td">
                  ${boardVo.contents}</td>
                </tr>
              </table>

    </section>

    <jsp:include page="../../include/footer.jsp"></jsp:include>

    <jsp:include page="../../../script/jsp/notice.jsp"></jsp:include>

    <script type="text/javascript">
          function deleteBoard(b_id,title) {
          	let result = confirm('공지사항 ['+ title +'] 를(을) 정말 삭제 하시겠습니까?');
          	if (result){
          		window.location.href = "${delete_url}"
          }
         }
    </script>

  </body>
</html>
