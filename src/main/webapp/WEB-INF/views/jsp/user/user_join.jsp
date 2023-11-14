<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=46bc308803f4e404bdf4521f4af2f32e&libraries=services"></script>

<script>
jQuery(document).ready(function() {

    let result = false;
    //이메일 중복확인
    jQuery('#mail-Check-duplication').click(function() {

		const email = jQuery('#userEmail1').val(); // 이메일 주소값 얻어오기!

		jQuery.ajax({
			type : 'POST',
			url : '<c:url value ="/user/mailCheckDuplication"/>', // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : {email : email},
			dataType: "json",
			success : function (data, status) {
				if(status === "success" ){
					if(data === true){
						alert('사용가능한 이메일 입니다');

                        jQuery('#mail-Check-Btn').attr('disabled',false);
                        jQuery('#mail-Check-text').attr('disabled',false);
                        jQuery('#mail-Check-Btn2').attr('disabled',false);

					} else if(data === false)  {
						alert('이미 가입한 이메일 입니다.');
					}
				} else {
					console.error("통신 오류: " + status);
				}
			}
		}); // end ajax
	}); // end send eamil

    // 이메일 인증번호 전송
	jQuery('#mail-Check-Btn').click(function() {
        alert('인증번호가 전송되었습니다.')
		const email = jQuery('#userEmail1').val(); // 이메일 주소값 얻어오기!
		const auth_number = jQuery('.mail-check-input') // 인증번호 입력하는곳

		var authNumber = {
			email : email,
		};

		jQuery.ajax({
			type : 'POST',
			url : '<c:url value ="/user/mailCheck"/>', // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
				if(status === "success" ){
					if(data === true){

					} else if(data === false)  {
						alert('이메일을 다시 확인해 주세요.')
					}
				} else {
					console.error("통신 오류: " + status);
				}
			}
		}); // end ajax
	}); // end send eamil


	// 인증번호 비교
	// blur -> focus가 벗어나는 경우 발생
	jQuery('#mail-Check-Btn2').click(function () {
		const email = jQuery('#userEmail1').val(); // 이메일 주소값 얻어오기!
		const auth_number = jQuery('#mail-Check-text').val(); //인증번호 얻어오기!

		var authNumber = {
				email : email,
				auth_number : auth_number
			};


		jQuery.ajax({
			type : 'POST',
			url : '<c:url value ="/user/authNumberCheck"/>',// GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
			    // 서버로부터 응답을 성공적으로 받았습니다.
				if (status === "success") {
		            // 인증 성공
		            if (data === true) {
		                alert("인증성공");
		                jQuery('#userEmail1').attr('disabled',true);
		    			jQuery('#mail-Check-Btn').attr('disabled',true);
		    			jQuery('#mail-Check-text').attr('disabled',true);
		    			jQuery('#mail-Check-Btn2').attr('disabled',true);

		            } else if(data === false){
		                // 인증 실패
					alert("인증실패")
		            }
			    } else {
			        // 서버와 통신 중 문제 발생
			        console.error("통신 오류: " + status);

			    }
			}
		}); // end ajax
	});// 인증번호 비교

	// 회원가입 버튼
	 jQuery("#joinButton").click(function () {

        //이름관련 정규식
	    let reg_name1 = /^[가-힣]+$/; // 한글만
        let reg_name2 = /^[a-zA-Z]+$/; // 영문만
        let reg_name3 = /^[a-z]+$/; // 영문 소문자만
        let reg_name4 = /^[A-Z]+$/; // 영문 대문자만
        let reg_name5 = /^[가-힣a-zA-Z]+$/; // 한글 + 영문만

        // 아이디관련 정규식
        let reg_id1 = /^[a-z0-9_-]{4,20}$/; // 소문자 + 숫자 + 언더바/하이픈 허용 4~20자리
        let reg_id2 = /^[A-Za-z]{1}[A-Za-z0-9_-]{3,19}$/ // 반드시 영문으로 시작 숫자+언더바/하이픈 허용 4~2

        // 비밀번호 관련 정규식
        let reg_pw1 = /^[a-z0-9_-]{6,18}$/; // 단순 6~18자리
        let reg_pw2 = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{6,24}/; // 문자와 특수문자 조합의 6~24 자리
        let reg_pw3 = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/; // 문자, 숫자 1개이상 포함, 8자리 이상

        // 이메일 관련 정규식
        let reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

        // 전화번호 관련 정규식
        let reg_num = /^[0-9]{8,13}$/; // 전화번호 숫자만
        let reg_mobile = /^\d{3}-\d{3,4}-\d{4}$/; // 휴대폰 번호
        let reg_tel = /^\d{2,3}-\d{3,4}-\d{4}$/; // 일반 전화 번호

        // 주민등록번호 정규식
        let reg_jumin = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;

        // URL 관련 정규식
        let reg_url = /^(https?:\/\/)?([a-z\d\.-]+)\.([a-z\.]{2,6})([\/\w\.-]*)*\/?$/; // URL 검사식

        const formData = new FormData();
        const email 	= jQuery("#userEmail1").val();
        const name		= jQuery("#name").val();
        const phone 	= jQuery("#phone").val();
        const photo 	= jQuery("#photo")[0].files[0];
        const address   = jQuery("#sample5_address").val();
        const latitude  = jQuery("#latitude").val();
        const password 	= jQuery("#password").val();
        const longitude = jQuery("#longitude").val();

        const passwordCheck = jQuery('#passwordCheck').val();
/*
        if (!reg_name1.test(name)) {
            jQuery('#name').focus();
            return;

        }  else if (!reg_pw2.test(password)) {
            jQuery('#password').focus();
            return;

        } else if(password !== passwordCheck){
            jQuery('#passwordCheck').focus();
            return;

        } else if (!reg_num.test(phone)) {
            jQuery('#phone').focus();
            return;

        } else if(!result){
            jQuery('#mail-Check-text').focus();
            return;
        }

*/

        formData.append("name",  	 name);
        formData.append("email",	 email);
        formData.append("phone", 	 phone);
        formData.append("address", 	 address);
        formData.append("password",  password);
        formData.append("longitude", longitude);
        formData.append("latitude",  latitude);

        if(photo != null){ formData.append("photoFile", photo); }

        jQuery.ajax({
            type		: "POST",
            url			: "http://localhost:8090/user/join",
            data		: formData,
            processData : false,
            contentType : false,
            success		:
            	function (data, status) {
                if (status === "success") {
                    // 회원가입 성공
                    if(data === true){
                        alert(data);
                        window.location.href = "http://localhost:8090";
                    // 실패
                    } else {
                        alert(data);
                    }
                } else {
                    alert("실패!ㅋ : "+data);
                    console.error("통신 오류 : " + status);
                }
            }
        });
    });// 회원가입 버튼_END



	// 비밀번호 비교
	jQuery('#passwordCheckBtn').click(function () {
		const password =  jQuery('#password').val();
		const passwordCheck = jQuery('#passwordCheck').val();

		if(password === passwordCheck){
			alert("비밀번호가 일치합니다");
		} else {
			alert("비밀번호가 일치하지 않습니다");
		}
	});// 비밀번호 비교_END


	//비밀번호 유효성 검사
	function validatePassword() {
        const password = document.getElementById("password").value;
        const massage  = document.getElementById("메시지");
        massage.innerHTML = "";

        if (비밀번호.length < 8) {
          	massage.innerHTML = "비밀번호는 최소 8자 이상이어야 합니다.";
        } else if (!/[A-Z]/.test(비밀번호)) {
        	massage.innerHTML = "비밀번호에는 적어도 하나의 대문자 문자가 있어야 합니다.";
        } else if (!/[a-z]/.test(비밀번호)) {
        	massage.innerHTML = "비밀번호에는 적어도 하나의 소문자 문자가 있어야 합니다.";
        } else if (!/\d/.test(비밀번호)) {
        	massage.innerHTML = "비밀번호에는 적어도 하나의 숫자가 있어야 합니다.";
        } else {
        	massage.innerHTML = "비밀번호가 유효합니다.";
        }
    }//비밀번호 유효성 검사_END
});//jQuery(document).ready(function()_END
</script>

