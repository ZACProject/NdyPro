<%@page import="com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault"%>
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
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY);

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
	.tHeaderCat,.tHeaderCatName,.tHeaderDes,
	.tHeaderLinkname,.tHeaderLinkurl{
		width:158px;
		text-align:center;
		line-height:50px;
	}
	.tHeaderHeight {
		height: 50px;
		text-align: center;
		line-height: 50px;
	}

</style>
</head>
<body>
<tags:header></tags:header>
		<div id="navigation">
			<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./CategoriesListN.jsp">カテゴリーリスト</a>
			</span>
			<span class="Logout">
				ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>
			</span>
		</div>

	 <form action="CategoriesListN.jsp" method="post" > <!--自分にpostしている-->
	 	<table border="1">
	 		<tr>
	 			<th><div class="tHeaderCat tHeaderHeight">カテゴリＩＤ</div></th>
	 			<th><div class="tHeaderCatName tHeaderHeight">カテゴリ名</div></th>
	 			<th><div class="tHeaderDes tHeaderHeitht">説明</div></th>
	 			<th><div class="tHeaderLinkname tHeaderHeight">リンク名</div></th>
	 			<th><div class="tHeaderLinkurl tHeaderHeight">リンクＵＲＬ</div></th>
	 			<th></th>
	 		</tr>
	 		<%
	 			while (rs.next()) {
			%>
				<tr>
					<td title="<%=rs.getInt("categoryid")%>"><%=rs.getInt("categoryid")  %></td>
					<td title="<%=rs.getString("categoryname")%>"><%=rs.getString("categoryname")%></td>
					<td title="<%=rs.getString("description") %>"><%=rs.getString("description") %></td>
					<td title="<%=rs.getString("linkname") %>"><%=rs.getString("linkname") %></td>
					<td class="picture"><a href ="#"><img src="images/danya.png"></a></td>
					<td class="editer"><%="<a href =\"./CategoriesSv?categoryid=" + rs.getString("categoryid") + "\"><img  src=\"images/edit.png\"</>" %>
				</tr>



			<%
					//ページサイズを超えない範囲でループ
	 				rowCounter ++;
					if(rowCounter >= pc.getPageSize()){
						break;
					}

	 			}
	 		%>
				 <tr>
		 			<td colspan="6">
		 			<div class="page"><span>	<%=pc.getCurrentPage()%></span><span> /</span><span> <%=pc.getMaxPage() %></span></div>
		 			</td>
	 			</tr>
	   </table>

	 			 <div class="submit">


			 			<input type="submit" value="FIRST" name="move">
			 			<input  type="submit" value="PREV" name="move">
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


<!-- </div> -->
 </body>
</html>