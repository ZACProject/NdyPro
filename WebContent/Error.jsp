<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//変数の宣言　エラー表示用文字列
	String strErrMsg = "";
	//セッションを取得する
	HttpSession hs = request.getSession();

	//エラーメッセージがセッションに設定されていれば
	//エラー表示用文字列へ内容を代入する。
	if(hs.getAttribute("ErrorMsg") != null){
		strErrMsg = (String)hs.getAttribute("ErrorMsg");
				//エラーメッセージはセッションに入っているので、
		//使用した後消去する。
		hs.setAttribute("ErrorMsg", null);
	}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ndy　エラーページ</title>
<style type="text/css">
	h2,h3{
		 text-align:center;
	}

	table{
		margin-left: auto;
 		margin-right: auto;
	}
</style>
</head>
<body>
	<h2>Errorが発生しました。</h2>
<table>
	<tr>
		<td>
			ご迷惑をおかけして申し訳ございません。<br>
			エラーメッセージは以下の通りです。<br>

			<%=strErrMsg %>

			<br>
		</td>
	</tr>
</table>
</body>
</html>