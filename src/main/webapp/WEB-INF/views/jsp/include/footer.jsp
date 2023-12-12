<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<c:url value='/resources/static/css/footer.css' />" rel="stylesheet" type="text/css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap" rel="stylesheet">
</head>
<body>
    <footer class="footer">
        <div class="document">
            <ul class="document_ul">
                <li><a href="#" alt="회사소개">회사소개</a></li>
                <li><a href="#" alt="개인정보처리방침">개인정보처리방침</a></li>
                <li><a href="#" alt="이용약관">이용약관</a></li>
            </ul>
        </div>
        <div class="iconBox">
            <ul class="icon_ul">
                <li onclick=window.open("https://www.instagram.com") class="instagram"><span class="span_instagram">Instagram</span><i class="fa-brands fa-instagram"></i></li>
                <li onclick=window.open("https://twitter.com") class="twitter"><span class="span_twitter">Twitter</span><i class="fa-brands fa-twitter"></i></li>
                <li onclick=window.open("https://github.com") class="github"><span class="span_github">Github</span><i class="fa-brands fa-github"></i></li>
                <li onclick=window.open("https://www.facebook.com") class="facebook"><span class="span_facebook">Facebook</span><i class="fa-brands fa-facebook"></i></li>
                <li onclick=window.open("https://www.youtube.com") class="youtube"><span class="span_youtube">YouTube</span><i class="fa-brands fa-youtube"></i></li>
            </ul>
        </div>
        <div class="information">
            <ul class="information_ul">
                <li>(주)랜덤밥스</li>
                <li>대표이사:이승훈</li>
                <li>사업자등록번호:2023-06-28</li>
            </ul>
            <ul class="information_ul">
                <li>서울 강남구 강남대로96길 16 4층</li>
                <li>대표전화:02-6952-0717</li>
                <li>팩스:02-6907-6801</li>
            </ul>
        </div>
    </footer>
</body>
</html>