<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$.noConflict();
	var jQ = jQuery;

	function userList() {
		jQ.ajax({
			url : "${pageContext.request.contextPath}/admin/userList",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ(".allUserList").html(rdata);
			},
			error : function(error) {
				console.error(error)
				alert("유저리스트 가져오기 실패");
			},
		});
	}

	function updateGrade() {
		let grade = jQ("select[name=grade]").val();
		let checkboxes = jQ("input[type=checkbox]:checked");
		checkboxes.each(function() {
			let id = jQ(this).closest("tr").find("td:eq(1)").text();
			let data = {
				id : id,
				grade : grade,
			};
			jQ.ajax({
				url : "/makgol/admin/modifyGrade",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					if (rdata === 1) {
						let updateGrade = jQ("select[name=grade]").val();
						jQ(this).closest("tr").find("td:eq(6)").text(updateGrade);
						userList();
					}
				},
				error : function(error) {
					alert("등급 변경 실패");
				},
			});
		});
	}

	function allCheckbox() {
		let checkbox = jQ("input[type=checkbox]");
		if (jQ("#allCheckbox").is(":checked")) {
			checkbox.prop("checked", true);
		} else {
			checkbox.prop("checked", false);
		}
	}
</script>