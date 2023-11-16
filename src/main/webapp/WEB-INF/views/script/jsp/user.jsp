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
            let ids = ""
            checkboxes.each(function(index){
                ids += jQuery(this).closest("td").next().val()+",";
            })
            jQ.ajax({
                    url : "/board/suggestion/deleteHistory/"+ids,
                    type : "POST",
                    success : function(rdata) {
                        alert("결과는?"+rdata.result);
                         allPostList();
                        },
                        error : function(error) {
                            alert("deleteBoard 오류");
                        },
                });
    }

</script>