</head>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>

<div class="form-group email-form">
    <label>회원가입!</label>
   <form action = "/user/join" method="post" enctype="multipart/form-data">
    <div class="input-group">
        <input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일">
        <select class="form-control" name="userEmail2" id="userEmail2">
            <option value = "@naver.com"   >@naver.com</option>
            <option value = "@daum.net"    >@daum.net</option>
            <option value = "@gmail.com"   >@gmail.com</option>
            <option value = "@hanmail.com" >@hanmail.com</option>
            <option value = "@yahoo.co.kr" >@yahoo.co.kr</option>
        </select>
    </div>
    <div>
    <button type="button" id="mail-Check-duplication">중복확인</button>
    <div>
    <div class="input-group-addon">
        <button type="button" class="btn btn-primary" id="mail-Check-Btn" disabled>본인인증</button>
    </div>
    <div class="mail-check-box">
        <input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" id="mail-Check-text" disabled>
        <div class="input-group-addon">
            <button type="button" class="btn btn-primary" id="mail-Check-Btn2" disabled>인증확인</button>
        </div>
        <input placeholder="이름" id="name" name="name"><br />
        <input type="password" placeholder="비밀번호" id="password" name="password"><br />
        <input type="password" placeholder="비밀번호 확인" id="passwordCheck" name="password">
        <button type="button" id="passwordCheckBtn" name="passwordCheckBtn">비밀번호 확인</button><br />
        <input placeholder="ex) 01012345678" id="phone" name="phone"><br />
        <input type="text" id="sample5_address" placeholder="주소">
        <input type="hidden" id="longitude" >
        <input type="hidden" id="latitude" >


        <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
        <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

        <input type="file" id="photo" name="photo">
        <button type="button" id="joinButton">가입</button>
    </div>
    </form>
</div>
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