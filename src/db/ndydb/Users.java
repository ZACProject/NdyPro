package db.ndydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.IBean;
import db.InputErrorException;
import db.PgDao;

public class Users extends PgDao {

	@Override
	public List<IBean> select(String[] keys) throws SQLException, InputErrorException {
		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		ResultSet 			rs 		= null;

		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Select文を構成する
			String sql = "select 	" 	+
					" linkname 	" 		+		//1
					", linkurl 		" 	+		//2
					", firstname 	" 	+		//3
					", lastname 	" 	+		//4
					", userlevel 	" 	+		//5
					", company 		"	+		//6
					", dept			"	+		//7
					", regdate 	 	" 	+ 		//8
					", duedate 		" 	+		//9
					", deleteflg 	"	+ 		//10
					", insdatetime 	"	+ 		//11
					", insuser  	"	+ 		//12
					", insmachine 	" 	+ 		//13
					", upddatetime 	" 	+		//14
					", upduser 		" 	+		//15
					", updmachine 	" 	+		//16
					"from users		"   +
					"where  userid = ? "+		//[0]
					" and username = ? "+		//[1]
					" and password = ? ";		//[2]

			//接続を設定する
			conn = this.openConnect();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, keys);
			//Select文の実行
			rs = stmt.executeQuery();

			//Beanオブジェクトを作成する
			UsersBn bn = new UsersBn();

