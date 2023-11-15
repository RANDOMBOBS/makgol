<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
let user_id = ${loginedUsersRequestVo.id}

function allPostList(){
    jQ.ajax({
        url: "/user/myPostList/" + user_id,
        type : "GET",
        dataType : "html",
        success : function(rdata) {
                 jQ("#my_history").html(rdata);
                },
                error : function(error) {
                    alert("allPostList 오류");
                },
    });
}

function deleteBoard(){
        let checkboxes= jQuery("input[type=checkbox]:checked");
            checkboxes.each(function(){
                let id = jQuery(this).closest("input[type=hidden]").value();
                jQ.ajax({
                    url : "/board/delete/"+id,
                    type : "GET",
                    success : function(rdata) {
                         alert(rdata);
                        },
                        error : function(error) {
                            alert("deleteBoard 오류");
                        },
                });
            })
    }

</script>