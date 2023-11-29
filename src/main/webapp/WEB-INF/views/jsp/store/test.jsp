<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-11-16
  Time: 오후 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>카테고리 리스트</h1>
<h4><a id="redirect">업장 리스트로 이동</a></h4>
</body>
<script>
    const keyword = prompt("카테고리를 정해주세요.: ");

    const request = {
        myX: 127.0282905480970000000000,
        myY: 37.4998293543379000000000,
        keyword,
    };

    const aEle = document.querySelector("#redirect");
    const redirectUri = "http://localhost:8090/store/list?x=" + request.myX + "&y=" + request.myY + "&keyword=" + request.keyword;
    aEle.setAttribute("href", redirectUri);
</script>
</html>
