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

			String sql = "select companyid," 	+
					"categorynamejp,"			+
					"addr1jp,"					+
					"linkname1,"    			+
					"linkurl1 "					+
					"from"	  					+
					" Companies ";
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
	 .tHeaderCmp,.tHeaderCmpName,.tHeaderAddr1jp,
	.tHeaderLinkname1,.tHeaderLinkurl1 {
		width		: 158px;
		text-align: center;

	}

	 /*  .tHeaderCmpName {
		width		: 158px;
		text-align: center;
		line-height: 50px;
	}*/

	.tHeaderHeight {
		/* width:100px; */
		height: 50px;
		text-align: center;
		line-height: 50px;
	}
	/* .tHeaderAddr1jp {
		width		: 100px;
		text-align: center;
		line-height: 50px;
	}
 */

</style>
</head>
<body>
<tags:header></tags:header>


	<div id = "navigation">
		<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./CompaniesListN.jsp">カンパニーリスト</a>

		</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>
	<div id="wrapper">

<form action="CompaniesListN.jsp" method ="post"><!-- 自分にpostしている -->
	<table border ="1">
		<tr>
			<th><div class="tHeaderCmp tHeaderHeight">カンパニID</div></th>
			<th><div class="tHeaderCmpName tHeaderHeight">カンパニ名JP</div></th>
			<th><div class="tHeaderAddr1jp tHeaderHeight">住所1JP</div></th>
			<th><div class="tHeaderLinkname1 tHeaderHeight">室内画像名1</div></th>
			<th><div class="tHeaderLinkurl1 tHeaderHeight">室内画像リンク先1</div></th>
			<th></th>
			</tr>
			<%
				while(rs.next()){
			%>
				<tr>
					<td title="<%=rs.getString("companyid") %>"><%=rs.getString("companyid") %></td>
					<td title="<%=rs.getString("categorynamejp") %>"><%=rs.getString("categorynamejp") %></td>
					<td title="<%=rs.getString("addr1jp") %>"><%=rs.getString("addr1jp") %></td>
					<td title="<%=rs.getString("linkname1") %>"><%=rs.getString("linkname1") %></td>
					<td class="picture"><a href ="#"><img src="images/danya.png"></a></td>
					<td class="editer"><%="<a href=\"./CompaniesSv?companyid="+ rs.getString("companyid")+"\"><img src=\"images/edit.png\"</>" %></td>
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
				<div class="page">	<%=pc.getCurrentPage()%>/<%=pc.getMaxPage() %></div>
					</td>
					</tr>
		</table>
				<div class="submit">
					<input  type="submit" value="FIRST" name="move">
		 			<input  type="submit" value="PREV" name="move">
					<input  type="submit" value="NEXT" name="move">
					<input  type="submit" value="LAST" name="move">
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