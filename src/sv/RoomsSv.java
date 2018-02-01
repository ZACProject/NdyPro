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
import db.ndydb.Rooms;
import db.ndydb.RoomsBn;

/**
 * Servlet implementation class RoomsSv
 */
@WebServlet("/RoomsSv")
public class RoomsSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomsSv() {
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
		String strRefUrl = "./Rooms.jsp";
		//Error が起こった時の遷移先
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//
		//変数の宣言
		String strRoomId = ""; 	//ルームID
		String strCompanyid = "";	//カンパニーID

		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("roomid") != null && request.getParameter("companyid") !=null ) {
			strRoomId = request.getParameter("roomid");
			strCompanyid = request.getParameter("companyid");
		}


		//****database処理部****//

		//変数の宣言（出力用のBean）
		RoomsBn bn = null;

		//呼出処理（select）
		//リクエストパラメータを配列にセットする
		String[] colSelect = new String[2];
		colSelect[0] = strRoomId;
		colSelect[1] = strCompanyid;
		Rooms dao = new Rooms();
		try {
			//select処理
			List<IBean> lstBn = dao.select(colSelect);
			//リストからBeanを取り出す
			bn = (RoomsBn)lstBn.get(0);
			//session へセットする
			hs.setAttribute("rooms", bn);
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
		String strRefUrl = "./Rooms.jsp";
		String strErrorUrl = "./Error.jsp";

		//****パラメータ受信部****//

		//変数の宣言
		String strRoomid = "";
		String strCompanyid = "";
		String strCategorynamejp = "";
		String strCategorynamecn = "";
		String strCategorynameen = "";
		String strCategorysubnamejp = "";
		String strCategorysubnamecn = "";
		String strCategorysubnameen = "";
		String strDescripjp = "";
		String strDescripcn = "";
		String strDescripen = "";
		String strSupplementjp = "";
		String strSupplementcn = "";
		String strSupplementen = "";
		String strPricejp = "";
		String strPricecn = "";
		String strPriceen = "";
		String strRunitnamejp = "";
		String strRunitnamecn = "";
		String strRunitnameen = "";
		String strCapactiy = "";
		String strCapactiy2 = "";
		String strPunitnamejp = "";
		String strPunitnamecn = "";
		String strPunitnameen = "";
		String strLinkname1 = "";
		String strLinkurl1 = "";
		String strLinkname2 = "";
		String strLinkurl2 = "";
		String strLinkname3 = "";
		String strLinkurl3 = "";
		String strDispNum = "";
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
		if(request.getParameter("roomid") != null) {
			strRoomid = request.getParameter("roomid");
		}
		if(request.getParameter("companyid") != null) {
			strCompanyid = request.getParameter("companyid");
		}
		if(request.getParameter("categorynamejp") != null) {
			strCategorynamejp  = request.getParameter("categorynamejp");
		}
		if(request.getParameter("categorynamecn") != null) {
			strCategorynamecn  = request.getParameter("categorynamecn");
		}
		if(request.getParameter("categorynameen") != null) {
			strCategorynameen  = request.getParameter("categorynameen");
		}
		if(request.getParameter("categorysubnamejp") != null) {
			strCategorysubnamejp  = request.getParameter("categorysubnamejp");
		}
		if(request.getParameter("categorysubnamecn") != null) {
			strCategorysubnamecn  = request.getParameter("categorysubnamecn");
		}
		if(request.getParameter("categorysubnameen") != null) {
			strCategorysubnameen  = request.getParameter("categorysubnameen");
		}
		if(request.getParameter("descripjp") != null) {
			strDescripjp  = request.getParameter("descripjp");
		}
		if(request.getParameter("descripcn") != null) {
			strDescripcn  = request.getParameter("descripcn");
		}
		if(request.getParameter("descripen") != null) {
			strDescripen  = request.getParameter("descripen");
		}
		if(request.getParameter("supplementjp") != null) {
			strSupplementjp  = request.getParameter("supplementjp");
		}
		if(request.getParameter("supplementcn") != null) {
			strSupplementcn  = request.getParameter("supplementcn");
		}
		if(request.getParameter("supplementen") != null) {
			strSupplementen  = request.getParameter("supplementen");
		}
		if(request.getParameter("pricejp") != null) {
			strPricejp = request.getParameter("pricejp");
		}
		if(request.getParameter("pricecn") != null) {
			strPricecn = request.getParameter("pricecn");
		}
		if(request.getParameter("priceen") != null) {
			strPriceen = request.getParameter("priceen");
		}
		if(request.getParameter("runitnamejp") != null) {
			strRunitnamejp  = request.getParameter("runitnamejp");
		}
		if(request.getParameter("runitnamecn") != null) {
			strRunitnamecn  = request.getParameter("runitnamecn");
		}
		if(request.getParameter("runitnameen") != null) {
			strRunitnameen  = request.getParameter("runitnameen");
		}
		if(request.getParameter("capactiy") != null) {
			strCapactiy = request.getParameter("capactiy");
		}
		if(request.getParameter("capactiy2") != null) {
			strCapactiy2 = request.getParameter("capactiy2");
		}
		if(request.getParameter("punitnamejp") != null) {
			strPunitnamejp  = request.getParameter("punitnamejp");
		}
		if(request.getParameter("punitnamecn") != null) {
			strPunitnamecn  = request.getParameter("punitnamecn");
		}
		if(request.getParameter("punitnameen") != null) {
			strPunitnameen  = request.getParameter("punitnameen");
		}
		if(request.getParameter("linkname1") != null) {
			strLinkname1  = request.getParameter("linkname1");
		}
		if(request.getParameter("linkurl1") != null) {
			strLinkurl1  = request.getParameter("linkurl1");
		}
		if(request.getParameter("linkname2") != null) {
			strLinkname2  = request.getParameter("linkname2");
		}
		if(request.getParameter("linkurl2") != null) {
			strLinkurl2  = request.getParameter("linkurl2");
		}
		if(request.getParameter("linkname3") != null) {
			strLinkname3  = request.getParameter("linkname3");
		}
		if(request.getParameter("linkurl3") != null) {
			strLinkurl3  = request.getParameter("linkurl3");
		}
		if(request.getParameter("dispnum") != null) {
			strDispNum   = request.getParameter("dispnum");
		}
		if(request.getParameter("regdate") != null) {
			strRegdate   = request.getParameter("regdate");
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
		RoomsBn  bn = null ;

		//room登録のどのブタンが押されているかを判定して処理を実行する
		if(request.getParameter("call_select") != null) {
			//呼出処理  -->  select
			//リクエストパラメータを配列にセットする
			String[] colSelect = new String[2];
			colSelect[0] = strRoomid;
			colSelect[1] = strCompanyid;
			Rooms dao = new Rooms();
			try {
				//select 処理
				List<IBean> lstBn = dao.select(colSelect);
				//リストからBeanを取り出す
				bn = (RoomsBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("rooms", bn);
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
			String[] collns = new String[38];
			collns[0] = strRoomid ;
			collns[1] = strCompanyid ;
			collns[2] = strCategorynamejp ;
			collns[3] = strCategorynamecn ;
			collns[4] = strCategorynameen ;
			collns[5] = strCategorysubnamejp ;
			collns[6] = strCategorysubnamecn ;
			collns[7] = strCategorysubnameen ;
			collns[8] = strDescripjp ;
			collns[9] = strDescripcn ;
			collns[10] = strDescripen ;
			collns[11] = strSupplementjp ;
			collns[12] = strSupplementcn ;
			collns[13] = strSupplementen ;
			collns[14] = strPricejp ;
			collns[15] = strPricecn ;
			collns[16] = strPriceen ;
			collns[17] = strRunitnamejp ;
			collns[18] = strRunitnamecn ;
			collns[19] = strRunitnameen ;
			collns[20] = strCapactiy ;
			collns[21] = strCapactiy2 ;
			collns[22] = strPunitnamejp ;
			collns[23] = strPunitnamecn ;
			collns[24] = strPunitnameen ;
			collns[25] = strLinkname1 ;
			collns[26] = strLinkurl1 ;
			collns[27] = strLinkname2 ;
			collns[28] = strLinkurl2 ;
			collns[29] = strLinkname3 ;
			collns[30] = strLinkurl3 ;
			collns[31] = strDispNum ;
			collns[32] = strRegdate ;
			collns[33] = strDuedate ;
			collns[34] = strDeleteflg ;
			collns[35] = strInsdatetime	;
			collns[36] = strInsuser ;
			collns[37] = strInsmachine ;

			//roomのdataアクセスのオブジェクトを生成する
			Rooms dao = new Rooms();
			try {
				List<IBean> lstBn = dao.insert(collns);
				bn = (RoomsBn)lstBn.get(0);
				hs.setAttribute("rooms", bn);
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
			String[] colUpd = new String[38];
			colUpd[0] = strCategorynamejp ;
			colUpd[1] = strCategorynamecn ;
			colUpd[2] = strCategorynameen ;
			colUpd[3] = strCategorysubnamejp ;
			colUpd[4] = strCategorysubnamecn ;
			colUpd[5] = strCategorysubnameen ;
			colUpd[6] = strDescripjp ;
			colUpd[7] = strDescripcn ;
			colUpd[8] = strDescripen ;
			colUpd[9] = strSupplementjp ;
			colUpd[10] = strSupplementcn ;
			colUpd[11] = strSupplementen ;
			colUpd[12] = strPricejp ;
			colUpd[13] = strPricecn ;
			colUpd[14] = strPriceen ;
			colUpd[15] = strRunitnamejp ;
			colUpd[16] = strRunitnamecn ;
			colUpd[17] = strRunitnameen ;
			colUpd[18] = strCapactiy ;
			colUpd[19] = strCapactiy2 ;
			colUpd[20] = strPunitnamejp ;
			colUpd[21] = strPunitnamecn ;
			colUpd[22] = strPunitnameen ;
			colUpd[23] = strLinkname1 ;
			colUpd[24] = strLinkurl1 ;
			colUpd[25] = strLinkname2 ;
			colUpd[26] = strLinkurl2 ;
			colUpd[27] = strLinkname3 ;
			colUpd[28] = strLinkurl3 ;
			colUpd[29] = strDispNum ;
			colUpd[30] = strRegdate ;
			colUpd[31] = strDuedate ;
			colUpd[32] = strDeleteflg ;
			colUpd[33] = strUpddatetime	;
			colUpd[34] = strUpduser ;
			colUpd[35] = strUpdmachine ;
			colUpd[36] = strRoomid ;
			colUpd[37] = strCompanyid ;
			//roomのdata アクセスクラスのオブジェクトを生成する
			Rooms dao = new Rooms();

			try {
				//update処理
				List<IBean> lstBn = dao.update(colUpd);
				//リストからBeanを取り出す
				bn = (RoomsBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("rooms", bn);
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
			String[] colSoftDel = new String[38];
			colSoftDel[0] = strCategorynamejp ;
			colSoftDel[1] = strCategorynamecn ;
			colSoftDel[2] = strCategorynameen ;
			colSoftDel[3] = strCategorysubnamejp ;
			colSoftDel[4] = strCategorysubnamecn ;
			colSoftDel[5] = strCategorysubnameen ;
			colSoftDel[6] = strDescripjp ;
			colSoftDel[7] = strDescripcn ;
			colSoftDel[8] = strDescripen ;
			colSoftDel[9] = strSupplementjp ;
			colSoftDel[10] = strSupplementcn ;
			colSoftDel[11] = strSupplementen ;
			colSoftDel[12] = strPricejp ;
			colSoftDel[13] = strPricecn ;
			colSoftDel[14] = strPriceen ;
			colSoftDel[15] = strRunitnamejp ;
			colSoftDel[16] = strRunitnamecn ;
			colSoftDel[17] = strRunitnameen ;
			colSoftDel[18] = strCapactiy ;
			colSoftDel[19] = strCapactiy2 ;
			colSoftDel[20] = strPunitnamejp ;
			colSoftDel[21] = strPunitnamecn ;
			colSoftDel[22] = strPunitnameen ;
			colSoftDel[23] = strLinkname1 ;
			colSoftDel[24] = strLinkurl1 ;
			colSoftDel[25] = strLinkname2 ;
			colSoftDel[26] = strLinkurl2 ;
			colSoftDel[27] = strLinkname3 ;
			colSoftDel[28] = strLinkurl3 ;
			colSoftDel[29] = strDispNum ;
			colSoftDel[30] = strRegdate ;
			colSoftDel[31] = strDuedate ;
			colSoftDel[32] = strDeleteflg ;
			colSoftDel[33] = strUpddatetime	;
			colSoftDel[34] = strUpduser ;
			colSoftDel[35] = strUpdmachine ;
			colSoftDel[36] = strRoomid ;
			colSoftDel[37] = strCompanyid ;
			System.out.println(colSoftDel[2]);
			System.out.println(colSoftDel[3]);
			System.out.println(colSoftDel[4]);
			System.out.println(colSoftDel[5]);
			System.out.println(colSoftDel[6]);
			System.out.println(colSoftDel[7]);
			//ルームのdaoオブジェクトを生成する
			Rooms dao = new Rooms();
			try {
				//softdelete 処理
				List<IBean> lstBn = dao.softdelete(colSoftDel);
				//リストからBeanを取り出す
				bn = (RoomsBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("rooms", bn);
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
			colDel[0] = strRoomid;
			colDel[1] = strCompanyid;
			Rooms dao = new Rooms();
			try {
				//delete 処理
				List<IBean> lstBn = dao.delete(colDel);
				//リストからBeanを取り出す
				bn = (RoomsBn)lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("rooms", bn);
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
