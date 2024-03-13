<script>
	let b_id = jQ('#like').attr("data-b-id");
	let user_id = jQ('#like').attr("data-user-id");
    let likeData = { b_id: b_id, user_id: user_id };

	let pageGroup = 1;   // (1~10, 11~20)
	let pageNum = 1;	 // 최초 페이지 번호
	// 한페이지에 보여질 글의 양
    let login = false;
	allBoardList(pageGroup, pageNum);

	function allBoardList(pGroup, pNum) {
		jQ.ajax({
			var url = "/board/vent/showAllList/"+login+"?pageNum="+pNum+"&pageGroup="+pGroup;
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ(".board_list").html(rdata);

			},
			error : function(error) {
				alert("allBoardList 오류");
			}
		});
	}

	function pageGrouping(pGroup, direct, total_page_group_num, total_page) {
		let pNum = 0
		if (direct==1 && pGroup==1) {
			pNum = 1
		} else if (direct==1 && pGroup>1) {
			pGroup = pGroup - 1
			pNum = pGroup*10-9
		} else if (direct==2 && pGroup<total_page_group_num) {
			pGroup = pGroup + 1;
			pNum = pGroup*10-9
		} else if (direct==2 && pGroup==total_page_group_num) {
			pNum = total_page

		}

		console.log(pGroup, pNum)

		allBoardList(pGroup, pNum)

	}



    function comList() {
        let board_id = parseInt(jQ("input[name=board_id]").val());
        jQ.ajax({
            url: "/board/vent/commentList/" + board_id,
            type: "GET",
            dataType: "html",
            success: function (rdata) {
                jQ(".boardCommentList").html(rdata);
            },
            error: function (error) {
                alert("comList 오류");
            },
        });
    }

    function createCommentForm() {
        let form = document.create_comment_form;

        if (form.nickname.value === "") {
            alert("닉네임을 입력해주세요.");
            form.nickname.focus();
        } else if (jQ("textarea").val() == "") {
            alert("댓글을 입력해주세요");
            form.content.focus();
        } else {
            let nickname = jQ("input[name=nickname]").val();
            let content = jQ("textarea").val();
            let board_id = jQ("input[name=board_id]").val();
            let user_id = jQ("input[name=user_id]").val();
            let grade = jQ("input[name=grade]").val();

            let data = {
                nickname: nickname,
                content: content,
                board_id: board_id,
                user_id: user_id,
                grade: grade,
            };
            jQ.ajax({
                url: "/board/vent/commentCreate",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (rdata) {
                    if (rdata === 1) {
                        comList();
                        jQ("input[name=nickname]").val("");
                        jQ("textarea").val("");
                    } else {
                        return;
                    }
                },
                error: function (error) {
                    alert("createCommentForm 오류");
                },
            });
        }
    }

    function modifyCommentForm(button) {
        var form = button.closest("form");

        if (form.nickname.value == "") {
            alert("수정할 닉네임을 입력해주세요.");
            form.nickname.focus();
        } else if (form.content.value == "") {
            alert("수정할 댓글을 입력해주세요");
            form.content.focus();
        } else if (window.confirm("댓글을 수정하시겠습니까?")) {
            let nickname = form.nickname.value;
            let content = form.content.value;
            let id = form.id.value;
            let modData = {
                nickname: nickname,
                content: content,
                id: id,
            };
            jQ.ajax({
                url: "/board/vent/commentModifyConfirm",
                type: "POST",
                data: JSON.stringify(modData),
                contentType: "application/json; charset=utf-8",
                success: function (rdata) {
                    if (rdata == 1) {
                        comList();
                    }
                },
                error: function (error) {
                    alert("modifyCommentForm 오류");
                },
            });
        }
    }

    function modifyCancle(button) {
        let form = $(button).closest("form");
        form[0].reset();

        let div = $(button).closest(".mod_cancle");
        div.hide();
    }

    function delComment(id) {
        if (window.confirm("삭제하시겠습니까?")) {
            jQ.ajax({
                url: "/board/vent/commentDelete/" + id,
                type: "GET",
                dataType: "html",
                success: function (result) {
                    if (result == 1) {
                        comList();
                    }
                },
                error: function (error) {
                    alert("delComment 오류");
                },
            });
        }
    }

    function modComment(button) {
        jQ(button).closest("ul").next().next().show();
    }

    function searchBoard() {
        let form = document.search_board_form;
        let searchOption = jQ("select[name=search]").val();
        let searchWord = jQ("input[name=searchWord]").val();


        if (searchWord == "") {
            alert("검색어를 입력해주세요");
            form.searchWord.focus();
        } else {
            let data = {
                searchOption: searchOption,
                searchWord: searchWord,
                pageNum: pageNum,
                pageGroup: pageGroup
            };
            jQ.ajax({
                url: "/board/vent/search",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (rdata) {
                    jQ(".board_list").html(rdata);
                },
                error: function (error) {
                    alert("searchBoard 오류");
                },
            });
        }
    }

    function userLikeStatus(b_id, user_id) {
        jQ.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/board/vent/userLikeStatus",
            data: JSON.stringify(likeData),
            contentType: "application/json; charset=UTF-8",
            success: function (rdata) {
                if (rdata.status) {
                    jQ("#like").next("i").addClass("fa-solid").removeClass("fa-regular");
                    jQ("#like").prop("checked", true);
                } else {
                    jQ("#like").next("i").addClass("fa-regular").removeClass("fa-solid");
                }
            },
            error: function (error) {
                console.log(error);
            },
        });
    }

    jQ("input[type=checkbox]").on("click", function () {
        if (!user_id) {
            alert("로그인 후 이용하실 수 있습니다.");
            return false;
        }
        if (jQ(this).prop("checked")) {
            jQ(this).next("i").addClass("fa-solid").removeClass("fa-regular");
            jQ.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/board/vent/likeBoard",
                data: JSON.stringify(likeData),
                contentType: "application/json; charset=UTF-8",
                success: function (rdata) {
                    jQ(".fa-thumbs-up").text(" "+rdata.totalLike);
                },
                error: function (error) {
                    console.log(error);
                },
            });
        } else {
            jQ(this).next("i").addClass("fa-regular").removeClass("fa-solid");
            jQ.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/board/vent/unlikeBoard",
                data: JSON.stringify(likeData),
                contentType: "application/json; charset=UTF-8",
                success: function (rdata) {
                    jQ(".fa-thumbs-up").text(" "+rdata.totalLike);
                },
                error: function (error) {
                    console.log(error);
                },
            });
        }
    });

    function imageURL(input) {
        jQ(input).next().children(".preview").attr("style", "display:none");
        jQ(input).next().children(".fa-plus").attr("style", "display:block");

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                jQ(input).next().children(".preview").attr("src", e.target.result);
                jQ(input).next().children(".fa-plus").attr("style", "display:none");
                jQ(input).parent().next().attr("style", "display:block");
                jQ(input).next().children("img").attr("style", "display:flex");
                let index = jQ(input).closest(".image").index();
                jQ(".oldImage:eq(" + index + ")").val("null");
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    function deleteImage(input) {
        jQ(input).prev().children().find(".preview").attr("style", "display:none");
        jQ(input).prev().children().find(".fa-plus").attr("style", "display:block");
        jQ(input).attr("style", "display:none");
        jQ(input).prev().find("input").val("");
        let index = jQ(input).closest(".image").index();
        jQ(".oldImage:eq(" + index + ")").val("null");
    }

    function createBoardForm() {
        let form = document.create_board_form;
        if (form.category.value == "") {
            alert("카테고리를 선택해주세요.");
            form.category.focus();
        } else if (form.title.value == "") {
            alert("제목을 입력해주세요");
            form.title.focus();
        } else if (form.contents.value == "") {
            alert("글 내용을 입력해주세요.");
            form.contents.focus();
        } else {
            form.submit();
        }
    }

    function resetContents() {
        let images = jQ(".image");
        console.log(images);
        jQ(images).children().find(".preview").attr("style", "display:none");
        jQ(images).children().find(".fa-plus").attr("style", "display:block");
        jQ(".delete_image").attr("style", "display:none");
        jQ(images).children().find("input").val("");
    }

    function returnToList() {
        window.location.href = "http://www.makgol.com/board/vent";
    }

</script>