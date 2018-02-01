package sv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.IBean;
import db.InputErrorException;
import db.ndydb.Users;
import db.ndydb.UsersBn;

/**
 * Servlet implementation class UsersSv
 */
@WebServlet("/UsersSv")
public class UsersSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersSv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//セッションを宣言する
		HttpSession hs = request.getSession();

		//遷移先
		String strRefUrl = "./Users.jsp";
		//Error が起こった時の遷移先
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//
		//変数の宣言
		String strUserid = ""; 	//ユーザーID
		String strUsername = "";	//ユーザー名
		String strUserpass = "";	//ユーザーパスワード

		//セッションからloginBnを取り出す
		String strUser = request.getParameter("strUser");
		UsersBn bn = (UsersBn)hs.getAttribute(strUser);
		System.out.println(bn == null);
		strUserid = bn.getUserid();
		strUsername = bn.getUsername();
		strUserpass = bn.getPassword();

		System.out.println("strUserid=  " + strUserid);
		System.out.println("strUsername=  " + strUsername);
		System.out.println("strUserpass=  " + strUserpass);


		//****database処理部****//

		//変数の宣言（出力用のBean）
		//UsersBn bn = null;

		//呼出処理（select）
		//リクエストパラメータを配列にセットする
		String[] colSelect = new String[3];
		colSelect[0] = strUserid;
		colSelect[1] = strUsername;
		colSelect[2] = strUserpass;
		Users dao = new Users();
		try {
			//select処理
			List<IBean> lstBn = dao.select(colSelect);
			//リストからBeanを取り出す
			bn = (UsersBn)lstBn.get(0);
			//session へセットする
			hs.setAttribute("users", bn);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		}

		//****画面遷移****//
		response.sendRedirect(strRefUrl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		//コンテンツ タイプ と エンコードを指定する]
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");

		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Users.jsp";
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//

		//変数の宣言
		String strUserid = "";
		String strUsername = "";
		String strPassword = "";
		String strLinkname = "";
		String strLinkurl = "";
		String strFirstname = "";
		String strLastname = "";
		String strUserlevel = "";
		String strCompany = "";
		String strDept = "";
		String strRegdate = "";
		String strDuedate = "";
		String strDeleteflg= "";
		String strInsdatetime	= "";
		String strInsuser = "";
		String strInsmachine = "";
		String strUpddatetime = "";
		String strUpduser = "";
		String strUpdmachine = "";

		//パラメータが送られ停る場合、変数にセットする
		if(request.getParameter("userid") != null) {
			strUserid = request.getParameter("userid");
		}
		if(request.getParameter("username") != null) {
			strUsername = request.getParameter("username");
		}
		if(request.getParameter("password") != null) {
			strPassword  = request.getParameter("password");
		}
		if(request.getParameter("linkname") != null) {
			strLinkname  = request.getParameter("linkname");
		}
		if(request.getParameter("linkurl") != null) {
			strLinkurl  = request.getParameter("linkurl");
		}
		if(request.getParameter("firstname") != null) {
			strFirstname  = request.getParameter("firstname");
		}

		if(request.getParameter("lastname") != null) {
			strLastname   = request.getParameter("lastname");
		}
		if(request.getParameter("userlevel") != null) {
			strUserlevel   = request.getParameter("userlevel");
		}
		if(request.getParameter("company") != null) {
			strCompany   = request.getParameter("company");
		}
		if(request.getParameter("dept") != null) {
			strDept   = request.getParameter("dept");
		}
		if(request.getParameter("regdate") != null) {
			strRegdate  = request.getParameter("regdate");
		}
		if(request.getParameter("duedate") != null) {
			strDuedate   = request.getParameter("duedate");
		}
		if(request.getParameter("deleteflg") != null) {
			strDeleteflg  = request.getParameter("deleteflg");
		}
		if(request.getParameter("insuser") != null) {
			strInsuser   = request.getParameter("insuser");
		}
		if(request.getParameter("insmachine") != null) {
			strInsmachine   = request.getParameter("insmachine");
		}
		if(request.getParameter("updateuser") != null) {
			strUpduser   = request.getParameter("updateuser");
		}
		if(request.getParameter("updmachine") != null) {
			strUpdmachine   = request.getParameter("updmachine");
		}

		//****database処理部****//

		//変数の宣言(出力のBean)
		UsersBn  bn = null ;

		//user登録のどのボタンが押されているかを判定して処理を実行する
		if(request.getParameter("call_select") != null) {
			//呼出処理  -->  select
			//リクエストパラメータを配列にセットする
			String[] colSelect = new String[3];
			colSelect[0] = strUserid;
			colSelect[1] = strUsername;
			colSelect[2] = strPassword;
			Users dao = new Users();
			try {
				//select 処理
				List<IBean> lstBn = dao.select(colSelect);
				//リストからBeanを取り出す
				bn = (UsersBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("users", bn);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_insert") != null) {
			//登録処理 ---> insert
			//リクエストパラメータを配列にセットする
			String[] collns = new String[16];
			collns[0] = strUserid;
			collns[1] = strUsername;
			collns[2] = strPassword;
			collns[3] = strLinkname;
			collns[4] = strLinkurl;
			collns[5] = strFirstname;
			collns[6] = strLastname;
			collns[7] = strUserlevel;
			collns[8] = strCompany;
			collns[9] = strDept;
			collns[10] = strRegdate;
			collns[11] = strDuedate;
			collns[12] = strDeleteflg;
			collns[13] = strInsdatetime;
			collns[14] = strInsuser;
			collns[15] = strInsmachine;
			//roomのdataアクセスのオブジェクトを生成する
			Users dao = new Users();
			try {
				List<IBean> lstBn = dao.insert(collns);
				bn = (UsersBn)lstBn.get(0);
				hs.setAttribute("users", bn);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_update") != null) {
			//更新処理 ---> update
			//リクエストパラメータを配列にセットする
			String[] colUpd = new String[16];
			colUpd[0] = strLinkname;
			colUpd[1] = strLinkurl;
			colUpd[2] = strFirstname;
			colUpd[3] = strLastname;
			colUpd[4] = strUserlevel;
			colUpd[5] = strCompany;
			colUpd[6] = strDept;
			colUpd[7] = strRegdate ;
			colUpd[8] = strDuedate ;
			colUpd[9] = strDeleteflg ;
			colUpd[10] = strUpddatetime	;
			colUpd[11] = strUpduser ;
			colUpd[12] = strUpdmachine ;
			colUpd[13] = strUserid ;
			colUpd[14] = strUsername ;
			colUpd[15] = strPassword ;
			//userのdata アクセスクラスのオブジェクトを生成する
			Users dao = new Users();

			try {
				//update処理
				List<IBean> lstBn = dao.update(colUpd);
				//リストからBeanを取り出す
				bn = (UsersBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("users", bn);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_softdelete") != null) {
			//論理削除処理 ---> softdelete
			//論理削除の場合、delateflgがいかなる値であっても
			//データアクセス側で'true'がセットされる。
			//リクエストパラメータを配列にセットする
			String[] colSoftDel = new String[16];
			colSoftDel[0] = strLinkname;
			colSoftDel[1] = strLinkurl;
			colSoftDel[2] = strFirstname;
			colSoftDel[3] = strLastname;
			colSoftDel[4] = strUserlevel;
			colSoftDel[5] = strCompany;
			colSoftDel[6] = strDept;
			colSoftDel[7] = strRegdate ;
			colSoftDel[8] = strDuedate ;
			colSoftDel[9] = strDeleteflg ;
			colSoftDel[10] = strUpddatetime	;
			colSoftDel[11] = strUpduser ;
			colSoftDel[12] = strUpdmachine ;
			colSoftDel[13] = strUserid ;
			colSoftDel[14] = strUsername ;
			colSoftDel[15] = strPassword ;
			//userのdaoオブジェクトを生成する
			Users dao = new Users();
			try {
				//softdelete 処理
				List<IBean> lstBn = dao.softdelete(colSoftDel);
				//リストからBeanを取り出す
				bn = (UsersBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("users", bn);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_delete") != null) {
			//物理削除処理 ---> delete
			//リクエストパラメータを配列にセットする
			String[] colDel = new String[3];
			colDel[0] = strUserid;
			colDel[1] = strUsername;
			colDel[2] = strPassword;
			Users dao = new Users();
			try {
				//delete 処理
				List<IBean> lstBn = dao.delete(colDel);
				//リストからBeanを取り出す
				bn = (UsersBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("users", bn);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}

		//****画面遷移****//
		response.sendRedirect(strRefUrl);
	}

}
