<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>
    <thead>
      <tr>
        <th><input type="checkbox" id="allMyCheckbox" onclick="allMyCheckbox()" /></th>
        <th>카테고리</th>
        <th>글제목</th>
        <th>공감수</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${boardVos}">
          <tr>
            <td><input type="checkbox" class="eachCheckbox"/></td>
            <input type="hidden" value="${item.b_id}">
            <td>${item.category}</td>
            <td><c:url value='/board/suggestion/detail' var='detail_url'>
                        <c:param name='b_id' value='${item.b_id}' />
                </c:url> <a href="${detail_url}">${item.title}</a></td>
            <td>${item.sympathy}</td>
            <td>${item.date}</td>
            <td>${item.hit}</td>
          </tr>
        </c:forEach>
    </tbody>
</table>

<button type="button" onclick="deleteBoard()">삭제</button>

