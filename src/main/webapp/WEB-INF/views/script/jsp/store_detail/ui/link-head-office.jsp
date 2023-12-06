<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    const linkHeadOffice = (site) => {
        const headOfficeEle = $("#head_office").parent("a");

        if (site) {
            headOfficeEle.attr({href: site});
        } else {
            $("#head_office").css({display:"none"})
            const iconEle = $("<i>");
            iconEle.addClass("fa-solid fa-house").text(" 사이트 없음").css({color: "gray", cursor: "default"});
            headOfficeEle.append(iconEle);
        }
    }
</script>