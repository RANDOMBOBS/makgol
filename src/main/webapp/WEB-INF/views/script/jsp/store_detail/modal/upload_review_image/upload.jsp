<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../data-structure/review-image-map.jsp"></jsp:include>
<script>
    const reader = new FileReader();
    let reviewImages = [];

    function imageURL(input) {
        const presentFile = input.files[0]
        jQ(input).next().children(".preview").attr("style", "display:none");
        jQ(input).next().children(".fa-plus").attr("style", "display:block");

        if (input.files && presentFile) {

            reader.onload = function (e) {
                jQ(input).next().children(".preview").attr("src", e.target.result);
                jQ(input).next().children(".fa-plus").attr("style", "display:none");
                jQ(input).parent().next().attr("style", "display:block");
                jQ(input).next().children("img").attr("style", "display:flex");
                let index = jQ(input).closest(".image").index();
                jQ(".oldImage:eq(" + index + ")").val("null");
            };
            reader.readAsDataURL(presentFile);

            reviewImages = [...reviewImages, presentFile];
        }
    }

    function deleteImage(input) {
        jQ(input).prev().children().find(".preview").attr("style", "display:none");
        jQ(input).prev().children().find(".fa-plus").attr("style", "display:block");
        jQ(input).attr("style", "display:none");
        let index = jQ(input).closest(".image").index();
        jQ(".oldImage:eq(" + index + ")").val("null");

        const presentFile = jQ("#file" + (index + 1))[0].files[0];
        reviewImages = reviewImages.filter(file => file.name != presentFile.name)
    }

    const upload = () => {
        $("#upload_review_image").click(() => {
            reviewImageMap.set("reviewImages", reviewImages);
            $(".modal_cover").css({display: "none"});
            $(".upload_modal").css({display: "none"});
        })
    };


</script>