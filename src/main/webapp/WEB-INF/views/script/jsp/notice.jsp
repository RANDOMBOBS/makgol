<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$.noConflict();
	var jQ = jQuery;

	function searchNotice() {
		let form = document.search_notice_form;
		let searchWord = jQ("input[name=searchWord]").val();
		 if (searchWord == '') {
			alert('검색어를 입력해주세요');
			form.searchWord.focus();
		} else {
			let data = {
				searchWord : searchWord
			}
			console.log(data); //성공
			jQ.ajax({
				url : "/board/searchNoticeList",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					jQ(".notice_list").html(rdata);
					jQ("input[name=searchWord]").val("");
				},
				error : function(error) {
					alert("검색오류");
				}
			});
		}
	}

          let boardNo = ${boardVo.b_id};
          let userNo = ${loginedUserMemberVo.user_id};


                if (userNo) {
        			userLikeStatus(boardNo, userNo)
        		} else {
        			userLikeCnt(boardNo)
        		}

	                function likeBtn(me){
                 			if (!userNo) {
                 				alert("로그인한 후 클릭하세요.")
                 				return false;
                 			}
                 			if (!jQ(me).find('i').hasClass('fa-solid')) {
                 				jQ(me).find('i').addClass('fa-solid').removeClass('fa-regular')
                 				jQ.ajax({
                 					type :"POST",
                 					url : "${pageContext.request.contextPath}/board/noticeLikeInsert",
                 					data : JSON.stringify(likedata),
                 					contentType : "application/json; charset=UTF-8",
                 					success : function(rdata){
                 						jQ('.likecnt').text(rdata.likecnt)
                 					},
                 					error : function(error){
                 						console.log(error)
                 					}
                 				})
                 			} else {
                 				jQ(me).find('i').addClass('fa-regular').removeClass('fa-solid')
                 				jQ.ajax({
                 					type :"POST",
                 					url : "${pageContext.request.contextPath}/board/noticeLikeDelete",
                 					data : JSON.stringify(likedata),
                 					contentType : "application/json; charset=UTF-8",
                 					success : function(rdata){
                 						jQ('.likecnt').text(rdata.likecnt)
                 					},
                 					error : function(error){
                 						console.log(error)
                 					}
                 				})
                 			}
                 		}

    function userLikeStatus(b_id, user_id){
            let likedata = { b_id : b_id , user_id : user_id }
    		jQ.ajax({
    			type :"POST",
    			url : "${pageContext.request.contextPath}/board/noticeLikeStatus",
    			data : JSON.stringify(likedata),
    			contentType : "application/json; charset=UTF-8",
    			success : function(rdata){
    			if(rdata.Status){
    				jQ(".like_cnt").prev().find('i').addClass('fa-solid').removeClass('fa-regular')
    			}else{
    				jQ(".like_cnt").prev().find('i').addClass('fa-regular').removeClass('fa-solid')
    			}
    			},
    			error : function(error){
    				console.log(error)
    			}
    		})
    	}

    function userLikeCount(b_id){
            let likedata = { b_id : b_id }
            jQ.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/board/noticeLikeCount",
                data : JSON.stringify(likedata),
                contentType : "application/json; charset=UTF-8",
                success : function(rdata){
                    jQ(".like_cnt").text(rdata.likeCnt)
                    },
                error : function(error){
                        console.log(error)
                    }
                })
                }



</script>