			//Select実行の結果をCategoriesBeanにセットする
			while(rs.next()) {

				bn.setLinkname(rs.getString(1));
				bn.setLinkurl(rs.getString(2));
				bn.setFirstname(rs.getString(3));
				bn.setLastname(rs.getString(4));
				bn.setUserlevel(rs.getString(5));
				bn.setCompany(rs.getString(6));
				bn.setDept(rs.getString(7));
				bn.setRegdate(bn.toString(java.sql.Types.DATE,rs.getString(8)));
				bn.setDuedate(bn.toString(java.sql.Types.DATE,rs.getString(9)));
				bn.setDeleteflg(rs.getString(10));
				bn.setInsdatetime(rs.getString(11));
				bn.setInsuser(rs.getString(12));
				bn.setInsmachine(rs.getString(13));
				bn.setUpddatetime(rs.getString(14));
				bn.setUpduser(rs.getString(15));
				bn.setUpdmachine(rs.getString(16));
//				bn.setUserid(rs.getString(1));
//				bn.setUsername(rs.getString(2));
//				bn.setPassword(rs.getString(3));
				bn.setUserid(keys[0]);
				bn.setUsername(keys[1]);
				bn.setPassword(keys[2]);
			}
			//CategoriesBeanをリストへ追加する
			lstBn.add(bn);
			return lstBn;

		} catch (SQLException e) {

			throw e ;

		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	@Override
	public List<IBean> update(String[] columnValues) throws SQLException, InputErrorException {

		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Update文を構成する
			String sql = "update users " +
					"set "				+
					" linkname 		= ? "		+	//[0]
					", linkurl 			= ? "		+	//[1]
					", firstname 		= ? " 		+	//[2]
					", lastname 		= ? " 		+	//[3]
					", userlevel 		= ? " 		+	//[4]
					", company 			= ? "		+ 	//[5]
					", dept				= ? "		+   //[6]
					", regdate 	 		= ? "		+ 	//[7]
					", duedate 			= ? " 		+	//[8]
					", deleteflg 		= ? "		+	//[9]
					", upddatetime 		= ? " 		+	//[10]	//更新日
					", upduser 			= ? " 		+	//[11]
					", updmachine 		= ? " 		+	//[12]
					" where userid 		= ? "		+ 	//[13]
					" and username 		= ? "		+   //[14]
					" and password 		= ? " ;			//[15]

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//更新日時をセットする
			columnValues[10] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);
			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成する。
			UsersBn bn = new UsersBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。
			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする

				bn.setUserid(columnValues[13]);
				bn.setUsername(columnValues[14]);
				bn.setPassword(columnValues[15]);
				bn.setLinkname(columnValues[0]);
				bn.setLinkurl(columnValues[1]);
				bn.setFirstname(columnValues[2]);
				bn.setLastname(columnValues[3]);
				bn.setUserlevel(columnValues[4]);
				bn.setCompany(columnValues[5]);
				bn.setDept(columnValues[6]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[7]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[8]));
				bn.setDeleteflg(columnValues[9]);
				bn.setUpddatetime(columnValues[10]);
				bn.setUpduser(columnValues[11]);
				bn.setUpdmachine(columnValues[12]);
				//CategoriesBeanをリストへ追加する
				lstBn.add(bn);
			}

			//CategoryBnオブジェクトを含んだリストを戻す
			return lstBn;

		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	@Override
	public List<IBean> insert(String[] columnValues) throws SQLException, InputErrorException {
		//変数を宣言する
		PreparedStatement stmt = null;
		Connection conn = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();

		try {
			String sql = "insert into users " 		+
					"(userid 	"		+			//[0]
					", username " 		+			//[1]
					", password " 		+ 			//[2]
					", linkname 	" 	+			//[3]
					", linkurl 		" 	+			//[4]
					", firstname 	" 	+			//[5]
					", lastname 	" 	+			//[6]
					", userlevel 	" 	+			//[7]
					", company "		+			//[8]
					", dept 		"	+			//[9]
					", regdate 	 	" 	+ 			//[10]
					", duedate 		" 	+			//[11]
					", deleteflg 	"	+ 			//[12]
					", insdatetime 	"	+ 			//[13]		//登録日
					", insuser  	"	+ 			//[14]
					", insmachine 	" 	+ 			//[15]
					") values " 		+
					"(?,?,?,?,?"		+
					",?,?,?,?,?" 		+
					",?,?,?,?,?" 		+
					",?) " ;

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//登録日をセットする
			columnValues[13] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);

			//インサート文を実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成sる。
			UsersBn bn = new UsersBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。

			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする
				bn.setUserid(columnValues[0]);
				bn.setUsername(columnValues[1]);
				bn.setPassword(columnValues[2]);
				bn.setLinkname(columnValues[3]);
				bn.setLinkurl(columnValues[4]);
				bn.setFirstname(columnValues[5]);
				bn.setLastname(columnValues[6]);
				bn.setUserlevel(columnValues[7]);
				bn.setCompany(columnValues[8]);
				bn.setDept(columnValues[9]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[10]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[11]));
				bn.setDeleteflg(columnValues[12]);
				bn.setInsdatetime(columnValues[13]);
				bn.setInsuser(columnValues[14]);
				bn.setInsmachine(columnValues[15]);
				//CategoriesBeanをリストへ追加する
				lstBn.add(bn);
			}

			return lstBn;

		} catch (SQLException e) {

			throw e;

		} finally {
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}

	}

	@Override
	public List<IBean> delete(String[] keys) throws SQLException, InputErrorException {
		//変数を宣言する
		PreparedStatement stmt = null;
		Connection conn = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			String sql = "delete from users " 	+
						"where userid= ? " 		+
						"and username = ? "		+
						"and password = ? ";

			conn = this.openConnect();

			stmt = this.setSqlStmtParams(conn, sql, keys);

			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成sる。
			UsersBn bn = new UsersBn();

			//成功判定（正常に更新されているかどうか）
			//resultCntに1以上設定されている場合は削除成功として
			//リストへ空のBeanを入れて戻す
			if(resultCnt == 1) {
				//削除成功　リストへ空のBeanを入れて返す
				lstBn.add(bn);
				return lstBn;
			}else {
				//削除できなかったら空のビーンオブジェクトを戻す
				return lstBn;
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	@Override
	public List<IBean> softdelete(String[] columnValues) throws SQLException, InputErrorException {

		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//論理Update文を構成する
			String sql = "update users " +
					"set "				+
					" linkname 		= ? "		+	//[0]
					", linkurl 			= ? "		+	//[1]
					", firstname 		= ? " 		+	//[2]
					", lastname 		= ? " 		+	//[3]
					", userlevel 		= ? " 		+	//[4]
					", company 			= ? "		+ 	//[5]
					", dept				= ? "		+   //[6]
					", regdate 	 		= ? "		+ 	//[7]
					", duedate 			= ? " 		+	//[8]
					", deleteflg 		= ? "		+	//[9]	//削除フラグ
					", upddatetime 		= ? " 		+	//[10]	//更新日
					", upduser 			= ? " 		+	//[11]
					", updmachine 		= ? " 		+	//[12]
					" where userid 		= ? "		+ 	//[13]
					" and username 		= ? "		+   //[14]
					" and password 		= ? " ;			//[15]

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//削除フラグを設定する
			columnValues[9] = "true";

			//更新日時をセットする
			columnValues[10] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);
			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成する。
			UsersBn bn = new UsersBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。
			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする
				bn.setUserid(columnValues[13]);
				bn.setUsername(columnValues[14]);
				bn.setPassword(columnValues[15]);
				bn.setLinkname(columnValues[0]);
				bn.setLinkurl(columnValues[1]);
				bn.setFirstname(columnValues[2]);
				bn.setLastname(columnValues[3]);
				bn.setUserlevel(columnValues[4]);
				bn.setCompany(columnValues[5]);
				bn.setDept(columnValues[6]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[7]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[8]));
				bn.setDeleteflg(columnValues[9]);
				bn.setUpddatetime(columnValues[10]);
				bn.setUpduser(columnValues[11]);
				bn.setUpdmachine(columnValues[12]);
				//CategoriesBeanをリストへ追加する
				lstBn.add(bn);
			}

			//CategoryBnオブジェクトを含んだリストを戻す
			return lstBn;

		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	public List<IBean> selectKey(String[] login) throws SQLException, InputErrorException {
		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		ResultSet 			rs 		= null;

		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Select文を構成する
			String sql = "select 	" 	+
					" userid		" 	+		//1
					",username 		"	+
					",password		 "	+
					"from users		"   +
					"where username = ? "+		//[0]
					" and password = ? ";		//[1]

			//接続を設定する
			conn = this.openConnect();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, login);
			//Select文の実行
			rs = stmt.executeQuery();

			//Beanオブジェクトを作成する
			UsersBn bn = new UsersBn();

			//Select実行の結果をCategoriesBeanにセットする
			while(rs.next()) {
				bn.setUserid(rs.getString(1));
			}
			//CategoriesBeanをリストへ追加する
			lstBn.add(bn);
			return lstBn;

		} catch (SQLException e) {

			throw e ;

		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	public static void main(String[] args) {

		Users us = new Users();

		/*
		//insert 確認
		String[] colIns = {
				"2"
				,"shimizu"
				,"pass1"
				,"テストリンク先"
				,"http://"
				,"清水"
				,"英雄"
				,"3"
				,"富士オフィス"
				,"IT推進課"
				,"2017-08-28"
				,"2017/09/28"
				,"false"
				,""
				,"shimizu"
				,"localhost"

			};

		try {
			List<IBean> lstBn = us.insert(colIns);
			//１が戻ればOK
			System.out.println(lstBn.size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		*/


		//String insDate = ct.getTimeStamp("yyyy-MM-dd hh:mm:ss");

		/*
		//update 確認
		String[] colvals = {
				"テストリンク先1"
				,"http://1"
				,"清水1"
				,"英雄1"
				,"3"
				,"富士オフィス&"
				,"IT推進課"
				,"2017-07-28"
				,"2017/09/28"
				,"false"
				,""
				,"shimizu"
				,"localhost"
				,"1"
				,"shimizu"
				,"pass"
			};

		try {
			List<IBean> lstBn = us.update(colvals);
			//１が戻ればOK
			System.out.println(lstBn .size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		*/

		/*

		//soltdelte 確認
		String[] colvals = {
				"テストリンク先1"
				,"http://1"
				,"清水1"
				,"英雄1"
				,"3"
				,"富士オフィス&"
				,"IT推進課"
				,"2017-07-28"
				,"2017/09/28"
				,"false"
				,""
				,"shimizu"
				,"localhost"
				,"1"
				,"shimizu"
				,"pass"
			};

		try {
			List<IBean> lstBn = us.softdelete(colvals);
			//１が戻ればOK
			System.out.println(lstBn .size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		*/





		/*
		//delete 確認
		String[] coldelete = {
				"2"
				,"shimizu"
				,"pass12"
			};

		try {
			List<IBean> lstBn = us.delete(coldelete);
			//１が戻ればOK
			System.out.println(lstBn.size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		*/

		//select 確認
		String[] colSelect = {
				"2"
				,"shimizu"
				,"pass1"
			};

		try {
			List<IBean> lstBn = us.select(colSelect);
			//１が戻ればOK
			System.out.println(lstBn.size());
			System.out.println(((UsersBn)lstBn.get(0)).getUserid() );
			System.out.println(((UsersBn)lstBn.get(0)).getUsername() );
			System.out.println(((UsersBn)lstBn.get(0)).getPassword() );

		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

}
