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
    <title>막내야 골라봐 | 404에러페이지 (ERROR)</title>
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
        margin: 200px auto;
      }
      span {
        font-weight: bold;
        font-size: 35px;
        text-shadow: 2px 4px 2px gray;
      }
    </style>
  </head>
  <body>
    <div class="errorpage">
      <img id="image" src="<c:url value='/resources/static/image/error.gif' />">
      <span>404 에러 페이지</span>
      <span></span>
    </div>
    
    <script>
        // JavaScript를 사용하여 이미지를 무한 반복으로 변경
        let image = document.getElementById("image");
        let loopCount = 0;
        
        function loopImage() {
            if (loopCount < 10) { // 루프를 원하는 횟수로 설정
                image.src = "<c:url value='/resources/static/image/error.gif' />"; // 이미지를 다시 로드
                loopCount++;
                setTimeout(loopImage, 4000); // 4초마다 반복
            }
        }
        loopImage(); // 루프 시작
    </script>
  </body>
</html>
