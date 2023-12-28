<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
  function resultMenu(menu) {
    jQ.ajax({
      url: "/main/resultMenu/" + menu,
      type: "GET",
      dataType: "html",
      success: function (rdata) {
        jQ(".selectedCategory").html(rdata);
        jQ("#spin").text("다시!");
      },
      error: function (error) {
        alert("메뉴결과?");
      },
    });
  }

  function selCategory() {
    // HTML 요소
    var allCategory = document.querySelectorAll(".category"); // 클래스명이 "category"인 모든 <span> 요소를 가져옵니다.
    var roulettePin = document.querySelector(".roulette_pin");
    var roulettePinRect = roulettePin.getBoundingClientRect(); //좌표

    // 룰렛 핀의 중심 좌표 계산
    var roulettePinCenterX = roulettePinRect.left + roulettePinRect.width / 2;
    var roulettePinCenterY = roulettePinRect.top + roulettePinRect.height / 2;

    // 초기화
    var closestCategory = null;
    var closestDistance = Infinity;

    allCategory.forEach(function (category) {
      var categoryRect = category.getBoundingClientRect();
      var categoryLeft = categoryRect.left; // <span> 요소의 왼쪽 좌표
      var categoryRight = categoryRect.right; // <span> 요소의 오른쪽 좌표
      var categoryTop = categoryRect.top; // <span> 요소의 위쪽 좌표
      var categoryBottom = categoryRect.bottom; // <span> 요소의 아래쪽 좌표

      // 룰렛 핀 중심 좌표와 <span> 요소의 중심 좌표 사이의 거리 계산
      var categoryCenterX = categoryLeft + (categoryRight - categoryLeft) / 2;
      var categoryCenterY = categoryTop + (categoryBottom - categoryTop) / 2;
      var distance = Math.sqrt(
        Math.pow(roulettePinCenterX - categoryCenterX, 2) +
          Math.pow(roulettePinCenterY - categoryCenterY, 2)
      );

      if (distance < closestDistance) {
        closestCategory = category;
        closestDistance = distance;
      }
    });

    if (closestCategory) {
      var menu = closestCategory.textContent;

      resultMenu(menu);
    }
  }

  let roulette = document.querySelector(".roulette");
  let btn = document.getElementById("spin");
  let number = Math.ceil(Math.random() * 10000);

  function handleButtonClick() {
    btn.disabled = true;

    roulette.style.transform = "rotate(" + number + "deg)";
    number += Math.ceil(Math.random() * 10000);

    setTimeout(function () {
      btn.disabled = false;

      selCategory();
    }, 4500);
  }

  btn.addEventListener("click", handleButtonClick);

  function todayMenuList() {

  var userInfo = {
    x : ${loginedUserVo.longitude},
    y : ${loginedUserVo.latitude}
  }

    jQ.ajax({
      url: "/main/todayMenuList/",
      type: "GET",
      dataType: "html",
      data: JSON.stringify(userInfo),
      success: function (rdata) {
        jQ(".todaymenu_list_div").html(rdata);
        slickTodaySlider();
      },
      error: function (error) {
        alert("today 오류");
      },
    });
  }

	btn.addEventListener("click", handleButtonClick);

	   function todayMenuList() {
	   var userInfo = {
           x : ${loginedUserVo.longitude},
           y : ${loginedUserVo.latitude}
         }
        		jQ.ajax({
        			url : "/main/todayMenuList/",
        			type : "GET",
        			dataType : "html",
        			data: JSON.stringify(userInfo),
        			success : function(rdata) {
        				jQ(".todaymenu_list_div").html(rdata);
        				slickTodaySlider()
        			},
        			error : function(error) {
        				alert('today 오류');
        			}
        		});
        	}

	function topMenuList() {
	var userInfo = {
               x : ${loginedUserVo.longitude},
               y : ${loginedUserVo.latitude}
             }
	        var header = new
    		jQ.ajax({
    			url : "/main/topMenuList",
    			type : "GET",
    			dataType : "html",
    			success : function(rdata) {
    				jQ(".topmenu_list_div").html(rdata);
    				slickTopSlider()
    			},
    			error : function(error) {
    				alert('TOP 오류');
    			}
    		});
    	}
</script>
