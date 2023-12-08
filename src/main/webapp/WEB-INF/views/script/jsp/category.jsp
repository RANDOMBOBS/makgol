<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
    //최초리스트
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

	// 버튼 클릭시 매개변수에 값에 따라 (한식,양식,중식,분식,일식,카페) 리스트를 보여줌
	function categoryMenu(categoryType){
		jQ.ajax({
			url: "/category/" + categoryType ,
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
</script>
