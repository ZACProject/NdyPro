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
	//UsersBnを取得する
	UsersBn bn = null;
	if(hs.getAttribute("users")!=null){
		//セッションからUserBnを取り出す
		bn = (UsersBn)hs.getAttribute("users");
	}else{
		//値が入っていないBeanをセットする
		bn = new UsersBn();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv ="X-UA-Compatible" content="IE=edge"/>
<title>ユーザー登録</title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Main.css">
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
<link rel = "stylesheet" href="Design/PublicReg.css"type = "text/css">
<!-- <link rel = "stylesheet" href="Design/Users.css"type = "text/css"> -->
<style type="text/css">


</style>
</head>
<body>
<tags:header></tags:header>



<div id="position">
			<span  class="show">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./UsersListN.jsp">ユーザーニリスト</a>&nbsp;&gt;
				<a href="./Users.jsp">ユーザー登録画面</a>

			</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>

	<form action="./UsersSv" method ="post">
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
				<th>ユーザーID</th>
			    <td colspan = "6"><input class="text-box" type ="text"  name = "userid" value ="<%= bn.getUserid() %>"></td>
			</tr>
			<tr>
				<th>ユーザー名</th>
			    <td colspan = "6"><input class="text-box" type ="text"  name = "username" value ="<%= bn.getUsername() %>"></td>
			</tr>
			<tr>
				<th>パスワード</th>
			    <td colspan = "6"><input class="text-box" type ="password"   name = "password" value ="<%= bn.getPassword() %>"></td>
			</tr>
			<tr>
				<th>ユーザー画像リンク名</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "linkname" value ="<%= bn.getLinkname() %>"></td>
			</tr>
			<tr>
				<th>ユーザー画像リンク先</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "linkurl" value ="<%= bn.getLinkurl() %>"></td>
			</tr>
			<tr>
				<th>姓</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "firstname" value ="<%= bn.getFirstname() %>"></td>
			</tr>
			<tr>
				<th>名</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "lastname" value ="<%= bn.getLastname() %>"></td>
			</tr>
			<tr>
				<th>ユーザーレベル</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "userlevel" value ="<%= bn.getUserlevel() %>"></td>
			</tr>
			<tr>
				<th>会社名</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "company" value ="<%= bn.getCompany() %>"></td>
			</tr>
			<tr>
				<th>組織名</th>
			    <td colspan = "6"><input class="text-box" type ="text" name = "dept" value ="<%= bn.getDept() %>"></td>
			</tr>
			<tr>
				<th>開始日</th>
				<td  colspan="6"><input class="text-box" type="text" name="regdate" value="<%=bn.getRegdate() %>"></td>
			</tr>
			<tr>
				<th>終了日</th>
				<td  colspan="6"><input class="text-box" type="text" name="duedate" value="<%=bn.getDuedate() %>"></td>
			</tr>
		</table> --%>
<div class="wrap">
	<div class="box3">
		<div class="userid">
			<label for="userid">ユーザーID</label>
			<input type="text" name="userid" id="userid" value="<%= bn.getUserid() %>">
		</div>
		<div class="username">
			<label for="username">ユーザー名</label>
			<input type="text" name="username" id="username" value="<%= bn.getUsername() %>">
		</div>
		<div class="password">
			<label for="password">パスワード</label>
			<input type="text" name="password" id="password" value="<%= bn.getPassword() %>">
		</div>
	</div>
	<div class="box2">
		<div class="linkname">
			<label for="linkname">画像リンク名</label>
			<input type="text" name="linkname" id="linkname" value="<%= bn.getLinkname() %>">
		</div>
		<div class="linkurl">
			<label for="linkurl">画像リンク先</label>
			<input type="file" name="linkurl" id="linkurl" value="<%= bn.getLinkurl() %>">
		</div>
	</div>
	<div class="box3">
		<div class="firstname">
			<label for="firstname">姓</label>
			<input type="text" name="firstname" id="firstname" value="<%= bn.getFirstname() %>">
		</div>
		<div class="lastname">
			<label for="lastname">名</label>
			<input type="text" name="lastname" id="lastname" value="<%= bn.getLastname() %>">
		</div>
		<div class="userlevel">
			<label for="userlevel">レベル</label>
			<input type="text" name="userlevel" id="userlevel" value="<%= bn.getUserlevel() %>">
		</div>
	</div>
	<div class="box2">
		<div class="company">
			<label for="company">会社名</label>
			<input type="text" name="company" id="company" value="<%= bn.getCompany() %>">
		</div>
		<div class="dept">
			<label for="dept">組織名</label>
			<input type="text" name="dept" id="dept" value="<%= bn.getDept() %>">
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