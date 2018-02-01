<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="db.*"%>
<%@ page import="db.ndydb.*"%>
<%@ page import="grid.PagingControl"%>
<%@ taglib prefix	="tags" tagdir="/WEB-INF/tags" %>

<%
	PagingControl pc = null;

	if(session.getAttribute("page")==null){
		pc = new PagingControl();
	}else{

		pc = (PagingControl)session.getAttribute("page");
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

 	// 接続およびSQL実行
	try {
 		// ドライバクラスをロード
		Class.forName(NdyConstants.PG_DRIVERNAME);
		// データベースへ接続
		Connection con = DriverManager.getConnection (NdyConstants.PG_URL ,NdyConstants.PG_USER ,NdyConstants.PG_PASSWORD);
		// ステートメントオブジェクトを生成
		Statement stmt = con.createStatement();

		String sql =
				"select categoryid" 					+
				"		,categoryname" 					+
				"		,description "					+
				"		,linkname "						+
				"		,linkurl "						+
				"from " 								+
				"categories ";

		//ページングのための件数を取得する
		pc.setTotalRecCount(con, sql);
		//リミット句を構成する
		String sqlLimit = "order by categoryid limit " + pc.getPageSize() + " offset " + pc.getOffsetRec();
		//セッションにページオブジェクトを保存する
		session.setAttribute("page", pc);
		ResultSet rs = stmt.executeQuery(sql+ sqlLimit);

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">



</style>
</head>
<body>

		<h2>カテゴリーリスト</h2>

 <form action="CategoriesList.jsp" method="post" > <!--自分にpostしている-->
 	<table border="1">
 		<tr>
 			<th>カテゴリＩＤ</th>
 			<th>カテゴリ名</th>
 			<th>説明</th>
 			<th>リンク名</th>
 			<th>リンクＵＲＬ</th>
 			<th></th>
 		</tr>
 		<%
 			while (rs.next()) {
		%>
			<tr>
				<td><%=rs.getInt("categoryid")  %></td>
				<td><%=rs.getString("categoryname")%></td>
				<td><%=rs.getString("description") %></td>
				<td><%=rs.getString("linkname") %></td>
				<td><%=rs.getString("linkurl") %></td>
				<td><%="<a href =\"./CategoriesSv?categoryid=" + rs.getString("categoryid") + "\">編集</>" %>
			</tr>

		<%} %>
			 <tr>
	 			<td colspan="6">
	 				<%=pc.getCurrentPage()%> / <%=pc.getMaxPage() %>
	 			</td>
 			</tr>
 			<tr>
 				<td colspan="6">
		 			<input type="submit" value="FIRST" name="move">
		 			<input type="submit" value="PREV" name="move">
					<input type="submit" value="NEXT" name="move">
					<input type="submit" value="LAST" name="move">
 				</td>
 			</tr>

 	</table>

 </form>
 <tags:Footer1></tags:Footer1>

 	<%
 		// データベースから切断
		stmt.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>

 </body>
</html>