<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const displayDetail = (param) => {
        const ulEle = document.querySelector("#intro .item_info_body ul");
        const liEles = Array.from(ulEle.children);
        const detailInfos = [
            param.placeName,
            param.phone,
            param.categoryName,
            param.addressName,
        ];

        liEles.forEach((liEle, idx) => {
            const spanEle = liEle.querySelector("span:nth-child(2)");
            spanEle.innerText = detailInfos[idx];
        });
    };

</script>