<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>

    <section>
     <div id="todaymenu_main_div">
        <ul class="todaymenu_main_ul">
          <li>오늘의메뉴</li>
          <li>한식</li>
          <li>양식</li>
          <li>중식</li>
          <li>분식</li>
          <li>일식</li>
          <li>카페/디저트</li>
        </ul>
      </div>
      <div class="todaymenu_list_div">
        
      </div>
    </section>
</body>
</html>