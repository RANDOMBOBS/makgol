<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
	function menuList() {
		jQ.ajax({
			url : "/category/categoryList", // controller에 해당되는 경로 이동
			type : "GET",
			dataType : "html",
			success : function(rdata) {   //category_list 데이터 값을 가져와 성공하면
				jQ("#category_list_div").html(rdata);   // class="category_list_div" 인 곳에 가져온 데이터를 넣는다.
			},
			error : function(error) {  			// 실패하면 오류
				alert('오류');
			}
		});
	}

	// 한식 클릭시 한식관련 메뉴
	function korMenu(){
		jQ.ajax({
			url: "/category/categoryKor",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('한식메뉴실패');
			}
		});
	}

	// 양식 클릭시 양식관련 메뉴
	function westMenu(){
		jQ.ajax({
			url: "/category/categoryWest",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('양식메뉴실패');
			}
		});
	}

	// 중식 클릭시 중식관련 메뉴
	function chiMenu(){
		jQ.ajax({
			url: "/category/categoryChi",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('중식메뉴실패');
			}
		});
	}

	// 분식 클릭시 분식관련 메뉴
	function snackMenu(){
		jQ.ajax({
			url: "/category/categorySnack",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('분식메뉴실패');
			}
		});
	}

	// 일식 클릭시 일식관련 메뉴
	function jpnMenu(){
		jQ.ajax({
			url: "/category/categoryJpn",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('일식메뉴실패');
			}
		});
	}

	// 카페 클릭시 카페관련 메뉴
	function cafeMenu(){
		jQ.ajax({
			url: "/category/categoryCafe",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ("#category_list_div").html(rdata);
			},
			error : function(error) {
				alert('카페/디저트메뉴실패');
			}
		});
	}


</script>
