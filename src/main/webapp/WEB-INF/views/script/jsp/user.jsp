<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
let user_id = ${loginedUsersRequestVo.id}

function myPostList(){
    jQ.ajax({
        url: "/user/myPostList/" + user_id,
        type : "GET",
        dataType : "html",
        success : function(rdata) {
                 jQ("#my_history").html(rdata);
                },
                error : function(error) {
                    alert("myPostList 오류");
                },
    });
}

function deleteBoard(){
 if (window.confirm('선택한 글을 삭제하시겠습니까?')){

            let checkboxes= jQuery("input[type=checkbox]:checked");
            let ids = ""
            checkboxes.each(function(index){
                ids += jQuery(this).closest("td").next().val()+",";
            })
            jQ.ajax({
                    url : "/board/suggestion/deleteMyBoard/"+ids,
                    type : "POST",
                    success : function(rdata) {
                        alert("선택한 글이 삭제되었습니다.");
                         myPostList();
                        },
                        error : function(error) {
                            alert("deleteBoard 오류");
                        },
                });
                }
    }

function myCommentList(){
    jQ.ajax({
        url: "/user/myCommentList/" + user_id,
        type : "GET",
        dataType : "html",
        success : function(rdata) {
                 jQ("#my_history").html(rdata);
                },
                error : function(error) {
                    alert("allCommentList 오류");
                },
    });

}


function deleteComment(){
 if (window.confirm('선택한 댓글을 삭제하시겠습니까?')){
            let checkboxes= jQuery("input[type=checkbox]:checked");
            let comids = ""
            checkboxes.each(function(index){
                comids += jQuery(this).closest("td").next().val()+",";
            })
            jQ.ajax({
                    url : "/board/suggestion/deleteMyComment/"+comids,
                    type : "POST",
                    success : function(rdata) {
                        alert("선택한 댓글이 삭제되었습니다.")
                         myCommentList();
                        },
                        error : function(error) {
                            alert("deleteComment 오류");
                        },
                });
                }
    }

    function myLikePost(){
  jQ.ajax({
        url: "/user/myLikePost/" + user_id,
        type : "GET",
        dataType : "html",
        success : function(rdata) {
                 jQ("#my_history").html(rdata);
                },
                error : function(error) {
                    alert("myLikePost 오류");
                },
    });

    }


    function deleteLike(){
     if (window.confirm('선택한 글의 공감을 취소하시겠습니까?')){
    let checkboxes= jQuery("input[type=checkbox]:checked");
            let likeids = ""
            let boardids =""
            checkboxes.each(function(index){
                likeids += jQuery(this).closest("td").next().val()+",";
                boardids +=jQuery(this).closest("td").next().next().val()+",";
            })
            let data = {likeids : likeids, boardids : boardids}
            jQ.ajax({
                    url : "/board/suggestion/deleteMyLike/",
                    type : "POST",
                    contentType: "application/json",
                    data : JSON.stringify(data),
                    dataType: "json",
                    success : function(rdata) {
                        alert("선택한 글의 공감이 취소되었습니다.")
                         myLikePost();
                        },
                        error : function(error) {
                            alert("deleteLike 오류");
                        },
                });
                }

    }

</script>