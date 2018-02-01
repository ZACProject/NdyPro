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
	//companiesBnを取得する
	CompaniesBn bn = null;

	if(hs.getAttribute("companies") !=null){
		//セッションからCompaniesBnを取り出す
		bn =(CompaniesBn)hs.getAttribute("companies");
	}else{
		//値が入っていないBeanをセットする
		bn = new CompaniesBn();
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv ="X-UA-Compatible" content="IE=edge"/>
<title>カンパに登録</title>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="./css/Main.css">
<link rel = "stylesheet" href="Wellcome.css"type = "text/css">
<link rel = "stylesheet" href="Design/PublicReg.css"type = "text/css">
<!-- <link rel = "stylesheet" href="Design/Companies.css"type = "text/css"> -->
<style type="text/css">


</style>
</head>
<body>
<tags:header></tags:header>




		<div id="position">
			<span  class="navi">
				<a href="./CategoriesListN.jsp">トップページ</a>&nbsp;&gt;
				<a href="./CompaniesListN.jsp">カンパニリスト</a>&nbsp;&gt;
				<a href="./Companies.jsp">カンパニ登録画面</a>

			</span>
			<span class="Logout">
			ようこそ,<%= ((Auth)session.getAttribute("LOGINUSER")).getLinkName()%>さん!!!
				<a href="./Logout.jsp">ログアウト</a>

			</span>
	</div>

	<form action="./CompaniesSv" method ="post">
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
				<th>カンパニID</th>
				<td colspan="6"><input class="text-box" type="text" name="companyid" value="<%=bn.getCompanyid()%>"></td>
			</tr>
			<tr>
				<th>カンパニ名JP</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynamejp" value="<%=bn.getCategorynamejp()%>"></td>
			</tr>
			<tr>
				<th>カンパニ名CN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynamecn" value="<%=bn.getCategorynamecn()%>"></td>
			</tr>
			<tr>
				<th>カンパニ名EN</th>
				<td colspan="6"><input class="text-box" type="text" name="categorynameen" value="<%=bn.getCategorynameen()%>"></td>
			</tr>
			<tr>
				<th>住所1JP</th>
				<td colspan="6"><input class="text-box" type="text" name="addr1jp" value="<%=bn.getAddr1jp()%>"></td>
			</tr>
			<tr>
				<th>住所2JP</th>
				<td colspan="6"><input class="text-box" type="text" name="addr2jp" value="<%=bn.getAddr2jp()%>"></td>
			</tr>
			<tr>
				<th>住所3JP</th>
				<td colspan="6"><input class="text-box" type="text" name="addr3jp" value="<%=bn.getAddr3jp()%>"></td>
			</tr>
			<tr>
				<th>郵便番号JP</th>
				<td colspan="6"><input class="text-box" type="text" name="zipjp" value="<%=bn.getZipjp()%>"></td>
			</tr>
			<tr>
				<th>住所1CN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr1cn" value="<%=bn.getAddr1cn()%>"></td>
			</tr>
			<tr>
				<th>住所2CN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr2cn" value="<%=bn.getAddr2cn()%>"></td>
			</tr>
			<tr>
				<th>住所3CN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr3cn" value="<%=bn.getAddr3cn()%>"></td>
			</tr>
			<tr>
				<th>郵便番号CN</th>
				<td colspan="6"><input class="text-box" type="text" name="zipcn" value="<%=bn.getZipcn()%>"></td>
			</tr>
			<tr>
				<th>住所1EN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr1en" value="<%=bn.getAddr1en()%>"></td>
			</tr>
			<tr>
				<th>住所2EN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr2en" value="<%=bn.getAddr2en()%>"></td>
			</tr>
			<tr>
				<th>住所3EN</th>
				<td colspan="6"><input class="text-box" type="text" name="addr3en" value="<%=bn.getAddr3en()%>"></td>
			</tr>
			<tr>
				<th>郵便番号EN</th>
				<td colspan="6"><input class="text-box" type="text" name="zipen" value="<%=bn.getZipen()%>"></td>
			</tr>
			<tr>
				<th>アクセス1JP</th>
				<td colspan="6"><input class="text-box" type="text" name="access1jp" value="<%=bn.getAccess1jp()%>"></td>
			</tr>
			<tr>
				<th>アクセス2JP</th>
				<td colspan="6"><input class="text-box" type="text" name="access2jp" value="<%=bn.getAccess2jp()%>"></td>
			</tr>
			<tr>
				<th>アクセス1CN</th>
				<td colspan="6"><input class="text-box" type="text" name="access1cn" value="<%=bn.getAccess1cn()%>"></td>
			</tr>
			<tr>
				<th>アクセス2CN</th>
				<td colspan="6"><input class="text-box" type="text" name="access2cn" value="<%=bn.getAccess2cn()%>"></td>
			</tr>
			<tr>
				<th>アクセス1EN</th>
				<td colspan="6"><input class="text-box" type="text" name="access1en" value="<%=bn.getAccess1en()%>"></td>
			</tr>
			<tr>
				<th>アクセス2EN</th>
				<td colspan="6"><input class="text-box" type="text" name="access2en" value="<%=bn.getAccess2en()%>"></td>
			</tr>
			<tr>
				<th>TEL(代表電話)</th>
				<td colspan="6"><input class="text-box" type="text" name="tel" value="<%=bn.getTel()%>"></td>
			</tr>
			<tr>
				<th>Fax</th>
				<td colspan="6"><input class="text-box" type="text" name="fax" value="<%=bn.getFax()%>"></td>
			</tr>
			<tr>
				<th>メール</th>
				<td colspan="6"><input class="text-box" type="text" name="email" value="<%=bn.getEmail()%>"></td>
			</tr>
			<tr>
				<th>ホームページアドレス</th>
				<td colspan="6"><input class="text-box" type="text" name="homeurl" value="<%=bn.getHomeurl()%>"></td>
			</tr>
			<tr>
				<th>室内画像名1</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname1" value="<%=bn.getLinkname1()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先1</th>
				<td colspan="6"><input class="text-box" type="file" name="linkurl1" value="<%=bn.getLinkurl1()%>"></td>
			</tr>
			<tr>
				<th>室内画像名2</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname2" value="<%=bn.getLinkname2()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先2</th>
				<td colspan="6"><input class="text-box" type="file" name="linkurl2" value="<%=bn.getLinkurl2()%>"></td>
			</tr>
			<tr>
				<th>室内画像名3</th>
				<td colspan="6"><input class="text-box" type="text" name="linkname3" value="<%=bn.getLinkname3()%>"></td>
			</tr>
			<tr>
				<th>室内画像リンク先3</th>
				<td colspan="6"><input class="text-box" type="file" name="linkurl3" value="<%=bn.getLinkurl3()%>"></td>
			</tr>
			<tr>
				<th>表示数</th>
				<td colspan="6"><input class="text-box"  type="text" name="dispnum" value="<%=bn.getDispnum()%>"></td>
			</tr>
			<tr>
				<th>開始日</th>
				<td  colspan="6"><input class="text-box"  type="text" name="regdate" value="<%=bn.getRegdate() %>"></td>
			</tr>
			<tr>
				<th>終了日</th>
				<td  colspan="6"><input class="text-box" type="text" name="duedate" value="<%=bn.getDuedate() %>"></td>
			</tr>
		</table> --%>
<div class="wrap">
	<div class="box1">
		<div class="companyid">
			<label for="companyid">カンパニID</label>
			<input type="text" name="companyid" id="companyid" value="<%=bn.getCompanyid()%>">
		</div>
	</div>
	<div class="box3">
		<div class="categorynamejp">
			<label for="categorynamejp">会社名JP</label>
			<input type="text" name="categorynamejp" id="categorynamejp" value="<%=bn.getCategorynamejp()%>">
		</div >
		<div class="categorynamecn">
			<label for="categorynamecn">会社名CN</label>
			<input type="text" name="categorynamecn" id="categorynamecn" value="<%=bn.getCategorynamecn()%>">
		</div>
		<div class="categorynameen">
			<label for="categorynameen">会社名EN</label>
			<input type="text" name="categorynameen" id="categorynameen" value="<%=bn.getCategorynameen()%>">
		</div>
	</div>
	<div class="box3">
		<div class="addr1jp">
			<label for="addr1jp">住所1JP</label>
			<input type="text" name="addr1jp" id="addr1jp" value="<%=bn.getAddr1jp()%>">
		</div>
		<div class="addr2jp">
			<label for="addr2jp">住所2JP</label>
			<input type="text" name="addr2jp" id="addr2jp" value="<%=bn.getAddr2jp()%>">
		</div>
		<div class="addr3jp">
			<label for="addr3jp">住所3JP</label>
			<input type="text" name="addr3jp" id="addr3jp" value="<%=bn.getAddr3jp()%>">
		</div>
	</div>
	<div class="box1">
		<div class="zipjp">
			<label for="zipjp">郵便番号JP</label>
			<input type="text" name="zipjp" id="zipjp" value="<%=bn.getZipjp()%>">
		</div>
	</div>
	<div class="box3">
		<div class="addr1cn">
			<label for="addr1cn">住所1CN</label>
			<input type="text" name="addr1cn" id="addr1cn" value="<%=bn.getAddr1cn()%>">
		</div>
		<div class="addr2cn">
			<label for="addr2cn">住所2CN</label>
			<input type="text" name="addr2cn" id="addr2cn" value="<%=bn.getAddr2cn()%>">
		</div>
		<div class="addr3cn">
			<label for="addr3cn">住所3CN</label>
			<input type="text" name="addr3cn" id="addr3cn" value="<%=bn.getAddr3cn()%>">
		</div>
	</div>
	<div class="box1">
		<div class="zipcn">
			<label for="zipcn">郵便番号CN</label>
			<input type="text" name="zipcn" id="zipcn" value="<%=bn.getZipcn()%>">
		</div>
	</div>
	<div class="box3">
		<div class="addr1en">
			<label for="addr1en">住所1EN</label>
			<input type="text" name="addr1en" id="addr1en" value="<%=bn.getAddr1en()%>">
		</div>
		<div class="addr2en">
			<label for="addr2en">住所2EN</label>
			<input type="text" name="addr2en" id="addr2en" value="<%=bn.getAddr2en()%>">
		</div>
		<div class="addr3en">
			<label for="addr3en">住所3EN</label>
			<input type="text" name="addr3en" id="addr3en" value="<%=bn.getAddr3en()%>">
		</div>
	</div>
	<div class="box1">
		<div class="zipen">
			<label for="zipen">郵便番号EN</label>
			<input type="text" name="zipen" id="zipen" value="<%=bn.getZipen()%>">
		</div>
	</div>
	<div class="box2">
		<div class="access1jp">
			<label for="access1jp">アクセス1JP</label>
			<input type="text" name="access1jp" id="access1jp" value="<%=bn.getAccess1jp()%>">
		</div>
		<div class="access2jp">
			<label for="access2jp">アクセス2JP</label>
			<input type="text" name="access2jp" id="access2jp" value="<%=bn.getAccess2jp()%>">
		</div>
	</div>
	<div class="box2">
		<div class="access1cn">
			<label for="access1cn">アクセス1CN</label>
			<input type="text" name="access1cn" id="access1cn" value="<%=bn.getAccess1cn()%>">
		</div>
		<div class="access2cn">
			<label for="access2cn">アクセス2CN</label>
			<input type="text" name="access2cn" id="access2cn" value="<%=bn.getAccess2cn()%>">
		</div>
	</div>
	<div class="box2">
		<div class="access1en">
			<label for="access1en">アクセス1EN</label>
			<input type="text" name="access1en" id="access1en" value="<%=bn.getAccess1en()%>">
		</div>
		<div class="access2en">
			<label for="access2en">アクセス2EN</label>
			<input type="text" name="access2en" id="access2en" value="<%=bn.getAccess2en()%>">
		</div>
	</div>
	<div class="box3">
		<div class="tel">
			<label for="tel">TEL</label>
			<input type="text" name="tel" id="tel" value="<%=bn.getTel()%>">
		</div>
		<div class="fax">
			<label for="fax">Fax</label>
			<input type="text" name="fax" id="fax" value="<%=bn.getFax()%>">
		</div>
		<div class="email">
			<label for="email">メール</label>
			<input type="text" name="email" id="email" value="<%=bn.getEmail()%>">
		</div>
	</div>
	<div class="box1">
		<div class="homeurl">
			<label for="homeurl">アドレス</label>
			<input type="text" name="homeurl" id="homeurl" value="<%=bn.getHomeurl()%>">
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