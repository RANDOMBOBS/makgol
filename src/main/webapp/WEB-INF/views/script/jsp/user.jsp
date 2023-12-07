<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    let user_id = ${loginedUserVo.id}
  function myPostList() {
    jQ(".myPosts").addClass("on");
    jQ(".myComments").removeClass("on");
    jQ(".myLikePosts").removeClass("on");
    jQ.ajax({
      url: "/user/myPostList/" + user_id,
      type: "GET",
      dataType: "html",
      success: function (rdata) {
        jQ("#my_history").html(rdata);
      },
      error: function (error) {
        alert("myPostList 오류");
      },
    });
  }

  function deleteBoard() {
    if (window.confirm("선택한 글을 삭제하시겠습니까?")) {
      let checkboxes = jQuery("tbody input[type=checkbox]:checked");
      let ids = "";
      checkboxes.each(function (index) {
        ids += jQuery(this).closest("td").attr("data-id") + ",";
      });
      console.log("아이디는?"+ids);
      jQ.ajax({
        url: "/board/suggestion/deleteMyBoard/" + ids,
        type: "POST",
        success: function (rdata) {
          alert("선택한 글이 삭제되었습니다.");
          myPostList();
          countingPosts();
        },
        error: function (error) {
          alert("deleteBoard 오류");
        },
      });
    }
  }

  function myCommentList() {
    jQ(".myComments").addClass("on");
    jQ(".myPosts").removeClass("on");
    jQ(".myLikePosts").removeClass("on");
    jQ.ajax({
      url: "/user/myCommentList/" + user_id,
      type: "GET",
      dataType: "html",
      success: function (rdata) {
        jQ("#my_history").html(rdata);
      },
      error: function (error) {
        alert("allCommentList 오류");
      },
    });
  }

  function deleteComment() {
    if (window.confirm("선택한 댓글을 삭제하시겠습니까?")) {
      let checkboxes = jQuery("tbody input[type=checkbox]:checked");
      let comids = "";
      checkboxes.each(function (index) {
        comids += jQuery(this).closest("td").attr("data-id") + ",";
      });
      console.log("댓글번호?"+comids)
      jQ.ajax({
        url: "/board/suggestion/deleteMyComment/" + comids,
        type: "POST",
        success: function (rdata) {
          alert("선택한 댓글이 삭제되었습니다.");
          myCommentList();
          countingComments();
        },
        error: function (error) {
          alert("deleteComment 오류");
        },
      });
    }
  }

  function myLikePost() {
    jQ(".myLikePosts").addClass("on");
    jQ(".myPosts").removeClass("on");
    jQ(".myComments").removeClass("on");
    jQ.ajax({
      url: "/user/myLikePost/" + user_id,
      type: "GET",
      dataType: "html",
      success: function (rdata) {
        jQ("#my_history").html(rdata);
      },
      error: function (error) {
        alert("myLikePost 오류");
      },
    });
  }

  function deleteLike() {
    if (window.confirm("선택한 글의 공감을 취소하시겠습니까?")) {
      let checkboxes = jQuery("tbody input[type=checkbox]:checked");
      let likeids = "";
      let boardids = "";
      checkboxes.each(function (index) {
        likeids += jQuery(this).closest("td").attr("data-like_id") + ",";
        boardids += jQuery(this).closest("td").attr("data-board_id") + ",";
      });
      console.log("공감번호?"+likeids+" 게시글번호?"+boardids);
      let data = { likeids: likeids, boardids: boardids };
      jQ.ajax({
        url: "/board/suggestion/deleteMyLike",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (rdata) {
          alert("선택한 글의 공감이 취소되었습니다.");
          myLikePost();
          countingLikes();
        },
        error: function (error) {
          alert("deleteLike 오류");
        },
      });
    }
  }

  function allMyCheckbox() {
    let checkbox = jQ("input[type=checkbox]");
    if (jQ("#allMyCheckbox").is(":checked")) {
      checkbox.prop("checked", true);
    } else {
      checkbox.prop("checked", false);
    }
  }

  jQ("#my_history").on("click", ".eachCheckbox", function () {
    let myCheck = true;
    jQ(".eachCheckbox").each(function () {
      if (!jQ(this).prop("checked")) {
        myCheck = false;
      }
    });
    jQ("#allMyCheckbox").prop("checked", myCheck);
  });

  function countingPosts() {
    jQ.ajax({
      url: "/user/countingPosts/" + user_id,
      type: "GET",
      dataType: "json",
      success: function (rdata) {
        jQ(".posts").text(rdata);
      },
      error: function (error) {
        alert("countingPosts 오류");
      },
    });
  }

  function countingComments() {
    jQ.ajax({
      url: "/user/countingComments/" + user_id,
      type: "GET",
      dataType: "json",
      success: function (rdata) {
        jQ(".comments").text(rdata);
      },
      error: function (error) {
        alert("countingComments 오류");
      },
    });
  }

  function countingLikes() {
    jQ.ajax({
      url: "/user/countingLikes/" + user_id,
      type: "GET",
      dataType: "json",
      success: function (rdata) {
        jQ(".likes").text(rdata);
      },
      error: function (error) {
        alert("countingLikes 오류");
      },
    });
  }


</script>
