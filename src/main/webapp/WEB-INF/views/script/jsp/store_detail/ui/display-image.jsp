<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const displayImage = (photo) => {
        const photoEle = $("#photo img");

        if (photo) {
            photoEle.attr({src: "https://" + photo}).css({width:"100%", height: "244px", borderRadius: "0 0 19px 19px"})
        } else {
            photoEle.attr({src: "http://localhost:8090/resources/static/image/default/error.gif"}).css({width: "100%", height: "75%"})
            const spanEle = $("<span>")
            spanEle.css({position: "relative", bottom:"22px", fontSize: "14px", color: "orange"}).text("아직 이미지가 없어요!")
            $("#photo .item_info_body").append(spanEle)
        }

    };

</script>