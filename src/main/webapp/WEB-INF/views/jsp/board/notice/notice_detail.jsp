<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
  </style>
    <meta charset="UTF-8" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />

  </head>
  <body>

    <jsp:include page="../../include/header.jsp"></jsp:include>

    <section>
      <div id="section_wrap">
        <div class="word">
          <h3>${boardVo.category}</h3>
        </div>
        <div class="notice_detail_div">
          <ul class="notice_detail_ul">
            <li>
              <table>
                <tr>
                  <td class="title">제목</td>
                  <td class="title_">${boardVo.title}</td>
                </tr>
                <tr>
                  <td class="title">작성자</td>
                  <td class="grade">${boardVo.grade}</td>
                </tr>
                <tr>
                  <td class="title">작성일</td>
                  <td class="date">${boardVo.date}</td>
                </tr>
                <tr>
                  <td class="title">내용</td>
                  <td class="contents">${boardVo.contents}</td>
                </tr>
              </table>
            </li>
          </ul>
        </div>

        <div class="buttons">
           <button><a href="/board/notice">목록</a></button>
        <%
           UsersRequestVo loginedAdminVo = (UsersRequestVo) session.getAttribute("loginedUserVo");
           if (loginedAdminVo != null) {
        %>
        <c:if test="${loginedUserVo.grade == '관리자'}">
          <c:url value="/board/modifyNotice" var="modify_url">
            <c:param name="b_id" value="${boardVo.b_id}" />
          </c:url>
          <button><a class="modify_notice_button" href="${modify_url}">수정</a></button>

          <c:url value="/board/deleteNotice" var="delete_url">
            <c:param name="b_id" value="${boardVo.b_id}" />
          </c:url>
          <button
          <a class="delete_notice_button" href="#javascript:;" onclick="deleteBoard(${boardVo.b_id},'${boardVo.title}')">삭제</a>
          </button>
          </c:if>
          <%
          }
          %>
        </div>
      </div>
    </section>

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
