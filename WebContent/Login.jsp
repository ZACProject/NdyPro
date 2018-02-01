<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.List" %>
<%@ page import="db.*"%>
<%@ page import="db.ndydb.*"%>


<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
	//ヴァージョン取得
	String strCurrVer = NdyConstants.CURRENT_VERSION;
	//現在のページ
	String strCurrentUrl = request.getRequestURL().toString();
	ServletContext context = request.getServletContext();
	List<IBean> lstBn = null;
	boolean blEnabled = false;

	HttpSession hs = request.getSession(false);

	if(request.getParameter("login") != null){
		//ログイン可能かどうか？
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		lstBn = new Auth().authCertification(userName, password);
		if(lstBn.size() ==1){
			Auth loginBn  =  (Auth)lstBn.get(0);
			if(loginBn.isLogin() ==true){
				String remoteAddr = request.getRemoteAddr();
				loginBn.setRemoteAddr(remoteAddr);
				//アプリケーションパスの取得
				String base = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getContextPath();
				//アプリケーションパスをセットします
				loginBn.setAppPath(base);
				//セッションの開始
				hs = request.getSession(true);
				//成功した場合、ログイン情報をセッションに格納する
				hs.setAttribute("LOGINUSER", loginBn);
				//RequestDispatcher rd = context.getRequestDispatcher("/Wellcome.jsp");
				//rd.forward(request, response);
				response.sendRedirect("./CategoriesListN.jsp");
			}
		}else{
			hs.invalidate();
		}
	}


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href ="Login1.css"type="text/css">
<style type="text/css">
</style>
</head>
<body>
	<form action="./Login.jsp" method="post">
    <div id="wrap">
        <div id="top">
        <img class="photo"src="images/Logo.png">
        <img class="photo1"src="images/ComName.png">
        </div>
        <div id="midd">
            <div class="midd-left"></div> <!-- <img src="images/12.JPG"> -->
            <div id="midd-right">
		     	 <div class="midd-right-head">
			            <img class="user" src="images/user.png">
			            <span>ユーザーログイン</span>
           		 </div>

		<div class="top1">
			<div class="top1-left">ユーザー名</div>
			<input type="text"name="username"  value="">
		</div>



	<div class="userError" id="userError"></div>


	<div class="middle">
		<div class="middle-left">パスワード</div>
		<input type="password"name="password"  value="">
	</div>


	<div class="passwordEro" id="passwordEro"></div>


	 <input class="button" type="submit" name = "login" value="ログイン">
	<!--<div class="loginError"></div>-->



<script type="text/javascript"></script>
        </div>
		</div>

    </div>
 </form>
 <script type="text/javascript">
/*  function check(){

     var username = document.getElementById('username').value;

     var password = document.getElementById('password').value;

     if(username == ''){

         document.getElementById('tip').innerHTML = "用户名不能为空";

         return;

     }

     if(password == ''){

         document.getElementById('tip').innerHTML = "密码不能为空";

         return;

     }
 */

 </script>
</body>
</html>