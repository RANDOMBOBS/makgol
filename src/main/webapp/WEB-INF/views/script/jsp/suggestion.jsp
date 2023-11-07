<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$.noConflict();
	var jQ = jQuery;

	function allBoardList() {
		jQuery.ajax({
			url : "/makgol/board/suggestion/showAllList",
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQuery(".board_list").html(rdata);
			},
			error : function(error) {
				alert("allBoardList 오류");
			}
		});
	}

	function comList() {
		let board_id = parseInt(jQ("input[name=board_id]").val());
		jQ.ajax({
			url : "/makgol/board/suggestion/commentList/" + board_id,
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ(".boardCommentList").html(rdata);
			},
			error : function(error) {
				alert("comList 오류");
			},
		});
	}

	function createCommentForm() {
		let form = document.create_comment_form;

		if (form.nickname.value == "") {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
		} else if (form.content.value == "") {
			alert("댓글을 입력해주세요");
			form.content.focus();
		} else {
			let nickname = jQ("input[name=nickname]").val();
			let content = jQ("input[name=content]").val();
			let board_id = jQ("input[name=board_id]").val();
			let user_id = jQ("input[name=user_id]").val();
			let data = {
				nickname : nickname,
				content : content,
				board_id : board_id,
				user_id : user_id,
			};
			jQ.ajax({
				url : "/makgol/board/suggestion/commentCreate",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					if (rdata === 1) {
						comList();
						jQ("input[name=nickname]").val("");
						jQ("input[name=content]").val("");
					} else {
						return;
					}
				},
				error : function(error) {
					alert("createCommentForm 오류");
				},
			});
		}
	}

	function modifyCommentForm(button) {
		var form = button.closest('form');

		if (form.nickname.value == '') {
			alert('수정할 닉네임을 입력해주세요.');
			form.nickname.focus();
		} else if (form.content.value == '') {
			alert('수정할 댓글을 입력해주세요');
			form.content.focus();
		} else if (window.confirm('수정하시겠습니까?')) {

			let nickname = form.nickname.value;
			let content = form.content.value;
			let id = form.id.value;
			let modData = {
				nickname : nickname,
				content : content,
				id : id
			};
			jQ.ajax({
				url : "/makgol/board/suggestion/commentModifyConfirm",
				type : "POST",
				data : JSON.stringify(modData),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					if (rdata == 1) {
						comList();
					}
				},
				error : function(error) {
					alert("modifyCommentForm 오류");
				},
			});
		}
	}

	function modifyCancle(button) {
		let form = $(button).closest('form');
		form[0].reset();

		let div = $(button).closest('.modCancle');
		div.hide();
	}

	function delComment(id) {
		if (window.confirm('삭제하시겠습니까?')) {
			jQ.ajax({
				url : "/makgol/board/suggestion/commentDelete/" + id,
				type : "GET",
				dataType : "html",
				success : function(result) {
					if (result == 1) {
						comList();
					}
				},
				error : function(error) {
					alert("delComment 오류");
				},

			});
		}
	}

	function modComment(button) {
		jQ(button).parent().parent().next().show();

	}

	function searchBoard() {
		let form = document.search_board_form;
		let searchOption = jQ("select[name=search]").val();
		let searchWord = jQ("input[name=searchWord]").val();
		if (searchOption == '') {
			alert('검색옵션을 입력해주세요');
			form.search.focus();
		} else if (searchWord == '') {
			alert('검색어를 입력해주세요');
			form.searchWord.focus();
		} else {
			let data = {
				searchOption : searchOption,
				searchWord : searchWord
			}
			console.log(data); //성공
			jQ.ajax({
				url : "/makgol/board/suggestion/search",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					jQ(".board_list").html(rdata);
					jQ("select[name=search]").val("");
					jQ("input[name=searchWord]").val("");
				},
				error : function(error) {
					alert("searchBoard 오류");
				}
			});
		}
	}

	jQ('input[type=checkbox]').on('click',function() {
		if (!user_id) {
			alert("로그인을 하세요.")
			return false;
		}
		if (jQ(this).prop('checked')) {
			jQ(this).next('i').addClass('fa-solid').removeClass('fa-regular')
			jQ.ajax({
			type :"POST",
			url : "${pageContext.request.contextPath}/board/suggestion/likeBoard",
			data : JSON.stringify(likeData),
			contentType : "application/json; charset=UTF-8",
			success : function(rdata){
				jQ('.fa-thumbs-up').text(rdata.totalLike)
			},
			error : function(error){
				console.log(error)
			}
		})
		
		} else {
			jQ(this).next('i').addClass('fa-regular').removeClass('fa-solid')

			jQ.ajax({
			type :"POST",
			url : "${pageContext.request.contextPath}/board/suggestion/unlikeBoard",
			data : JSON.stringify(likeData),
			contentType : "application/json; charset=UTF-8",
			success : function(rdata){
				jQ('.fa-thumbs-up').text(rdata.totalLike)
			},
			error : function(error){
				console.log(error)
			}
		})
		}
	})
	
	
	function userLikeStatus(b_id, user_id){
		jQ.ajax({
			type :"POST",
			url : "${pageContext.request.contextPath}/board/suggestion/userLikeStatus",
			data : JSON.stringify(likeData),
			contentType : "application/json; charset=UTF-8",
			success : function(rdata){
			if(rdata.status){
				jQ("#like").next('i').addClass('fa-solid').removeClass('fa-regular')
				jQ("#like").prop("checked", true)
			}else{
				jQ("#like").next('i').addClass('fa-regular').removeClass('fa-solid')
			}
			},
			error : function(error){
				console.log(error)
			}
		})
	}
</script>