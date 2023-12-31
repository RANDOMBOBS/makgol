<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<script>


    function searchNotice() {
        let form = document.search_notice_form;
        let searchWord = jQ("input[name=searchWord]").val();
        console.log(searchWord);
        if (searchWord == '') {
            alert('검색어를 입력해주세요');
            form.searchWord.focus();
        } else {
            let data = {
                searchWord: searchWord
            }
            console.log(data); //성공
            jQ.ajax({
                url: "/board/searchNoticeList",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (rdata) {
                    jQ(".board_list").html(rdata);
                    jQ("input[name=searchWord]").val("");
                },
                error: function (error) {
                    alert("검색오류");
                }
            });
        }
    }

</script>
