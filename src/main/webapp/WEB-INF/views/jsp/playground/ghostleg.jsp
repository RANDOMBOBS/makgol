<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




	<link rel="stylesheet" href="../../../../../resources/static/css/game/style.css">



<h3>Ghost Leg</h3>
<div id="initBtns">

	<div id=left"></div>

	<div id="setNumPlayers">
		<button id="decBtn">LESS -</button>
		<span id="numPlayers">2</span><span id="playerText">PLAYERS</span>
		<button id="incBtn">MORE +</button>
	</div>
	<div id="begin">
		<button id="startBtn">PLAY</button>
	</div>
	<div id="langBtns">
		<button id="engLang">English</button>
	</div>

	<div id="right"></div>
</div>

<div id="canvasContainer">
	<canvas id="canvas" width="800" height="790"></canvas>
</div>
<button id="resultBtn">SHOW RESULT</button>
<div id="resultContainer"></div>