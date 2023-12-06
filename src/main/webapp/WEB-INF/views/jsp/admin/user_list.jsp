<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>
    <colgroup>
    <col />
    <col />
    <col />
    <col />
    <col />
    <col />
    <col />
    </colgroup>
    <thead>
        <tr>
            <th><input type="checkbox" id="allCheckbox" onclick="allCheckbox()" /></th>
            <th>회원번호</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>프로필사진</th>
            <th>등급</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${userVos}" >
            <tr class="one_user">
                <td><input type="checkbox" class="eachCheckbox" /></td>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.email}</td>
                <td>${item.phone}</td>
                <td><img src="<c:url value='http://localhost:8090${item.photo_path}'/>"</td>
                <td>${item.grade}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>




<jsp:include page="../../script/jsp/user_management.jsp"></jsp:include>

