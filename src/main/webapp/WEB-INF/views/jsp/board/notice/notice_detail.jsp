<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
  
  <style>
  ul,li{
  	list-style:none;
  }
  
  </style>
    <meta charset="UTF-8" />

    <script type="text/javascript">

      function deleteBoard(b_id,title) {
      	console.log('deleteNotice() CALLED!!');
      	let result = confirm('공지사항 ['+ title +'] 를(을) 정말 삭제 하시겠습니까?');
      	if (result){
      		location.href = "<c:url value='/board/deleteNotice?b_id="+ b_id +"'/>";
      }
    </script>
  </head>
  <body>
    <section>
      <div id="section_wrap">
        <div class="word">
          <h3>${boardVo.category}</h3>
        </div>
        <div class="notice_detail">
          <ul>
            <li>
              <table>
                <tr>
                  <td>제목</td>
                  <td>${boardVo.title}</td>
                </tr>
                <tr>
                  <td>작성자</td>
                  <td>${boardVo.name}</td>
                </tr>
                <tr>
                  <td>작성일</td>
                  <td>${boardVo.date}</td>
                </tr>
                <tr>
                  <td>내용</td>
                  <td>${boardVo.contents}</td>
                </tr>
              </table>
            </li>
          </ul>
        </div>
        <div class="buttons">
          <c:url value="/board/notice" var="notice_url">
            <c:param name="b_id" value="${boardVo.b_id}" />
          </c:url>
          <a class="notice_button" href="${notice_url}">목록</a>

          <c:url value="/board/modifyNotice" var="modify_url">
            <c:param name="b_id" value="${boardVo.b_id}" />
          </c:url>
          <a class="modify_notice_button" href="${modify_url}">수정</a>

          <c:url value="/board/deleteNotice" var="delete_url">
            <c:param name="b_id" value="${boardVo.b_id}" />
          </c:url>
          <a
            class="delete_notice_button"
            href="${delete_url}"
            onclick="deleteBoard(${boardVo.b_id},'${boardVo.title}')"
            >삭제</a
          >
        </div>
      </div>
    </section>
  </body>
</html>
