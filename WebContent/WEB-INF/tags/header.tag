<%@ tag language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href ="Wellcome.css"type="text/css">
<div id="header">
		 <div class="h-logo">
			<img class="photo"src="images/Logo.png">
			<img class="photo1"src="images/ComName.png">
		</div>
		<div class="theme"></div>
		<div class="url">ZAC予約管理システム</div>
	</div>
<div id="menu">
		<ul>
			<li id="ca"><a href="./CategoriesListN.jsp" target="iframe">カテゴリ一覧</a></li>
			<li id="co"><a href="./CompaniesListN.jsp" target="iframe">カンパニ一覧</a></li>
			<li id="bi"><a href="./BizcalendarListN.jsp" target="iframe">カレンダ一覧</a></li>
			<li id="ro"><a href="./RoomsListN.jsp" target="iframe">ルーム一覧</a></li>
			<li id="us"><a href="./UsersListN.jsp" target="iframe">ユーザー一覧</a></li>

		</ul>
		<%-- <div>ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!

		</div> --%>
	</div>
<!-- <script>
var oCa = document.getElementById("ca");
var oCo = document.getElementById("co");
var oBi = document.getElementById("bi");
var oRo = document.getElementById("ro");
var oUs = document.getElementById("us");
oCa.onclick= function(){
	this.style.color="yellow";
	this.style.background="white";
	oCo.style.background="#0660A3";
	oBi.style.background="#0660A3";
	oRo.style.background="#0660A3";
	oUs.style.background="#0660A3";

}
oCo.onclick=function(){
	this.style.background="white";
	oCa.style.background="#0660A3";
	oBi.style.background="#0660A3";
	oRo.style.background="#0660A3";
	oUs.style.background="#0660A3";

}
</script> -->