<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=46bc308803f4e404bdf4521f4af2f32e&libraries=services"></script>
</head>

<body>

<jsp:include page="../include/header.jsp"></jsp:include>

<c:set var="emailInfo" value="${loginedUsersRequestVo.email.split('@')}"/>
<c:set var="emailId" value="${emailInfo[0]}"/>
<c:set var="emailDomain" value="${emailInfo[1]}"/>

<div class="form-group email-form">
  <label>회원정보 수정</label>
  <form action="<c:url value='/user/modifyUserConfirm'><c:param name="oldFile" value="${loginedUsersRequestVo.photo_path}"/></c:url>"
 method="post" name="modify_user_info" enctype="multipart/form-data">
    <div class="input-group">
      <input type="text" class="form-control" name="userEmail1" id="userEmail1" value="${emailId}" readonly disabled>
      <select class="form-control" name="userEmail2" id="userEmail2" readonly disabled>
        <option value="@naver.com" <c:if test="${emailDomain == 'naver.com'}">selected</c:if>>@naver.com</option>
        <option value="@daum.net" <c:if test="${emailDomain == 'daum.net'}">selected</c:if>>@daum.net</option>
        <option value="@gmail.com" <c:if test="${emailDomain == 'gmail.com'}">selected</c:if>>@gmail.com</option>
        <option value="@hanmail.com" <c:if test="${emailDomain == 'hanmail.com'}">selected</c:if>>@hanmail.com</option>
        <option value="@yahoo.co.kr" <c:if test="${emailDomain == 'yahoo.co.kr'}">selected</c:if>>@yahoo.co.kr</option>
      </select>
    </div>
    <input placeholder="이름" id="name" name="name" value="${loginedUsersRequestVo.name}" readonly disabled><br />
    <input type="password" placeholder="비밀번호" id="password" name="password"><br />
    <input type="password" placeholder="비밀번호 확인" id="passwordCheck" name="passwordCheck">
    <button type="button" id="passwordCheckBtn" name="passwordCheckBtn">비밀번호 확인</button><br />
    <input placeholder="전화번호" id="phone" name="phone" value="${loginedUsersRequestVo.phone}"><br />
    <input type="text" id="sample5_address" value="${loginedUsersRequestVo.address}"><br>

    <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
    <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
    <input type="hidden" id="longitude" >
    <input type="hidden" id="latitude" >

    <input type="file" id="photoFile" name="photoFile">
    <input type="hidden" name="id" value="${loginedUsersRequestVo.id}">
    <button type="button" onclick="ModifyUserInfo()">회원정보수정</button>
  </form>
</div>

<script>
  let pwCheck = true;
 jQ("#passwordCheckBtn").on("click", function () {
   console.log("Button clicked");
   let pw = jQ("#password").val();
   let pw2 = jQ("#passwordCheck").val();
   if (pw !== pw2) {
     alert("비밀번호가 같지 않습니다.");
   } else {
     pwCheck = false;
     alert("비밀번호가 일치합니다.");
   }
 });

  function ModifyUserInfo() {
    let form = document.modify_user_info;
    if (form.password.value === "") {
      alert("비밀번호를 입력해주세요");
      form.password.focus();
    } else if (form.passwordCheck.value === "") {
      alert("비밀번호 확인을 입력해주세요");
      form.passwordCheck.focus();
    } else if (pwCheck) {
      alert("비밀번호 확인 버튼을 눌러주세요");
    } else if (form.phone.value === "") {
      alert("전화번호를 입력해주세요");
      form.phone.focus();
    } else {
      if (window.confirm('정보를 수정하시겠습니까?')) {
      form.submit();
      }
    }
  }

</script>

<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        document.getElementById("longitude").value = result.x;
                        document.getElementById("latitude").value = result.y;

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script>
</body>
</html>
