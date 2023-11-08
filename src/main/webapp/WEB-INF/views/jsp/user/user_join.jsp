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

<script>
$(document).ready(function() {
	$('#mail-Check-Btn').click(function() {
		
		const email = $('#userEmail1').val() + $('#userEmail2').val(); // 이메일 주소값 얻어오기!
		const auth_number = $('.mail-check-input') // 인증번호 입력하는곳 
		
		var authNumber = {
			email : email,
		};
		
		
		$.ajax({
			type : 'POST',
			url : '<c:url value ="/user/mailCheck"/>', // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
				if(status === "success" ){
					if(data === true){
						alert('인증번호가 전송되었습니다.')
						checkInput.attr('disabled',false);
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
	$('#mail-Check-Btn2').click(function () {
		const email = $('#userEmail1').val() + $('#userEmail2').val(); // 이메일 주소값 얻어오기!
		const auth_number = $('#mail-Check-text').val(); //인증번호 얻어오기!
		
		var authNumber = {
				email : email,
				auth_number : auth_number
			};
			
		
		$.ajax({
			type : 'POST',
			url : '<c:url value ="/user/authNumberCheck"/>',// GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
				if (status === "success") {
		            // 서버로부터 응답을 성공적으로 받았습니다.
		            if (data === true) {
		                // 인증 성공
		                alert("인증성공");
		    			$('#mail-Check-Btn').attr('disabled',true);
		    			
		    	         
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
	 $("#joinButton").click(function () {
		
        const formData = new FormData();
        const email 	= $("#userEmail1").val()+$("#userEmail2").val();
        const name		= $("#name").val();
        const password 	= $("#password").val();
        const phone 	= $("#phone").val();
        const photo 	= $("#photo")[0].files[0];
        
        const imageInput = $("#photo")[0];
        console.log("photo", photo.files)
        
        formData.append("email",	email);
        formData.append("name",  	name);
        formData.append("password", password);
        formData.append("phone", 	phone);
        formData.append("photoFile", 	photo);

        $.ajax({
            type		: "POST",
            url			: "http://localhost:8090/user/join",
            data		: formData,
            processData : false,
            contentType : false,
            success		: 
            	function (data) {
                if (status === "success") {
                    // Process the response data, e.g., show a success message
                } else {
                    console.error("통신 오류: " + status);
                }
            }
        });
    });// 회원가입 버튼_END
	
 	// 회원가입 버튼
 	/*
	$("#joinButton").click(function () {
		
        const email 	= $("#userEmail1").val()+$("#userEmail2").val();
        const name		= $("#name").val();
        const password 	= $("#password").val();
        const phone 	= $("#phone").val();
        const photo 	= $("#photo")[0].files[0];
        
        let form = {
        		email 	 : email,
        		name  	 : name,
        		password : password,
        		phone	 : phone,
        };

        $.ajax({
            type		: "POST",
            url			: "http://localhost:8090/user/join",
            data		: JSON.stringify(form),
            dataType	: "json",
            contentType : "application/json; charset=utf-8",
            success 	: function (data, status) {
                if (status === "success") {
                	if (data === true) {
                		alert("성공!ㅋ");
                	} else {
                		alert("실패!ㅋ");
                	}
                } else {
                	alert(data);
                    console.error("통신 오류: " + status);
                }
            }
        });
        
    });// 회원가입 버튼_END */
	
	// 비밀번호 비교 
	$('#passwordCheckBtn').click(function () {
		const password =  $('#password').val();
		const passwordCheck = $('#passwordCheck').val();
		
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
	
	
	
});//$(document).ready(function()_END
</script>

</head>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>

<div class="form-group email-form">
    <label>회원가입!</label>
   <form action = "makgol/user/join" method="post" enctype="multipart/form-data">
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
    <div class="input-group-addon">
        <button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
    </div>
    <div class="mail-check-box">
        <input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" id="mail-Check-text">
        <div class="input-group-addon">
            <button type="button" class="btn btn-primary" id="mail-Check-Btn2">인증확인</button>
        </div>
        <input placeholder="이름" id="name" name="name"><br />
        <input type="password" placeholder="비밀번호" id="password" name="password"><br />
        <input type="password" placeholder="비밀번호 확인" id="passwordCheck" name="password">
        <button type="button" id="passwordCheckBtn" name="passwordCheckBtn">비밀번호 확인</button><br />
        <input placeholder="핸드폰" id="phone" name="phone"><br />
        <input type="file" id="photo" name="photo">
        <button type="button" id="joinButton">가입</button>
    </div>
    </form>
</div>

</body>
</html>