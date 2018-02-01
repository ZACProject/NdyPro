<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="db.ndydb.*"%>
<%@ taglib prefix	="tags" tagdir="/WEB-INF/tags" %>



<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
	//ヴァージョン取得
	String strCurrVer = NdyConstants.CURRENT_VERSION;
	//現在のページ
	String strCurrentUrl = request.getRequestURL().toString();
	//コンテキスト
	ServletContext context = request.getServletContext();
	HttpSession hs = request.getSession();
	//セッションからログインユーザーを呼び出す
	Auth lgUser = (Auth)hs.getAttribute("LOGINUSER");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href ="Wellcome.css"type="text/css">
<style type="text/css">
</style>
</head>
<body>
	<tags:header></tags:header>
	<%-- <div id="navigation">
		 <span class="show"><a href="./Wellcome.jsp">トップページ</a>
		</span>
		<span class="logout">ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>
		</span>
	</div> --%>


	<%-- <div id="menu">
		<ul>
			<li><a href="./CategoriesListN.jsp" target="iframe">カテゴリ一覧</a></li>
			<li><a href="./CompaniesListN.jsp" target="iframe">カンパニ一覧</a></li>
			<li><a href="./BizcalendarListN.jsp" target="iframe">カレンダ一覧</a></li>
			<li><a href="./RoomsListN.jsp" target="iframe">ルーム一覧</a></li>
			<li><a href="./UsersListN.jsp" target="iframe">ユーザー一覧</a></li>

		</ul>
		<div>ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!

		</div>
	</div>--%>



<!-- <form action=""></form> -->


</body>

</html>