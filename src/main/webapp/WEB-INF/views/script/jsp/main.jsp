<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
$.noConflict();
var jQ = jQuery;

	function resultMenu(menu) {
		jQ.ajax({
			url : "/makgol/main/resultMenu/"+menu,
			type : "GET",
			dataType : "html",
			success : function(rdata) {   
				jQ(".selectedCategory").html(rdata);
				jQ("#spin").text("다시!");
			},
			error : function(error) { 
				alert('메뉴결과?');			
			}
		});
	}

	function selCategory() {
		// HTML 요소
		var allCategory = document.querySelectorAll(".category"); // 클래스명이 "category"인 모든 <span> 요소를 가져옵니다.
		var roulettePin = document.querySelector(".roulette_pin");
		var roulettePinRect = roulettePin.getBoundingClientRect(); //좌표

		// 룰렛 핀의 중심 좌표 계산
		var roulettePinCenterX = roulettePinRect.left + roulettePinRect.width
				/ 2;
		var roulettePinCenterY = roulettePinRect.top + roulettePinRect.height
				/ 2;

		// 초기화
		var closestCategory = null;
		var closestDistance = Infinity;

		allCategory.forEach(function(category) {
			var categoryRect = category.getBoundingClientRect();
			var categoryLeft = categoryRect.left; // <span> 요소의 왼쪽 좌표
			var categoryRight = categoryRect.right; // <span> 요소의 오른쪽 좌표
			var categoryTop = categoryRect.top; // <span> 요소의 위쪽 좌표
			var categoryBottom = categoryRect.bottom; // <span> 요소의 아래쪽 좌표

			// 룰렛 핀 중심 좌표와 <span> 요소의 중심 좌표 사이의 거리 계산
			var categoryCenterX = categoryLeft + (categoryRight - categoryLeft)
					/ 2;
			var categoryCenterY = categoryTop + (categoryBottom - categoryTop)
					/ 2;
			var distance = Math.sqrt(Math.pow(roulettePinCenterX
					- categoryCenterX, 2)
					+ Math.pow(roulettePinCenterY - categoryCenterY, 2));

			if (distance < closestDistance) {
				closestCategory = category;
				closestDistance = distance;
			}
		});

		if (closestCategory) {
			var menu = closestCategory.textContent;
			console.log(menu);
			
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

		setTimeout(function() {
			btn.disabled = false;

			selCategory();
		}, 4500); 	}

	btn.addEventListener("click", handleButtonClick);
</script>