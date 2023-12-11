<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=46bc308803f4e404bdf4521f4af2f32e&libraries=services"></script>
  <link href="<c:url value='/resources/static/css/user.css' />" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="../include/header.jsp"></jsp:include>

 <div id="modify_user">
      <h1 class="modify_user_title">회원정보 수정</h1>
  <form action="<c:url value='/user/modifyUserConfirm'><c:param name="oldFile" value="${userInfo.photo_path}"/></c:url>"
        method="post" name="modify_user_info" class="modify_user_form" enctype="multipart/form-data">

    <div class="profile_image">
      <label for="photoFile">
        <input type="file" id="photoFile" class="photo_file" name="photoFile" onchange="userimageURL(this)" />
        <i class="fa-solid fa-image"></i>
        <img src="http://localhost:8090${loginedUserVo.photo_path}" alt="프로필사진" class="preview" />
      </label>
    </div>
    <br />

    <div class="column email">
      <p class="caution">* 이메일과 이름은 변경하실 수 없습니다.</p>
      <span class="description">이메일</span>
      <input type="text" class="form-control" name="email" id="email" value="${userInfo.email}" readonly disabled />
    </div>

    <div class="column name">
      <span class="description">이름</span>
      <input placeholder="이름" id="name" name="name" value="${loginedUserVo.name}" readonly disabled />
    </div>

    <div class="column password">
      <span class="description">새 비밀번호</span>
      <input type="password" placeholder="비밀번호" id="password" name="password" />
    </div>

    <div class="column password_check">
      <span class="description">비밀번호 확인</span>
      <input type="password" placeholder="비밀번호 확인" id="passwordCheck" name="passwordCheck" />
      <button type="button" id="passwordCheckBtn" name="passwordCheckBtn"> 확인 </button>
    </div>

    <div class="column phone">
      <span class="description">전화번호</span>
      <input placeholder="전화번호" id="phone" name="phone" value="${userInfo.phone}" />
    </div>

    <div class="column address">
      <span class="description">주소</span>
      <input type="text" id="sample5_address" name="address" value="${userInfo.address}" readonly/>

      <input type="hidden" name="longitude" id="resultX" value="${userInfo.longitude}" />
      <input type="hidden" name="latitude" id="resultY" value="${userInfo.latitude}" />
      <input type="button" class="search" onclick="sample5_execDaumPostcode()" value="검색" />
    </div>

    <div id="map"></div>

    <input type="hidden" name="id" value="${loginedUserVo.id}" />
    <input type="hidden" name="grade" value="${loginedUserVo.grade}" />
    <button type="button" class="mod button" onclick="ModifyUserInfo()"> 수정 </button>
    <button type="button" class="cancel button" onclick="ModifyUserCancle()"> 취소 </button>
  </form>
</div>

<script>
function userimageURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        jQ(input).next().next(".preview").attr("src", e.target.result);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }


  let pwCheck = true;
 jQ("#passwordCheckBtn").on("click", function () {
   console.log("Button clicked");
   let pw = jQ("#password").val();
   let pw2 = jQ("#passwordCheck").val();
   if(pw === ""){
    alert("비밀번호를 입력해주세요");
   } else {
       if (pw !== pw2) {
           alert("비밀번호가 같지 않습니다.");
         } else {
           pwCheck = false;
           alert("비밀번호가 일치합니다.");
         }
   }
 });

     var mapContainer = document.getElementById('map'), // 지도를 표시할 div
       mapOption = {
         center: new daum.maps.LatLng(${userInfo.latitude}, ${userInfo.longitude}), // 지도의 중심좌표
         level: 5 // 지도의 확대 레벨
       };

     // 지도를 미리 생성
     var map = new daum.maps.Map(mapContainer, mapOption);
     // 주소-좌표 변환 객체를 생성
     var geocoder = new daum.maps.services.Geocoder();
     // 마커를 미리 생성
     var marker = new daum.maps.Marker({
       position: new daum.maps.LatLng(${userInfo.latitude}, ${userInfo.longitude}),
       map: map
     });


    function sample5_execDaumPostcode() {
      new daum.Postcode({
        oncomplete: function (data) {
          var addr = data.address; // 최종 주소 변수

          // 주소 정보를 해당 필드에 넣는다.
          document.getElementById("sample5_address").value = addr;
          // 주소로 상세 정보를 검색
          let x = ""
          let y = ""
          geocoder.addressSearch(data.address, function (results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

              var result = results[0]; //첫번째 결과의 값을 활용

              console.log(result.x, result.y)
              x = result.x
              y = result.y



              // 해당 주소에 대한 좌표를 받아서
              var coords = new daum.maps.LatLng(result.y, result.x);
              // 지도를 보여준다.
              mapContainer.style.display = "block";
              map.relayout();
              // 지도 중심을 변경한다.
              map.setCenter(coords);
              // 마커를 결과값으로 받은 위치로 옮긴다.
              marker.setPosition(coords)

                        jQ("#resultX").val(x);
                        jQ("#resultY").val(y);
            }
          })


        }
      }).open();
    }

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
        } else if (form.phone.value === "") {
          alert("전화번호를 입력해주세요");
          form.phone.focus();
        } else {
          if (window.confirm("정보를 수정하시겠습니까?")) {
            form.submit();
          }
        }
      }


    function ModifyUserCancle(){
       window.location.href = "http://localhost:8090/user/myPage?user_id=${loginedUserVo.id}"
    }




</script>
</body>
</html>
