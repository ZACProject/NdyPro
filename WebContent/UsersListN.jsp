<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@ page import="db.*"%>
<%@ page import="db.ndydb.*"%>
<%@ page import="grid.PagingControl"%>
<%@ taglib prefix	="tags" tagdir="/WEB-INF/tags" %>
<%
PagingControl pc = null;
if(session.getAttribute("page")==null){
	pc=new PagingControl();
}else{
	pc=(PagingControl)session.getAttribute("page");
}
if(request.getParameter("move") != null && request.getParameter("move").equals("NEXT") ){
	pc.moveNext();
}else if(request.getParameter("move") != null && request.getParameter("move").equals("PREV")){
	pc.movePrevious();
}else if(request.getParameter("move") != null && request.getParameter("move").equals("FIRST")){
	pc.moveFirst();
}else if(request.getParameter("move") != null && request.getParameter("move").equals("LAST")){
	pc.moveLast();
}

%>

<%

//接続及びSQL実行
try{
	//ドライバクラスをロード
	Class.forName(NdyConstants.PG_DRIVERNAME);
	//データベースへ接続
	Connection con = DriverManager.getConnection(NdyConstants.PG_URL ,NdyConstants.PG_USER ,NdyConstants.PG_PASSWORD);
	//ステートメントオブジェクトを生成
	Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);
	String sql = "select userid,"          +
				"username,"                +
				"password,"					+
				"linkname,"                 +
				"linkurl "     				+
				"from "						+
				"users";
	//ページングのための件数を取得する
	pc.setTotalRecCount(con, sql);
	//セッションにページオブジェクトを保存する
	session.setAttribute("page", pc);
	ResultSet rs = stmt.executeQuery(sql);
	//開始行の設定
	rs.absolute(pc.getStartRecord());
	//行数カウンタの設定
	int rowCounter = 0;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
<link rel = "stylesheet" href="Design/PublicList.css"type = "text/css">
<style type="text/css">


</style>
</head>
<body>
<tags:header></tags:header>


	<div id = "navigation">
		<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./UsersListN.jsp">ユーザーリスト</a>

		</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>
<div id="wrapper">

<form action="UsersListN.jsp" method="post" > <!--自分にpostしている-->
	<table border = "1">
		<tr>
			<th>ユーザーID</th>
			<th>ユーザー名</th>
			<th>パスワード</th>
			<th>画像リンク名</th>
			<th>画像リンク先</th>
			<th></th>
		</tr>
		<%
			int i = 0;
			while(rs.next()){
				UsersBn bn = new UsersBn();
				bn.setUserid(rs.getString("userid"));
				bn.setUsername(rs.getString("username"));
				bn.setPassword(rs.getString("password"));
				String strUser = "user" + i;
				session.setAttribute(strUser, bn);
		%>

		<tr>
					<td title="<%=rs.getString("userid") %>"><%=rs.getString("userid") %></td>
					<td title="<%=rs.getString("username") %>"><%=rs.getString("username") %></td>
					<td title="<%=rs.getString("password") %>"><%=rs.getString("password") %></td>
					<td title="<%=rs.getString("linkname") %>"><%=rs.getString("linkname") %></td>
					<td class="picture"><a href ="#"><img src="images/danya.png"></a></td>
				<td class="editer"><a href= "./UsersSv?strUser=user<%=i%>"><img src="images/edit.png"></a></td>
		</tr>
		<%
				i++;
				//ページサイズを超えない範囲でループ
 				rowCounter ++;
				if(rowCounter >= pc.getPageSize()){
					break;
				}

 			}
 			%>
 			<tr>
					<td colspan="6">
				<div class="page">	<%=pc.getCurrentPage()%>/<%=pc.getMaxPage() %></div>
					</td>
					</tr>
		</table>
				<div class="submit">
					<input type="submit" value="FIRST" name="move">
		 			<input type="submit" value="PREV" name="move">
					<input type="submit" value="NEXT" name="move">
					<input type="submit" value="LAST" name="move">
 				</div>

</form>
<tags:Footer1></tags:Footer1>
	<%
	//データベースから切断
	stmt.close();
	con.close();
	}catch(Exception e){
		e.printStackTrace();
	}


	%>
</div>
</body>
</html>