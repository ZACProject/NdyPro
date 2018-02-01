
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="db.*" %>
<%@page import="db.ndydb.*" %>
<%@taglib prefix ="tags" tagdir="/WEB-INF/tags" %>
<%
//変数の宣言
	//管理列にセットするための変数
	//ログオン時に取得しておく
	String userName = "";//ログオンした時のユーザー名をセットする
	String remoteAddr="";//端末名あるいは接続先のアドレスをセットする

	//セッション宣言する
	HttpSession hs = request.getSession();
	//コンテキスト
	ServletContext context = request.getServletContext();
	//セッションからログインユーザーを呼び出す
	Auth lgUser = (Auth)hs.getAttribute("LOGINUSER");
	userName = lgUser.getUserName();
	remoteAddr = lgUser.getRemoteAddr();

	//BizcaBnを取得する
	BizcalendarBn bn = null;

	if(hs.getAttribute("bizcalendar")!=null){
		//セッションからBizacalendarBnを取り出す
		bn = (BizcalendarBn)hs.getAttribute("bizcalendar");
	}else{
		//値が入っていないBeanをセットする
		bn = new BizcalendarBn();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv ="X-UA-Compatible" content="IE=edge"/>
<title>カレンダ登録</title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Main.css">
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
<link rel = "stylesheet" href="Design/PublicReg.css"type = "text/css">
<!-- <link rel = "stylesheet" href="Design/Bizcalendar.css" type = "text/css"> -->
<style type="text/css">


</style>
</head>
<body>
<tags:header></tags:header>





		<div id="position">
			<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./BizcalendarListN.jsp">カレンダリスト</a>&nbsp;&gt;
				<a href="./Bizcalendar.jsp">カレンダ登録画面</a>
			</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>

	<form action="./BizcalendarSv" method ="post">
		<!-- 管理列情報 -->
		<input type="hidden" name="deleteflg" value="false">
		<input type="hidden" name="insuser"   value="<%=userName %>">
		<input type="hidden" name="insmachine"value="<%=remoteAddr %>">
		<input type="hidden" name="updateuser"value="<%=userName%>">
		<input type="hidden" name="updmachine"value="<%=remoteAddr%>">
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
				<th>会社ID</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "companyid" value ="<%= bn.getCompanyid() %>" ></td>
			</tr>
			<tr>
				<th>営業日</th>
				<td colspan = "6"><input class="text-box" type ="text" name = "bizday" value ="<%= bn.getBizday() %>" ></td>
			</tr>
			<tr>
				<th>説明</th>
				<td colspan = "6"><textarea class="text-box" rows="3" cols="20" name = "description" <%= bn.getDescription() %>></textarea></td>
			</tr>
			<tr>
				<th>予約可能</th>
				<td colspan = "6"><input class="text-box" type ="text" name = "openflg" value ="<%= bn.getOpenflg() %>"></td>
			</tr>
			</table> --%>
<div class="wrap">
	<div class="box3">
		<div class="companyid">
			<label for="companyid" >会社ID</label>
			<input type="text" name="companyid" id="companyid" value="<%= bn.getCompanyid() %>">
		</div>


		<div class="bizday">
			<label for="bizday">営業日</label>
			<input type="text" name="bizday" id="bizday" value="<%= bn.getBizday() %>">
		</div>
		<div class="openflg">
			<label for="openflg">予約可能</label>
			<input type="text" name="openflg" id="openflg" value="<%= bn.getOpenflg() %>">
		</div>
	</div>

	<div class="box">
		<div class="description">
			<label for="description">説明</label>
			<textarea  rows="2" cols="30" name="description" id="description"><%=bn.getDescription()%></textarea>
		</div>
	</div>

	<div class="wrap-sub">
		<input class="sub" type="submit" name="call_select" value="呼出">
		<input class="sub" type="submit" name="call_insert" value="登録">
		<input class="sub" type="submit" name="call_update" value="更新">
		<input class="sub" type="submit" name="call_softdelete" value="論理削除">
		<input class="sub" type="submit" name="call_delete" value="削除">
	</div>
</div>
			</form>
			<tags:Footer1></tags:Footer1>

</body>
</html>