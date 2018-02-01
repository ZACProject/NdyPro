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
import db.ndydb.Bizcalendar;
import db.ndydb.BizcalendarBn;

/**
 * Servlet implementation class BizcalendarSv
 */
@WebServlet("/BizcalendarSv")
public class BizcalendarSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BizcalendarSv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Bizcalendar.jsp";
		//Error が起こった時の遷移先
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//
		//変数の宣言
		String strCompanyid = ""; 	//会社ID
		String strBizday = "";	//営業日

		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("companyid") != null && request.getParameter("bizday") !=null ) {
			strCompanyid = request.getParameter("companyid");
			strBizday = request.getParameter("bizday");
		}


		//****database処理部****//

		//変数の宣言（出力用のBean）
		BizcalendarBn bn = null;

		//呼出処理（select）
		//リクエストパラメータを配列にセットする
		String[] colSelect = new String[2];
		colSelect[0] = strCompanyid;
		colSelect[1] = strBizday;
		Bizcalendar dao = new Bizcalendar();
		try {
			//select処理
			List<IBean> lstBn = dao.select(colSelect);
			//リストからBeanを取り出す
			bn = (BizcalendarBn)lstBn.get(0);
			//session へセットする
			hs.setAttribute("bizcalendar", bn);
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
		String strRefUrl = "./Bizcalendar.jsp";
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//

		//変数の宣言
		String strCompanyid = "";
		String strBizday = "";
		String strDescription = "";
		String strOpenflg = "";
		String strDeleteflg= "";
		String strInsdatetime	= "";
		String strInsuser = "";
		String strInsmachine = "";
		String strUpddatetime = "";
		String strUpduser = "";
		String strUpdmachine = "";

		//パラメータが送られ停る場合、変数にセットする
		if(request.getParameter("companyid") != null) {
			strCompanyid = request.getParameter("companyid");
		}
		if(request.getParameter("bizday") != null) {
			strBizday = request.getParameter("bizday");
		}
		if(request.getParameter("description") != null) {
			strDescription  = request.getParameter("description");
		}
		if(request.getParameter("openflg") != null) {
			strOpenflg  = request.getParameter("openflg");
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
		BizcalendarBn  bn = null ;

		//room登録のどのブタンが押されているかを判定して処理を実行する
		if(request.getParameter("call_select") != null) {
			//呼出処理  -->  select
			//リクエストパラメータを配列にセットする
			String[] colSelect = new String[2];
			colSelect[0] = strCompanyid;
			colSelect[1] = strBizday;
			Bizcalendar dao = new Bizcalendar();
			try {
				//select 処理
				List<IBean> lstBn = dao.select(colSelect);
				//リストからBeanを取り出す
				bn = (BizcalendarBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("bizcalendar", bn);
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
			String[] collns = new String[8];
			collns[0] = strCompanyid ;
			collns[1] = strBizday ;
			collns[2] = strDescription ;
			collns[3] = strOpenflg ;
			collns[4] = strDeleteflg ;
			collns[5] = strInsdatetime	;
			collns[6] = strInsuser ;
			collns[7] = strInsmachine ;

			//roomのdataアクセスのオブジェクトを生成する
			Bizcalendar dao = new Bizcalendar();
			try {
				List<IBean> lstBn = dao.insert(collns);
				bn = (BizcalendarBn)lstBn.get(0);
				hs.setAttribute("bizcalendar", bn);
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
			String[] colUpd = new String[8];
			colUpd[0] = strDescription ;
			colUpd[1] = strOpenflg ;
			colUpd[2] = strDeleteflg ;
			colUpd[3] = strUpddatetime	;
			colUpd[4] = strUpduser ;
			colUpd[5] = strUpdmachine ;
			colUpd[6] = strCompanyid ;
			colUpd[7] = strBizday ;
			//bizcalendarのdata アクセスクラスのオブジェクトを生成する
			Bizcalendar dao = new Bizcalendar();

			try {
				//update処理
				List<IBean> lstBn = dao.update(colUpd);
				//リストからBeanを取り出す
				bn = (BizcalendarBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("bizcalendar", bn);
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
			String[] colSoftDel = new String[8];
			colSoftDel[0] = strDescription ;
			colSoftDel[1] = strOpenflg ;
			colSoftDel[2] = strDeleteflg ;
			colSoftDel[3] = strUpddatetime	;
			colSoftDel[4] = strUpduser ;
			colSoftDel[5] = strUpdmachine ;
			colSoftDel[6] = strCompanyid ;
			colSoftDel[7] = strBizday ;
			//bizcalendarのdaoオブジェクトを生成する
			Bizcalendar dao = new Bizcalendar();
			try {
				//softdelete 処理
				List<IBean> lstBn = dao.softdelete(colSoftDel);
				//リストからBeanを取り出す
				bn = (BizcalendarBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("bizcalendar", bn);
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
			String[] colDel = new String[2];
			colDel[0] = strCompanyid;
			colDel[1] = strBizday;
			Bizcalendar dao = new Bizcalendar();
			try {
				//delete 処理
				List<IBean> lstBn = dao.delete(colDel);
				//リストからBeanを取り出す
				bn = (BizcalendarBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("bizcalendar", bn);
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
