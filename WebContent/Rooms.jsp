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
	//RoomsBnを所得する
	RoomsBn bn = null;
	if(hs.getAttribute("rooms")!=null){
		//セッションからBizacalendarBnを取り出す
		bn = (RoomsBn)hs.getAttribute("rooms");
	}else{
		//値が入っていないBeanをセットする
		bn = new RoomsBn();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv ="X-UA-Compatible" content="IE=edge"/>
<title>部屋登録</title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Main.css">
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
<link rel = "stylesheet" href="Design/PublicReg.css"type = "text/css">
<!-- <link rel = "stylesheet" href="Design/Rooms.css"type = "text/css"> -->
<style type="text/css">

</style>
</head>
<body>
<tags:header></tags:header>




		<div id="position">
			<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./RoomsListN.jsp">部屋リスト</a>&nbsp;&gt;
				<a href="./Rooms.jsp">部屋登録画面</a>

			</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>

	<form action="./RoomsSv" method ="post">
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
				<th>部屋ID</th>
				<td colspan="6"><input class="text-box" type="text" name="roomid" value="<%=bn.getRoomid()%>"></td>
			</tr>
			<tr>
				<th>カンパニID</th>
				<td colspan="6"><input class="text-box" type="text" name="companyid" value="<%=bn.getCompanyid()%>"></td>
			</tr>
			<tr>
				<th>部屋名JP</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynamejp" value="<%=bn.getCategorynamejp()%>"></td>
			</tr>

			<tr>
				<th>部屋名CN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynamecn" value="<%=bn.getCategorynamecn()%>"></td>
			</tr>
			<tr>
				<th>部屋名EN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynameen" value="<%=bn.getCategorynameen()%>"></td>
			</tr>

			<tr>
				<th>部屋別名JP</th>
				<td colspan="6"><input class="text-box" type="text" name="categorysubnamejp" value="<%=bn.getCategorysubnamejp()%>"></td>
			</tr>
			<tr>
				<th>部屋別名CN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorysubnamecn" value="<%=bn.getCategorysubnamecn()%>"></td>
			</tr>
			<tr>
				<th>部屋別名EN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorysubnameen" value="<%=bn.getCategorysubnameen()%>"></td>
			</tr>
			<tr>
				<th>説明JP</th>
				<td colspan="6"><input class="text-box" type="text" name="descripjp" value="<%=bn.getDescripjp()%>"></td>
			</tr>
			<tr>
				<th>説明CN</th>
				<td colspan="6"><input class="text-box" type="text" name="descripcn" value="<%=bn.getDescripcn()%>"></td>
			</tr>
			<tr>
				<th>説明EN</th>
				<td colspan="6"><input class="text-box" type="text" name="descripen" value="<%=bn.getDescripen()%>"></td>
			</tr>
			<tr>
				<th>補足事項JP</th>
				<td colspan="6"><input class="text-box" type="text" name="supplementjp" value="<%=bn.getSupplementjp()%>"></td>
			</tr>
			<tr>
				<th>補足事項CN</th>
				<td colspan="6"><input class="text-box" type="text" name="supplementcn" value="<%=bn.getSupplementcn()%>"></td>
			</tr>
			<tr>
				<th>補足事項EN</th>
				<td colspan="6"><input class="wenben" type="text" name="supplementen" value="<%=bn.getSupplementen()%>"></td>
			</tr>
			<tr>
				<th>単価JP</th>
				<td colspan="6"><input class="text-box" type="text" name="pricejp" value="<%=bn.getPricejp()%>"></td>
			</tr>
			<tr>
				<th>単価CN</th>
				<td colspan="6"><input class="text-box" type="text" name="pricecn" value="<%=bn.getPricecn()%>"></td>
			</tr>
			<tr>
				<th>単価EN</th>
				<td colspan="6"><input class="text-box" type="text" name="priceen" value="<%=bn.getPriceen()%>"></td>
			</tr>
			<tr>
				<th>部屋単位JP</th>
				<td colspan="6"><input class="text-box" type="text" name="runitnamejp" value="<%=bn.getRunitnamejp()%>"></td>
			</tr>
			<tr>
				<th>部屋単位CN</th>
				<td colspan="6"><input class="text-box" type="text" name="runitnamecn" value="<%=bn.getRunitnamecn()%>"></td>
			</tr>
			<tr>
				<th>部屋単位EN</th>
				<td colspan="6"><input class="text-box" type="text" name="runitnameen" value="<%=bn.getRunitnameen()%>"></td>
			</tr>
			<tr>
				<th>定員</th>
				<td colspan="6"><input class="text-box" type="text" name="capactiy" value="<%=bn.getCapactiy()%>"></td>
			</tr>
			<tr>
				<th>定員(小)</th>
				<td colspan="6"><input class="text-box" type="text" name="capactiy2" value="<%=bn.getCapactiy2()%>"></td>
			</tr>
			<tr>
				<th>定員単位JP</th>
				<td colspan="6"><input class="text-box" type="text" name="punitnamejp" value="<%=bn.getPunitnamejp()%>"></td>
			</tr>
			<tr>
				<th>定員単位CN</th>
				<td colspan="6"><input class="text-box" type="text" name="punitnamecn" value="<%=bn.getPunitnamecn()%>"></td>
			</tr>
			<tr>
				<th>定員単位EN</th>
				<td colspan="6"><input class="text-box" type="text" name="punitnameen" value="<%=bn.getPunitnameen()%>"></td>
			</tr>
			<tr>
				<th>室内画像名1</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname1" value="<%=bn.getLinkname1()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先1</th>
				<td colspan="6"><input class="text-box" type="text" name="linkurl1" value="<%=bn.getLinkurl1()%>"></td>
			</tr>
			<tr>
				<th>室内画像名2</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname2" value="<%=bn.getLinkname2()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先2</th>
				<td colspan="6"><input class="text-box" type="text" name="linkurl2" value="<%=bn.getLinkurl2()%>"></td>
			</tr>
			<tr>
				<th>室内画像名3</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname3" value="<%=bn.getLinkname3()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先3</th>
				<td colspan="6"><input class="text-box" type="text" name="linkurl3" value="<%=bn.getLinkurl3()%>"></td>
			</tr>
			<tr>
				<th>表示数</th>
				<td colspan="6"><input class="text-box" type="text" name="dispnum" value="<%=bn.getDispnum()%>"></td>
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
	<div class="box2">
		<div class="roomid">
			<label for="roomid">部屋ID</label>
			<input type="text" name="roomid" id="roomid" value="<%=bn.getRoomid()%>">
		</div>
		<div class="companyid">
			<label for="companyid">カンパニID</label>
			<input type="text" name="companyid" id="companyid" value="<%=bn.getCompanyid()%>">
		</div>
	</div>
	<div class="box3">
		<div class="categorynamejp">
			<label for="categorynamejp">部屋名JP</label>
			<input type="text" name="categorynamejp" id="categorynamejp" value="<%=bn.getCategorynamejp()%>">
		</div>
		<div class="categorynamecn">
			<label for="categorynamecn">部屋名CN</label>
			<input type="text" name="categorynamecn" id="categorynamecn" value="<%=bn.getCategorynamecn()%>">
		</div>
		<div class="categorynameen">
			<label for="categorynameen">部屋名EN</label>
			<input type="text" name="categorynameen" id="categorynameen" value="<%=bn.getCategorynameen()%>">
		</div>
	</div>
	<div class="box3">
		<div class="categorysubnamejp">
			<label for="categorysubnamejp">部屋別名JP</label>
			<input type="text" name="categorysubnamejp" id="categorysubnamejp" value="<%=bn.getCategorysubnamejp()%>">
		</div>
		<div class="categorysubnamecn">
			<label for="categorysubnamecn">部屋別名CN</label>
			<input type="text" name="categorysubnamecn" id="categorysubnamecn" value="<%=bn.getCategorysubnamecn()%>">
		</div>
		<div class="categorysubnameen">
			<label for="categorysubnameen">部屋別名EN</label>
			<input type="text" name="categorysubnameen" id="categorysubnameen" value="<%=bn.getCategorysubnameen()%>">
		</div>
	</div>
	<div class="box4">
		<div class="descripjp">
			<label for="descripjp">説明JP</label>
			<input type="text" name="descripjp" id="descripjp" value="<%=bn.getDescripjp()%>">
		</div>
		<div class="descripcn">
			<label for="descripjcn">説明CN</label>
			<input type="text" name="descripcn" id="descripcn" value="<%=bn.getDescripcn()%>">
		</div>
		<div class="descripen">
			<label for="descripen">説明EN</label>
			<input type="text" name="descripen" id="descripen" value="<%=bn.getDescripen()%>">
		</div>
	</div>
	<div class="box3">
		<div class="supplementjp">
			<label for="supplementjp">補足事項JP</label>
			<input type="text"  name="supplementjp" id="supplementjp" value="<%=bn.getSupplementjp()%>">
		</div>
		<div class="supplementcn">
			<label for="supplementcn">補足事項CN</label>
			<input type="text"  name="supplementcn" id="supplementcn" value="<%=bn.getSupplementcn()%>">
		</div>
		<div class="supplementen">
			<label for="supplementen">補足事項EN</label>
			<input type="text"  name="supplementen" id="supplementen" value="<%=bn.getSupplementen()%>">
		</div>
	</div>
	<div class="box3">
		<div class="pricejp">
			<label for="pricejp">単価JP</label>
			<input type="text" name="pricejp" id="pricejp" value="<%=bn.getPricejp()%>">
		</div>
		<div class="pricecn">
			<label for="pricecn">単価CN</label>
			<input type="text" name="pricecn" id="pricecn" value="<%=bn.getPricecn()%>">
		</div>
		<div class="priceen">
			<label for="priceen">単価EN</label>
			<input type="text" name="priceen" id="priceen" value="<%=bn.getPriceen()%>">
		</div>
	</div>
	<div class="box3">
		<div class="runitnamejp">
			<label for="runitnamejp">部屋単位JP</label>
			<input type="text" name="runitnamejp" id="runitnamejp" value="<%=bn.getRunitnamejp()%>">
		</div>
		<div class="runitnamecn">
			<label for="runitnamecn">部屋単位CN</label>
			<input type="text" name="runitnamecn" id="runitnamecn" value="<%=bn.getRunitnamecn()%>">
		</div>
		<div class="runitnameen">
			<label for="runitnameen">部屋単位EN</label>
			<input type="text" name="runitnameen" id="runitnameen" value="<%=bn.getRunitnameen()%>">
		</div>
	</div>
	<div class="box2">
		<div class="capactiy">
			<label for="capactiy">定員</label>
			<input type="text" name="capactiy" id="capactiy" value="<%=bn.getCapactiy()%>">
		</div>
		<div class="capactiy2">
			<label for="capactiy2">定員(小)</label>
			<input type="text" name="capactiy2" id="capactiy2" value="<%=bn.getCapactiy2()%>">
		</div>
	</div>
	<div class="box3">
		<div class="punitnamejp">
			<label for="punitnamejp">定員単位JP</label>
			<input type="text" name="punitnamejp" id="punitnamejp" value="<%=bn.getPunitnamejp()%>">
		</div>
		<div class="punitnamecn">
			<label for="punitnamecn">定員単位CN</label>
			<input type="text" name="punitnamecn" id="punitnamecn" value="<%=bn.getPunitnamecn()%>">
		</div>
		<div class="punitnameen">
			<label for="punitnameen">定員単位EN</label>
			<input type="text" name="punitnameen" id="punitnameen" value="<%=bn.getPunitnameen()%>">
		</div>
	</div>
	<div class="box2">
		<div class="linkname1">
			<label for="linkname1">室内画像名1</label>
			<input type="text" name="linkname1" id="linkname1" value="<%=bn.getLinkname1()%>">
		</div>
		<div class="linkurl1">
			<label for="linkurl1">リンク先1</label>
			<input type="file" name="linkurl1" id="linkurl1" value="<%=bn.getLinkurl1()%>">
		</div>
	</div>
	<div class="box2">
		<div class="linkname2">
			<label for="linkname2">室内画像名2</label>
			<input type="text" name="linkname2" id="linkname2" value="<%=bn.getLinkname2()%>">
		</div>
		<div class="linkurl2">
			<label for="linkurl2">リンク先2</label>
			<input type="file" name="linkurl2" id="linkurl2" value="<%=bn.getLinkurl2()%>">
		</div>
	</div>
	<div class="box2">
		<div class="linkname3">
			<label for="linkname3">室内画像名3</label>
			<input type="text" name="linkname3" id="linkname3" value="<%=bn.getLinkname3()%>">
		</div>
		<div class="linkurl3">
			<label for="linkurl3">リンク先3</label>
			<input type="file" name="linkurl3" id="linkurl3" value="<%=bn.getLinkurl3()%>">
		</div>
	</div>
	<div class="box1">
		<div class="dispnum">
			<label for="dispnum">表示数</label>
			<input type="text" name="dispnum" id="dispnum" value="<%=bn.getDispnum()%>">
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