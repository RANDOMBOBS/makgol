<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<style>

.noticeLikeBtn{
    width:200px;
}
.fa-solid{
    font-size:100px;
}
.icon {
  position: absolute;
  top: calc(50% - 64px);
  left: calc(50% - 64px);
  width: 128px;
  height: 128px;
  cursor: pointer;
  color: rgba(76, 76, 76, .9);
  transform: scale(1.1);
  transition: all .1s ease-out, color .3s;
  z-index: 5;
  vertical-align: middle;
  text-align: center;
}

.icon:hover {
  color: rgba(76, 76, 76, 1);
  transition: all .1s ease-out;
}

.icon:active {
  transform: scale(0.9);
  transition: all .1s ease-out;
}

.icon i {
  margin-top: 32px;
  font-size: 64px;
  pointer-events: none;
}

.unselectable {
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    outline: 0;
}

</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>공감수</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${boardVo}" varStatus="status">
			<tr>
				<td>${fn:length(boardVo)-(status.index)}</td>
				<td><c:url value='/board/detailNotice' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' />
					</c:url> <a href="${detail_url}">${item.title}</a></td>
				<td>${item.sympathy}</td>
				<td>${item.name}</td>
				<td>${item.date}</td>
				<td>${item.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 글쓰기 버튼 클릭시 페이지 이동 -->
<c:url value='/board/noticeCreateForm' var='notice_create_url'>
	<c:param name='name' value='${item.name}' />
</c:url>
<div class="icon"><span class="material-symbols-outlined">
                  thumb_up
                  </span></div>
<a href="${notice_create_url}">글쓰기</a>

<script>
var active = false;
var interv = null;
$(".icon").click(function() {

  if(active)
    {
      active = false;
      $('.icon').css("transform", "scale(1.1)").css("color", "rgba(76, 76, 76, .9)");
      $('.icon:hover').css("color", "rgba(76, 76, 76, 1)");
    } else {
      active = true;
      clearTimeout(interv);
      interv = setTimeout(function() {
         if(active == true)
           {
             active = false;
            $('.icon').css("transform", "scale(1.1)").css("color", "rgba(76, 76, 76, .9)");
            $('.icon:hover').css("color", "rgba(76, 76, 76, 1)");
           }
      }, 5000);
      $('.icon:hover').css("color", "#1E88E5");
      $('.icon').css("color", "#1E88E5");
      const RADIUS = 64;
      const circle = new mojs.Shape({
        stroke:   {'transparent' : "#1E88E5"},
        strokeWidth: { [1.005*RADIUS] : 0 },
        fill:     'none',
        scale:    { 0: 1, easing: 'quad.out' },
        radius:   RADIUS,
        duration:  350
      }).generate().play();
      const burst = new mojs.Burst({
        className: ".icon",
        count:      10,
        degree:   360,
        radius:   { 32: 128 },
        speed:    6,
        angle: 45,
        children: {
          shape:        'line',
          stroke: {'#1E88E5' : '#42A5F5'},
          strokeDasharray: '100%',
          strokeDashoffset: { '-100%' : '100%' },
          easing:       'quad.out',
          fill: 'null',
          radius:       3,
          scale: {2 : 1},
          angle:      0,
          speed: 2,
          duration: 650,
        }
      }).generate().play();
      $('.icon').css("transform", "scale(1.3)");
    }
});
</script>
