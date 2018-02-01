/**
 *
 */
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

/**
 * カテゴリテーブル用のデータアクセスクラス
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 */
public class Categories extends PgDao {

	@Override
	public List<IBean> select(String[] keys) throws InputErrorException, SQLException {

		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		ResultSet 			rs 		= null;

		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Select文を構成する
			String sql = "select " 			+
					"categoryid 		"	+		//1
					", categoryname 	" 	+		//2
					", description 		" 	+		//3
					", linkname 		" 	+		//4
					", linkurl 			" 	+		//5
					", regdate 			" 	+		//6
					", duedate 			" 	+		//7
					", deleteflg 		"	+		//8
					", insdatetime 		"	+		//9
					", insuser 			" 	+		//10
					", insmachine 		" 	+		//11
					", upddatetime 		" 	+		//12
					", upduser 			" 	+		//13
					", updmachine 		" 	+		//14
					"from categories	"   +
					"where categoryid 	=? ";		//key[0]

			//接続を設定する
			conn = this.openConnect();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, keys);
			//Select文の実行
			rs = stmt.executeQuery();

			//Beanオブジェクトを作成する
			CategoriesBn bn = new CategoriesBn();

			//Select実行の結果をCategoriesBeanにセットする
			while(rs.next()) {
				bn.setCategoryid(rs.getString(1));
				bn.setCategoryname(rs.getString(2));
				bn.setDescription(rs.getString(3));
				bn.setLinkname(rs.getString(4));
				bn.setLinkurl(rs.getString(5));
				bn.setRegdate(bn.toString(java.sql.Types.DATE,rs.getString(6)));
				bn.setDuedate(bn.toString(java.sql.Types.DATE,rs.getString(7)));
				bn.setDeleteflg(rs.getString(8));
				bn.setInsdatetime(rs.getString(9));
				bn.setInsuser(rs.getString(10));
				bn.setInsmachine(rs.getString(11));
				bn.setUpddatetime(rs.getString(12));
				bn.setUpduser(rs.getString(13));
				bn.setUpdmachine(rs.getString(14));
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
	public List<IBean> update(String[] columnValues) throws  SQLException,InputErrorException{

		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Update文を構成する
			String sql = "update categories " 		+
					"set "							+
					" categoryname 		= ? " 		+		//0
					", description 		= ? " 		+		//1
					", linkname 		= ?	" 		+		//2
					", linkurl 			= ? " 		+		//3
					", regdate 			= ? " 		+		//4
					", duedate 			= ? " 		+		//5
					", deleteflg 		= ? "		+		//6
					", upddatetime 		= ? " 		+		//7
					", upduser 			= ? " 		+		//8
					", updmachine 		= ? " 		+		//9
					" where categoryid 	= ?";				//10

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//更新日時をセットする
			columnValues[7] = this.getSqlTimespanStringNow();
			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);
			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成する。
			CategoriesBn bn = new CategoriesBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。
			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする

				bn.setCategoryname(columnValues[0]);
				bn.setDescription(columnValues[1]);
				bn.setLinkname(columnValues[2]);
				bn.setLinkurl(columnValues[3]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[4]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[5]));
				bn.setDeleteflg(columnValues[6]);
				bn.setUpddatetime(columnValues[7]);
				bn.setUpduser(columnValues[8]);
				bn.setUpdmachine(columnValues[9]);
				bn.setCategoryid(columnValues[10]);
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
	public List<IBean> insert(String[] columnValues) throws SQLException,InputErrorException{

		//変数を宣言する
		PreparedStatement stmt = null;
		Connection conn = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();

		try {
			String sql = "insert into categories " 		+
					"( categoryid 	"		+
					", categoryname " 		+
					", description 	" 		+
					", linkname 	" 		+
					", linkurl 		" 		+
					", regdate 		" 		+
					", duedate 		" 		+
					", deleteflg 	"		+
					", insdatetime 	"		+
					", insuser  	"		+
					", insmachine 	" 		+
					") values " 			+
					"(?,?,?,?,?,?,?,?,?,?,?) " ;

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//登録日をセットする
			columnValues[8] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);

			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成sる。
			CategoriesBn bn = new CategoriesBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。

			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする
				bn.setCategoryid(columnValues[0]);
				bn.setCategoryname(columnValues[1]);
				bn.setDescription(columnValues[2]);
				bn.setLinkname(columnValues[3]);
				bn.setLinkurl(columnValues[4]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[5]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[6]));
				bn.setDeleteflg(columnValues[7]);
				bn.setInsdatetime(columnValues[8]);
				bn.setInsuser(columnValues[9]);
				bn.setInsmachine(columnValues[10]);
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
	public List<IBean> delete(String[] keys) throws SQLException,InputErrorException{

		//変数を宣言する
		PreparedStatement stmt = null;
		Connection conn = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			String sql = "delete from categories " 		+
						"where categoryid= ?";

			conn = this.openConnect();

			stmt = this.setSqlStmtParams(conn, sql, keys);

			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成sる。
			CategoriesBn bn = new CategoriesBn();

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
			//Update文を構成する
			String sql = "update categories " 		+
					"set "							+
					" categoryname 		= ? " 		+		//0
					", description 		= ? " 		+		//1
					", linkname 		= ?	" 		+		//2
					", linkurl 			= ? " 		+		//3
					", regdate 			= ? " 		+		//4
					", duedate 			= ? " 		+		//5
					", deleteflg 		= ? "		+		//6
					", upddatetime 		= ? " 		+		//7
					", upduser 			= ? " 		+		//8
					", updmachine 		= ? " 		+		//9
					" where categoryid 	= ?";				//10

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//削除フラグを設定する
			columnValues[6] = "true";

			//更新日時をセットする
			columnValues[7] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);

			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成する。
			CategoriesBn bn = new CategoriesBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。
			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする
				bn.setCategoryname(columnValues[0]);
				bn.setDescription(columnValues[1]);
				bn.setLinkname(columnValues[2]);
				bn.setLinkurl(columnValues[3]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE, columnValues[4]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE, columnValues[5]));
				bn.setDeleteflg(columnValues[6]);
				bn.setUpddatetime(columnValues[7]);
				bn.setUpduser(columnValues[8]);
				bn.setUpdmachine(columnValues[9]);
				bn.setCategoryid(columnValues[10]);
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

	public static void main(String[] args) {

		Categories ct = new Categories();
		/*
		//insert 確認
		String[] colIns = {
				"3"
				,"インサートテスト要です。"
				,"説明テスト　更新テスト"
				,"テストリンク先"
				,"http://"
				,"2017-08-28"
				,"2017/09/28"
				,"true"
				,""
				,"shimizu"
				,"localhost"

			};

		try {
			List<IBean> categoBn = ct.insert(colIns);
			//１が戻ればOK
			System.out.println(categoBn.size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		*/


		//String insDate = ct.getTimeStamp("yyyy-MM-dd hh:mm:ss");


		//update 確認
		String[] colvals = {
				"テスト要です。"
				,"説明テスト　更新テスト"
				,"テストリンク先"
				,"http://"
				,"2017/08/28"
				,"2017/10/28"
				,"true"
				,""
				,"shimizu"
				,"localhost"
				,"1"
			};

		try {
			List<IBean> categoBn = ct.update(colvals);
			//１が戻ればOK
			System.out.println(categoBn.size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



		/*
		//select 確認
		String[] colSelect = {
				"1"
			};

		try {
			List<IBean> categoBn = ct.select(colSelect);
			//１が戻ればOK
			System.out.println(categoBn.size());
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
		String[] colSelect = {
				"2"
			};

		try {
			List<IBean> categoBn = ct.delete(colSelect);
			//１が戻ればOK
			System.out.println(categoBn.size());
		} catch (InputErrorException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		*/


	}


}
