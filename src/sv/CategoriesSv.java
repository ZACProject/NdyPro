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
import db.ndydb.Categories;
import db.ndydb.CategoriesBn;

/**
 * Servlet implementation class CategoriesSv
 */
@WebServlet("/CategoriesSv")
public class CategoriesSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesSv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//コンテンツタイプトエンコードを指定する
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Categories.jsp";
		//Errorが起こった時の遷移先
		String strErrorUrl ="./Error.jsp";

		//*****パラメータ受信部*****//

		//変数の宣言
		String strCategoryId 		= 		"";		//カテゴリID

		//Categories.jspからのリクエストパラメータのセット
		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("categoryid") != null) {
			strCategoryId = request.getParameter("categoryid");
		}
		//*****データベース処理部*****//

		//変数を宣言する（出力用のBean）
		CategoriesBn bn = null;

		//呼出処理（select）
		//リクエストパラメータを配列にセットする
		String[] colSelect = new String[1];
		colSelect[0] = strCategoryId;
		Categories dao = new Categories();

		try {
			//Select処理
			List<IBean> lstBn = dao.select(colSelect);
			//リストからBeanを取り出す
			 bn = (CategoriesBn)lstBn.get(0);
			 //セッションへセットする。
			 hs.setAttribute("categories", bn);

		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		}


		//*****画面遷移*****
		response.sendRedirect(strRefUrl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response)

		//コンテンツタイプトエンコードを指定する
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Categories.jsp";
		//Errorが起こった時の遷移先
		String strErrorUrl ="./Error.jsp";

		//*****パラメータ受信部*****//

		//変数の宣言
		String strCategoryId 		= 		"";		//カテゴリID
		String strCategoryName 	=	 	"";		//カテゴリ名
		String strDescription 	= 		"";		//説明
		String strLinkName 		= 		"";		//リンク名
		String strLinkUrl			= 		"";		//リンク先
		String strRegdate  		= 		"";		//開始日
		String strDuedate 			= 		"";		//終了日
		String strDeleteflg 		= 		"";		//削除フラグ
		String strInsdatetime 	= 		"";		//登録日時		(Dao側でセットする)
		String strInsuser			= 		"";		//登録ユーザー名
		String strInsmachine 		= 		"";		//登録端末
		String strUpddatetime 	= 		"";		//更新日時		(Dao側でセットする)
		String strUpduser			= 		"";		//更新ユーザー
		String strUpdmachine 		= 		"";		//更新端末

		//Categories.jspからのリクエストパラメータのセット
		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("categoryid") != null) {
			strCategoryId = request.getParameter("categoryid");
		}
		if(request.getParameter("categoryname") != null) {
			strCategoryName = request.getParameter("categoryname");
		}
		if(request.getParameter("description") != null) {
			strDescription = request.getParameter("description");
		}
		if(request.getParameter("linkname") != null) {
			strLinkName = request.getParameter("linkname");
		}
		if(request.getParameter("linkurl") != null) {
			strLinkUrl = request.getParameter("linkurl");
		}
		if(request.getParameter("regdate") != null) {
			strRegdate = request.getParameter("regdate");
		}
		if(request.getParameter("duedate") != null) {
			strDuedate = request.getParameter("duedate");
		}
		if(request.getParameter("deleteflg") != null) {
			strDeleteflg= request.getParameter("deleteflg");
		}
		if(request.getParameter("insuser") != null) {
			strInsuser = request.getParameter("insuser");
		}
		if(request.getParameter("insmachine") != null) {
			strUpddatetime  = request.getParameter("insmachine");
		}
		if(request.getParameter("upduser") != null) {
			strUpduser = request.getParameter("upduser");
		}
		if(request.getParameter("updmachine") != null) {
			strUpdmachine = request.getParameter("updmachine");
		}


		//*****データベース処理部*****//

		//変数を宣言する（出力用のBean）
		CategoriesBn bn = null;

		//カテゴリ登録のどのボタンが押されているかを判定して
		//処理を実行する
		if(request.getParameter("call_select") != null) {

			//呼出処理（select）
			//リクエストパラメータを配列にセットする
			String[] colSelect = new String[1];
			colSelect[0] = strCategoryId;
			Categories dao = new Categories();

			try {
				//Select処理
				List<IBean> lstBn = dao.select(colSelect);
				//リストからBeanを取り出す
				 bn = (CategoriesBn)lstBn.get(0);
				 //セッションへセットする。
				 hs.setAttribute("categories", bn);

			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}

		}else if(request.getParameter("call_insert") != null){

			//登録処理（insert）
			//リクエストパラメータを配列にセットする
			String[] colIns = new String[11];
			colIns[0] = strCategoryId	;
			colIns[1] = strCategoryName	;
			colIns[2] = strDescription	;
			colIns[3] = strLinkName		;
			colIns[4] = strLinkUrl		;
			colIns[5] = strRegdate		;
			colIns[6] = strDuedate		;
			colIns[7] = strDeleteflg	;	//削除フラグ
			colIns[8] = strInsdatetime	;	//登録日時
			colIns[9] = strInsuser		;	//登録者
			colIns[10] = strInsmachine	;	//登録端末

			//カテゴリーのDataアクセスクラスのオブジェクトを生成する
			Categories dao = new Categories();

			try {
				//insert処理
				List<IBean> lstBn = dao.insert(colIns);
				//リストからBeanを取り出す
				bn = (CategoriesBn)lstBn.get(0);
				//セッションへセットする。
				hs.setAttribute("categories", bn);

			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}

		}else if(request.getParameter("call_update") != null){

			//更新処理（update）
			//リクエストパラメータを配列にセットする
			String[] colUpd = new String[11];
			colUpd[0] = strCategoryName	;
			colUpd[1] = strDescription	;
			colUpd[2] = strLinkName		;
			colUpd[3] = strLinkUrl		;
			colUpd[4] = strRegdate		;
			colUpd[5] = strDuedate		;
			colUpd[6] = strDeleteflg	;	//削除フラグ
			colUpd[7] = strUpddatetime	;	//更新日時
			colUpd[8] = strUpduser		;	//更新者
			colUpd[9] = strUpdmachine	;	//更新端末
			colUpd[10] = strCategoryId	;
			//カテゴリーのDataアクセスクラスのオブジェクトを生成する
			Categories dao = new Categories();

			try {
				//insert処理
				List<IBean> lstBn = dao.update(colUpd);
				//リストからBeanを取り出す
				bn = (CategoriesBn)lstBn.get(0);
				//セッションへセットする。
				hs.setAttribute("categories", bn);

			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}


		}else if(request.getParameter("call_softdelete") != null){

			//更新処理（softdelete）
			//論理削除の場合、delateflgがいかなる値であっても
			//データアクセス側で'true'がセットされる。
			//リクエストパラメータを配列にセットする
			String[] colSoftDel = new String[11];
			colSoftDel[0] = strCategoryName	;
			colSoftDel[1] = strDescription	;
			colSoftDel[2] = strLinkName		;
			colSoftDel[3] = strLinkUrl		;
			colSoftDel[4] = strRegdate		;
			colSoftDel[5] = strDuedate		;
			colSoftDel[6] = strDeleteflg	;	//削除フラグ
			colSoftDel[7] = strUpddatetime	;	//更新日時
			colSoftDel[8] = strUpduser		;	//更新者
			colSoftDel[9] = strUpdmachine	;	//更新端末
			colSoftDel[10] = strCategoryId	;
			//カテゴリーのDataアクセスクラスのオブジェクトを生成する
			Categories dao = new Categories();

			try {
				//insert処理
				List<IBean> lstBn = dao.softdelete(colSoftDel);
				//リストからBeanを取り出す
				bn = (CategoriesBn)lstBn.get(0);
				//セッションへセットする。
				hs.setAttribute("categories", bn);

			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_delete") != null){

			//更新処理（delete）
			//リクエストパラメータを配列にセットする
			String[] colDel = new String[1];
			colDel[0] = strCategoryId;
			Categories dao = new Categories();

			try {
				//Select処理
				List<IBean> lstBn = dao.delete(colDel);
				//リストからBeanを取り出す
				 bn = (CategoriesBn)lstBn.get(0);
				 //セッションへセットする。
				 hs.setAttribute("categories", bn);

			} catch (InputErrorException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}


		}

		//*****画面遷移*****
		response.sendRedirect(strRefUrl);

	}

}
