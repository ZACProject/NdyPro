
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.*"%>
<%@ page import="db.ndydb.*"%>
<%@ taglib prefix	="tags" tagdir="/WEB-INF/tags" %>
<%
	//変数の宣言
	//管理列にセットするための変数。
	//ログオン時に取得しておく
	String userName 	= "";		//ログオンした時のユーザー名をセットする
	String remoteAddr 	= "";		//端末名 or 接続先のアドレスをセットする

	//セッション宣言する
	HttpSession hs = request.getSession();
	//コンテキスト
	ServletContext context = request.getServletContext();
	//セッションからログインユーザーを呼び出す
	Auth lgUser = (Auth)hs.getAttribute("LOGINUSER");
	userName = lgUser.getUserName();
	remoteAddr = lgUser.getRemoteAddr();

	//CategoriesBnを取得する
	CategoriesBn bn = null;

	if(hs.getAttribute("categories") !=null ){
		//セッションからCategoriesBnを取り出す
		bn = (CategoriesBn)hs.getAttribute("categories");
	}else {
		//値がはいっていないBeanをセットする。
		bn = new CategoriesBn();
	}

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>カテゴリ登録</title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Main.css">
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
 <link rel = "stylesheet" href="Design/PublicReg.css"type = "text/css">
<!-- <link rel = "stylesheet" href="Design/Categories.css"type = "text/css"> -->
<!-- <script type="text/javascript" src="Public.js">
</script> -->

<style type="text/css">


</style>
</head>
<body>
<tags:header></tags:header>



	<div id="position">
			<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./CategoriesListN.jsp">カテゴリニリスト</a>&nbsp;&gt;
				<a href="./Categories.jsp">カテゴリ登録画面</a>

			</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>


	<form action="./CategoriesSv" method="post" >
		<!-- 管理列情報 -->
		<input type="hidden" name="deleteflg"	value="false">
		<input type="hidden" name="insuser"		value="<%=userName 		%>">
		<input type="hidden" name="insmachine"	value="<%=remoteAddr 	%>">
		<input type="hidden" name="updateuser"	value="<%=userName		%>">
		<input type="hidden" name="updmachine"	value="<%=remoteAddr	%>">
		<%-- <table border="1">
		<tr>
		<th></th>
		<th><input class="action" type="submit" name="call_select" value="呼出"></th>
		<th><input class="action" type="submit" name="call_insert" value="登録"></th>
		<th><input class="action" type="submit" name="call_update" value="更新"></th>
		<th><input class="action" type="submit" name="call_softdelete" value="論理削除"></th>
		<th><input class="action" type="submit" name="call_delete" value="削除"></th>
		<th></th>
		</tr>

			<tr>
				<th>カテゴリID</th>
				<td colspan="6"><input  class="text-box" type="text" name="categoryid" value="<%=bn.getCategoryid() %>"></td>
			</tr>
			<tr>
				<th>カテゴリ名</th>
				<td colspan="6"><input class="text-box" type="text"   name="categoryname" value="<%=bn.getCategoryname() %>"></td>
			</tr>
			<tr>
				<th>カテゴリの説明</th>
				<td colspan="6">
					<textarea  class="text-box" rows="3" cols="20" name="description"><%=bn.getDescription()%></textarea>
				</td>
			</tr>
			<tr>
				<th>リンク名</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname" value="<%=bn.getLinkname() %>"></td>
			</tr>
			<tr>
				<th>リンク先</th>
				<td colspan="6"><input  class="text-box" type="text" name="linkurl" value="<%=bn.getLinkurl() %>"></td>
			</tr>
			<tr>
				<th>開始日</th>
				<td colspan="6"><input  class="text-box" type="text" name="regdate" value="<%=bn.getRegdate() %>"></td>
			</tr>
			<tr>
				<th>終了日</th>
				<td colspan="6"><input  class="text-box" type="text" name="duedate" value="<%=bn.getDuedate() %>"></td>
			</tr>
		</table> --%>
<div class="wrap">
	<div class="box2">
		<div class="categoryid">
			<label for="categoryid">カテゴリID  </label>
			<input type="text"  name="categoryid" id="categoryid" value="<%=bn.getCategoryid() %>">
		</div>
		<div class="categoryname">
			<label for="categoryname">カテゴリ名</label>
			<input type="text"  name="categoryname" id="categoryname" value="<%=bn.getCategoryname() %>">
		</div>
	</div>

	<div class="box">
		 <div class="description">
			<label for="description">説明</label>
			<textarea rows="2" cols="30" name="description" id="description"><%=bn.getDescription()%></textarea>
		 </div>
	</div>

	<div class="box2">
		<div class="linkname">
			<label for="linkname">リンク名</label>
			<input type="text" name="linkname" id="linkname" value="<%=bn.getLinkname() %>">
		 </div>

		<div class="linkurl">
			<label for="linkurl">リンク先</label>
			<input type="file" name="linkurl" id="linkurl" value="<%=bn.getLinkurl() %>">
		 </div>
	</div>

	<div class="box2">
		<div class="regdate">
			<label for="regdate">開始日</label>
			<input type="text" name="regdate" id="regdate" value="<%=bn.getRegdate() %>">
		 </div>

		<div class="duedate">
			<label for="duedate">終了日</label>
			<input type="text" name="duedate" id="duedate" value="<%=bn.getDuedate() %>">
		 </div>
	</div>
	<div class="wrap-sub">
		 <input class="sub" type="submit" name="call_select" value="呼出">
		 <input class="sub" type="submit" name="call_select" value="登録">
		 <input class="sub" type="submit" name="call_select" value="更新">
		 <input class="sub" type="submit" name="call_select" value="論理削除">
		 <input class="sub" type="submit" name="call_select" value="削除">
	</div>

</div>

	</form>

	<tags:Footer1></tags:Footer1>

</body>
</html>