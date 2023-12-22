<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <meta charset="UTF-8" />
    <title>막내야 골라봐 | 에러페이지 (ERROR)</title>
    <style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }
      .errorpage {
        text-align: center;
        height: 500px;
        width: 400px;
        margin: 30px auto;
      }
      span {
              font-weight: bold;
              font-size: 35px;
              text-shadow: 2px 4px 2px gray;
            }
      p {
              font-weight: bold;
              font-size: 20px;
            }

a {
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: inherit;
    padding-top: 50px;
}
a img {
    width: 700px;

}
    </style>
  </head>
  <body>
  <a href="http://www.makgol.com"><img id="logoimage" src="<c:url value='/resources/static/image/default/mainLogo.png' />"></a>
    <div class="errorpage">
      <img id="image" src="<c:url value='/resources/static/image/default/error.gif' />">
      <span>에러 페이지</span>
    <c:if test="${not empty exception}">
          <br><br>
          <p>${exception.message}</p>
        </c:if>
    </div>


    <script>
        // JavaScript를 사용하여 이미지를 무한 반복으로 변경
        let image = document.getElementById("image");
        let loopCount = 0;
        
        function loopImage() {
            if (loopCount < 10) { // 루프를 원하는 횟수로 설정
                image.src = "<c:url value='/resources/static/image/default/error.gif' />"; // 이미지를 다시 로드
                loopCount++;
                setTimeout(loopImage, 4000); // 4초마다 반복
            }
        }
        loopImage(); // 루프 시작
    </script>
  </body>
</html>
