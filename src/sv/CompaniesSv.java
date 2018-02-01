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
import db.ndydb.Companies;
import db.ndydb.CompaniesBn;

@WebServlet("/CompaniesSv")

public class CompaniesSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompaniesSv() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//コンテンツタイプとエンコードを指定する
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Companies.jsp";
		//Errorが起こった時の遷移先
		String strErrorUrl = "./Error.jsp";
		//変数の宣言
		String strCompanyId  =    "";//カンパニID

		//Companies.jspからのリクエストパラメータのセット
		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("companyid") != null) {
			strCompanyId = request.getParameter("companyid");
		}
		//変数を宣言する（出力用のBean)
		CompaniesBn bn = null;
		//呼出処理(select)
		//リクエストパラメータを配列にセットする
		String[] colSelect = new String[1];
		colSelect[0]= strCompanyId;
		Companies dao = new Companies();

		try {
			//Select 処理
			List<IBean> lstBn = dao.select(colSelect);
			//リストからBeanを取り出す
			bn=(CompaniesBn)lstBn.get(0);
			//セッションへセットする
			hs.setAttribute("companies", bn);

		}catch(InputErrorException e) {
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		}catch(SQLException e) {
			e.printStackTrace();
			hs.setAttribute("ErrorMsg", e.getMessage());
			strRefUrl = strErrorUrl;
		}
		//画面遷移
		response.sendRedirect(strRefUrl);


	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//doGet(reuquest,response)
		//コンテンツタイプとエンコードを指定する
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//セッションを宣言する
		HttpSession hs = request.getSession();
		//遷移先
		String strRefUrl = "./Companies.jsp";
		//Errorが起こった時の遷移先
		String strErrorUrl = "./Error.jsp";
		//パラメータ受信部
		//変数の宣言
		String strCompanyId = "";				//会社ID
		String strCategorynamejp = "";		//会社名JP
		String strCategorynamecn = "";		//会社名CN
		String strCategorynameen = "";		//会社名EN
		String strAddr1jp = "";				//住所1JP
		String strAddr2jp = "";				//住所2JP
		String strAddr3jp = "";				//住所3JP
		String strZipjp = "";					//郵便番号Jp
		String strAddr1cn = "";				//住所名1CN
		String strAddr2cn = "";				//住所名2CN
		String strAddr3cn = "";				//住所名3CN
		String strZipcn = "";					//郵便番号CN
		String strAddr1en = "";				//住所1EN
		String strAddr2en = "";				//住所2EN
		String strAddr3en = "";				//住所3EN
		String strZipen = "";					//郵便番号EN
		String strAccess1jp = "";				//アクセス1JP
		String strAccess2jp = "";				//アクセス2JP
		String strAccess1cn = "";				//アクセス1CN
		String strAccess2cn = "";				//アクセス2CN
		String strAccess1en = "";				//アクセス1EN
		String strAccess2en = "";				//アクセス2EN
		String strTel = "";					//TEL(代表番号)
		String strFax = "";					//		Fax
		String strEmail = "";					//メール
		String strHomeurl = "";				//ホームページアドレス
		String strLinkname1 = "";				//室内画像名1
		String strLinkurl1 = "";				//室内画像リンク先1
		String strLinkname2 = "";				//室内画像名2
		String strLinkurl2 = "";				//室内画像リンク先2
		String strLinkname3 = "";				//室内画像名3
		String strLinkurl3= "";				//室内画像リンク先3
		String strDispnum = "";				//表示数
		String strRegdate = "";				//開始日
		String strDuedate = "";				//終了日
		String strDeleteflg = "";				//削除フラグ
		String strInsdatetime = "";			//登録日時
		String strInsuser = "";				//登録ユーザー名
		String strInsmachine = "";			//登録端末
		String strUpddatetime = "";			//更新日時
		String strUpduser = "";				//更新ユーザー
		String strUpdmachine = "";			//更新端末
		//Companies.jspからのリクエスパラメータのセット
		//パラメータが送られている場合、変数にセットする
		if(request.getParameter("companyid") != null) {
			strCompanyId = request.getParameter("companyid");
		}
		if(request.getParameter("categorynamejp") != null) {
			strCategorynamejp = request.getParameter("categorynamejp");
		}
		if(request.getParameter("categorynamecn") != null) {
			strCategorynamecn = request.getParameter("categorynamecn");
		}
		if(request.getParameter("categorynameen") != null) {
			strCategorynameen = request.getParameter("categorynameen");
		}
		if(request.getParameter("addr1jp") != null) {
			strAddr1jp = request.getParameter("addr1jp");
		}
		if(request.getParameter("addr2jp") != null) {
			strAddr2jp = request.getParameter("addr2jp");
		}
		if(request.getParameter("addr3jp") != null) {
			strAddr3jp = request.getParameter("addr3jp");
		}
		if(request.getParameter("zipjp") != null) {
			strZipjp = request.getParameter("zipjp");
		}
		if(request.getParameter("addr1cn") != null) {
			strAddr1cn = request.getParameter("addr1cn");
		}
		if(request.getParameter("addr2cn") != null) {
			strAddr2cn = request.getParameter("addr2cn");
		}
		if(request.getParameter("addr3cn") != null) {
			strAddr3cn = request.getParameter("addr3cn");
		}
		if(request.getParameter("zipcn") != null) {
			strZipcn = request.getParameter("zipcn");
		}
		if(request.getParameter("addr1en") != null) {
			strAddr1en = request.getParameter("addr1en");
		}
		if(request.getParameter("addr2en") != null) {
			strAddr2en = request.getParameter("addr2en");
		}
		if(request.getParameter("addr3en") != null) {
			strAddr3en = request.getParameter("addr3en");
		}
		if(request.getParameter("zipen") != null) {
			strZipen = request.getParameter("zipen");
		}
		if(request.getParameter("access1jp") != null) {
			strAccess1jp = request.getParameter("access1jp");
		}
		if(request.getParameter("access2jp") != null) {
			strAccess2jp = request.getParameter("access2jp");
		}
		if(request.getParameter("access1cn") != null) {
			strAccess1cn = request.getParameter("access1cn");
		}
		if(request.getParameter("access2cn") != null) {
			strAccess2cn = request.getParameter("access2cn");
		}
		if(request.getParameter("access1en") != null) {
			strAccess1en = request.getParameter("access1en");
		}
		if(request.getParameter("access2en") != null) {
			strAccess2en = request.getParameter("access2en");
		}
		if(request.getParameter("tel") != null) {
			strTel = request.getParameter("tel");
		}
		if(request.getParameter("fax") != null) {
			strFax = request.getParameter("fax");
		}
		if(request.getParameter("email") != null) {
			strEmail = request.getParameter("email");
		}
		if(request.getParameter("homeurl") != null) {
			strHomeurl = request.getParameter("homeurl");
		}
		if(request.getParameter("linkname1") != null) {
			strLinkname1 = request.getParameter("linkname1");
		}
		if(request.getParameter("linkurl1") != null) {
			strLinkurl1 = request.getParameter("linkurl1");
		}
		if(request.getParameter("linkname2") != null) {
			strLinkname2 = request.getParameter("linkname2");
		}
		if(request.getParameter("linkurl2") != null) {
			strLinkurl2 = request.getParameter("linkurl2");
		}
		if(request.getParameter("linkname3") != null) {
			strLinkname3 = request.getParameter("linkname3");
		}
		if(request.getParameter("linkurl3") != null) {
			strLinkurl3 = request.getParameter("linkurl3");
		}
		if(request.getParameter("dispnum") != null) {
			strDispnum = request.getParameter("dispnum");
		}
		if(request.getParameter("regdate") != null) {
			strRegdate = request.getParameter("regdate");
		}
		if(request.getParameter("duedate") != null) {
			strDuedate = request.getParameter("duedate");
		}
		if(request.getParameter("deleteflg") != null) {
			strDeleteflg = request.getParameter("deleteflg");
		}
		if(request.getParameter("insdatetime") != null) {
			strInsdatetime = request.getParameter("insdatetime");
		}
		if(request.getParameter("insuser") != null) {
			strInsuser = request.getParameter("insuser");
		}
		if(request.getParameter("insmachine") != null) {
			strInsmachine = request.getParameter("insmachine");
		}
		if(request.getParameter("upddatetime") != null) {
			strUpddatetime = request.getParameter("upddatetime");
		}
		if(request.getParameter("upduser") != null) {
			strUpduser = request.getParameter("upduser");
		}
		if(request.getParameter("updmachine") != null) {
			strUpdmachine = request.getParameter("updmachine");
		}
		//変数を宣言する（出力用のBean）
		CompaniesBn bn = null;
		//カテゴリ登録のどのボタンが押されているかを判定して
		//処理を実行する
		if(request.getParameter("call_select") != null) {
			//呼出処理（select）
			//リクエストパラメータを配列にセットする
			String[] colSelect = new String[1];
			colSelect[0] = strCompanyId;
			Companies dao = new Companies();

			try {
				//Select処理
				List<IBean> lstBn = dao.select(colSelect);
				//リストからBeanを取り出す
				bn=(CompaniesBn)lstBn.get(0);
				//セッションへセットする。
				 hs.setAttribute("companies", bn);
			}catch(InputErrorException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}catch(SQLException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_insert") !=null) {
			//登録処理（insert）
			//リクエストパラメータを配列にセットする
			String[] colIns = new String[39];
			colIns[0] = strCompanyId;
			colIns[1] = strCategorynamejp;
			colIns[2] = strCategorynamecn;
			colIns[3] = strCategorynameen;
			colIns[4] = strAddr1jp;
			colIns[5] = strAddr2jp;
			colIns[6] = strAddr3jp;
			colIns[7] = strZipjp;
			colIns[8] = strAddr1cn;
			colIns[9] = strAddr2cn;
			colIns[10] = strAddr3cn;
			colIns[11] = strZipcn;
			colIns[12] = strAddr1en;
			colIns[13] = strAddr2en;
			colIns[14] = strAddr3en;
			colIns[15] = strZipen;
			colIns[16] = strAccess1jp;
			colIns[17] = strAccess2jp;
			colIns[18] = strAccess1cn;
			colIns[19] = strAccess2cn;
			colIns[20] = strAccess1en;
			colIns[21] = strAccess2en;
			colIns[22] = strTel;
			colIns[23] = strFax;
			colIns[24] = strEmail;
			colIns[25] = strHomeurl;
			colIns[26] = strLinkname1;
			colIns[27] = strLinkurl1;
			colIns[28] = strLinkname2;
			colIns[29] = strLinkurl2;
			colIns[30] = strLinkname3;
			colIns[31] = strLinkurl3;
			colIns[32] = strDispnum;
			colIns[33] = strRegdate;
			colIns[34] = strDuedate;
			colIns[35] = strDeleteflg;		//削除フラグ
			colIns[36] = strInsdatetime	;	//登録日時
			colIns[37] = strInsuser		;	//登録者
			colIns[38] = strInsmachine	;	//登録端末

			//カテゴリーのDataアクセスクラスのオブジェクトを生成する
			Companies dao = new Companies();

			try {
				//insert処理
				List<IBean> lstBn = dao.insert(colIns);
				//リストからBeanを取り出す
				bn = (CompaniesBn) lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("companies", bn);

			}catch(InputErrorException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;

			}catch(SQLException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}
		}else if(request.getParameter("call_update") != null) {
			//更新処理(update)
			//リクエストパラメータを配列にセットする
			String[] colUpd = new String[39];
			colUpd[0] = strCategorynamejp;
			System.out.println("0:"+strCategorynamejp);
			colUpd[1] = strCategorynamecn;
			System.out.println("1:"+strCategorynamecn);
			colUpd[2] = strCategorynameen;
			System.out.println("2:"+strCategorynameen);
			colUpd[3] = strAddr1jp;
			System.out.println("3:"+strAddr1jp);
			colUpd[4] = strAddr2jp;
			System.out.println("4:"+strAddr2jp);
			colUpd[5] = strAddr3jp;
			System.out.println("5:"+strAddr3jp);
			colUpd[6] = strZipjp;
			System.out.println("6:"+strZipjp);
			colUpd[7] = strAddr1cn;
			System.out.println("7:"+strAddr1cn);
			colUpd[8] = strAddr2cn;
			System.out.println("8:"+strAddr2cn);
			colUpd[9] = strAddr3cn;
			System.out.println("9:"+strAddr3cn);
			colUpd[10] = strZipcn;
			System.out.println("10:"+strZipcn);
			colUpd[11] = strAddr1en;
			System.out.println("11:"+strAddr1en);
			colUpd[12] = strAddr2en;
			System.out.println("12:"+strAddr2en);
			colUpd[13] = strAddr3en;
			System.out.println("13:"+strAddr3en);
			colUpd[14] = strZipen;
			System.out.println("14:"+strZipen);
			colUpd[15] = strAccess1jp;
			System.out.println("15:"+strAccess1jp);
			colUpd[16] = strAccess2jp;
			System.out.println("16:"+strAccess2jp);
			colUpd[17] = strAccess1cn;
			System.out.println("17:"+strAccess1cn);
			colUpd[18] = strAccess2cn;
			System.out.println("18:"+strAccess2cn);
			colUpd[19] = strAccess1en;
			System.out.println("19:"+strAccess1en);
			colUpd[20] = strAccess2en;
			System.out.println("20:"+strAccess2en);
			colUpd[21] = strTel;
			System.out.println("21:"+strTel);
			colUpd[22] = strFax;
			System.out.println("22:"+strFax);
			colUpd[23] = strEmail;
			System.out.println("23:"+strEmail);
			colUpd[24] = strHomeurl;
			System.out.println("24:"+strHomeurl);
			colUpd[25] = strLinkname1;
			System.out.println("25:"+strLinkname1);
			colUpd[26] = strLinkurl1;
			System.out.println("26:"+strLinkurl1);
			colUpd[27] = strLinkname2;
			System.out.println("27:"+strLinkname2);
			colUpd[28] = strLinkurl2;
			System.out.println("28:"+strLinkurl2);
			colUpd[29] = strLinkname3;
			System.out.println("29:"+strLinkname3);
			colUpd[30] = strLinkurl3;
			System.out.println("30:"+strLinkurl3);
			colUpd[31] = strDispnum;
			System.out.println("31:"+strDispnum);
			colUpd[32] = strRegdate;
			System.out.println("32:"+strRegdate);
			colUpd[33] = strDuedate;
			System.out.println("33:"+strDuedate);
			colUpd[34] = strDeleteflg;
			System.out.println("34:"+strDeleteflg);//削除フラグ
			colUpd[35] = strUpddatetime	;
			System.out.println("35:"+strUpddatetime);//更新日時
			colUpd[36] = strUpduser		;
			System.out.println("36:"+strUpduser);//更新者
			colUpd[37] = strUpdmachine	;
			System.out.println("37:"+strUpdmachine);//更新端末
			colUpd[38] = strCompanyId;
			System.out.println("38:"+strCompanyId);
			//カンパニのdateアクセスのオブジェクトを生成する
			Companies dao = new Companies();


			try {
				//insert処理
				List<IBean> lstBn = dao.update(colUpd);
				//リストからBeanを取り出す
				bn = (CompaniesBn) lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("companies", bn);

			}catch(InputErrorException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;

			}catch(SQLException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}

		}else if(request.getParameter("call_softdelete") != null){

			//更新処理（softdelete）
			//論理削除の場合、delateflgがいかなる値であっても
			//データアクセス側で'true'がセットされる。
			//リクエストパラメータを配列にセットする
			String[] colSoftDel = new String[39];
			colSoftDel[0] = strCategorynamejp;
			colSoftDel[1] = strCategorynamecn;
			colSoftDel[2] = strCategorynameen;
			colSoftDel[3] = strAddr1jp;
			colSoftDel[4] = strAddr2jp;
			colSoftDel[5] = strAddr3jp;
			colSoftDel[6] = strZipjp;
			colSoftDel[7] = strAddr1cn;
			colSoftDel[8] = strAddr2cn;
			colSoftDel[9] = strAddr3cn;
			colSoftDel[10] = strZipcn;
			colSoftDel[11] = strAddr1en;
			colSoftDel[12] = strAddr2en;
			colSoftDel[13] = strAddr3en;
			colSoftDel[14] = strZipen;
			colSoftDel[15] = strAccess1jp;
			colSoftDel[16] = strAccess2jp;
			colSoftDel[17] = strAccess1cn;
			colSoftDel[18] = strAccess2cn;
			colSoftDel[19] = strAccess1en;
			colSoftDel[20] = strAccess2en;
			colSoftDel[21] = strTel;
			colSoftDel[22] = strFax;
			colSoftDel[23] = strEmail;
			colSoftDel[24] = strHomeurl;
			colSoftDel[25] = strLinkname1;
			colSoftDel[26] = strLinkurl1;
			colSoftDel[27] = strLinkname2;
			colSoftDel[28] = strLinkurl2;
			colSoftDel[29] = strLinkname3;
			colSoftDel[30] = strLinkurl3;
			colSoftDel[31] = strDispnum;
			colSoftDel[32] = strRegdate;
			colSoftDel[33] = strDuedate;
			colSoftDel[34] = strDeleteflg;		//削除フラグ
			colSoftDel[35] = strUpddatetime	;	//更新日時
			colSoftDel[36] = strUpduser		;	//更新者
			colSoftDel[37] = strUpdmachine	;	//更新端末
			colSoftDel[38] = strCompanyId;
			//カテゴリーのDataアクセスクラスのオブジェクトを生成する
			Companies dao = new Companies();


			try {
				//insert処理
				List<IBean> lstBn = dao.softdelete(colSoftDel);
				//リストからBeanを取り出す
				bn = (CompaniesBn) lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("companies", bn);

			}catch(InputErrorException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;

			}catch(SQLException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}

		}else if(request.getParameter("call_delete") != null){
			//更新処理（delete）
			//リクエストパラメータを配列にセットする
			String[] colDel = new String[1];
			colDel[0] = strCompanyId;
			Companies dao = new Companies();
			try {
				//insert処理
				List<IBean> lstBn = dao.delete(colDel);
				//リストからBeanを取り出す
				bn = (CompaniesBn) lstBn.get(0);
				//セッションへセットする
				hs.setAttribute("companies", bn);

			}catch(InputErrorException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;

			}catch(SQLException e) {
				e.printStackTrace();
				hs.setAttribute("ErrorMsg", e.getMessage());
				strRefUrl = strErrorUrl;
			}

		}
		//画面遷移
		response.sendRedirect(strRefUrl);


}

}
