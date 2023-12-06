<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
  <link href="<c:url value='/resources/static/css/admin.css' />" rel="stylesheet" type="text/css" />

</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>


    <div id="user_management">
      <h1 class="management_page_title">회원목록</h1>
      <div class="user_list">
      <div class="user_related">
        <div class="change_role">
          <span>선택한 회원을 </span>
          <select name="grade">
            <option value="준회원">준회원</option>
            <option value="정회원">정회원</option>
            <option value="우수회원">우수회원</option>
            <option value="관리자">관리자</option>
            <option value="블랙리스트">블랙리스트</option>
          </select>
          <span> 등급으로 변경합니다.</span>
          <button type="button" onclick="updateGrade()">변경하기</button>
        </div>

        <div class="search_box">
            <div class="search">
              <select name="searchOption" id="searchOption" onchange="searchOption()">
                  <option value="userName">회원명</option>
                  <option value="userRole">회원등급</option>
              </select>
              <select name="searchRole" class="searchValue">
                  <option value="준회원">준회원</option>
                  <option value="정회원">정회원</option>
                  <option value="우수회원">우수회원</option>
                  <option value="관리자">관리자</option>
                  <option value="블랙리스트">블랙리스트</option>
              </select>
              <input type="text" name="searchText" class="searchValue on" placeholder="회원명을 입력해주세요">
              <button type="button" onclick="searchUserList()"/><i class='fa-solid fa-magnifying-glass'></i></button>
            </div>
        </div>
      </div>

	  <div class="all_user_list"></div>
     </div>
	<jsp:include page="../../script/jsp/user_management.jsp"></jsp:include>

	<script>
		userList();

        function searchOption(){
            let searchOption = jQ("#searchOption").val();
            if(searchOption == "userRole"){
                jQ("select.searchValue").addClass('on')
                jQ("input.searchValue").removeClass('on')
            } else {
                jQ("input.searchValue").addClass('on')
                jQ("select.searchValue").removeClass('on')
            }
        }

          function searchUserList(){
                let searchOption = jQ("#searchOption").val();
                let searchValue;
                if(searchOption == 'userName') {
                    searchValue = jQ("input.searchValue").val();
                } else if(searchOption == 'userRole') {
                    searchValue = jQ("select.searchValue").val();
                }
                if(!searchValue){
                    alert("검색어를 입력해주세요");
                } else {
                    let data = { searchOption: searchOption, searchValue: searchValue};
                    jQ.ajax({
                      url: "/admin/search",
                      type: "POST",
                      data: JSON.stringify(data),
                      contentType: "application/json; charset=utf-8",
                      success: function (rdata) {
                        jQ(".all_user_list").html(rdata);

                      },
                      error: function (error) {
                        alert("searchUserList 오류");
                      },
                    });
                  }

            }

	</script>

</body>
</html>
