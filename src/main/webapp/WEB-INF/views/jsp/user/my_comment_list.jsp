<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="my_comment_table">
    <colgroup>
        <col />
        <col />
    </colgroup>
    <thead>
    <tr>
        <th>
            <input type="checkbox" id="allMyCheckbox" onclick="allMyCheckbox()" />
        </th>
        <th>댓글</th>
    </tr>
</thead>
<tbody>
   <c:forEach var="item" items="${commentVos}">
   <tr>
     <td data-id="${item.id}"><input type="checkbox" class="eachCheckbox"/></td>
     <td>
        <c:url value='/board/suggestion/detail' var='detail_url'>
          <c:param name='b_id' value='${item.board_id}' />
        </c:url>
        <a href="${detail_url}">
          <p class="comment_content">${item.content}</p>
          <p class="comment_date">${item.date}</p>
          <p class="board_title">${item.title}</p>
        </a>
     </td>
   </tr>
   </c:forEach>
  </tbody>
</table>

<button type="button" onclick="deleteComment()">선택삭제</button>

