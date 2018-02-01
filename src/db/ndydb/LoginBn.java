package db.ndydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.IBean;
import db.InputErrorException;
/**
 * ログオンユーザー保持情報クラス
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 *
 */
public class LoginBn extends Users{

	/**
	 * ログオン認証
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return true ログイン成功 false ログイン失敗を判定値を戻す
	 * @throws InputErrorException
	 * @throws SQLException
	 */
	public boolean authCertification(String userName,String password) throws InputErrorException, SQLException {

		//変数を宣言する
		Connection 			conn 	= null;
		PreparedStatement 	stmt 	= null;
		ResultSet 			rs 		= null;


		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();



		//セキュリティ対応 サニタイジング
		String[] keys = new String[2];
		keys[0] =  this.SQLEscape(userName);
		keys[1] =  this.SQLEscape(password);
		try {

			//Select文を構成する
			String sql = "select 	" 	+
					" userid 	" 		+		//1
					"from users		"   +
					"where username = ? "+		//[0]
					" and password = ? ";		//[1]

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

			}
			//CategoriesBeanをリストへ追加する
			lstBn.add(bn);

			return false;


		} catch (SQLException e) {

			throw e ;

		} finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}


	}
	/**
	* 文字列の置換を行う
	*
	* @param input 処理の対象の文字列
	* @param pattern 置換前の文字列
	* @param replacement 置換後の文字列
	* @return 置換処理後の文字列
	*/
	public String substitute(String input, String pattern, String replacement) {
		// 置換対象文字列が存在する場所を取得
		int index = input.indexOf(pattern);
		// 置換対象文字列が存在しなければ終了
		if(index == -1) {
			return input;
		}
		// 処理を行うための StringBuffer
		StringBuffer buffer = new StringBuffer();
		buffer.append(input.substring(0, index) + replacement);
		if(index + pattern.length() < input.length()) {
			// 残りの文字列を再帰的に置換
			String rest = input.substring(index + pattern.length(), input.length());
			buffer.append(substitute(rest, pattern, replacement));
		}
		return buffer.toString();
	}


	/**
	 * SQL文出力用に次の置換を行う
	 * ' -> ''
	 * \ -> \\
	 *
	 * @param input 置換対象の文字列
	 * @return 置換処理後の文字列
	 */
	public String SQLEscape(String input) {
		input = substitute(input, "'", "''");
		input = substitute(input, "\\", "\\\\");
		return input;
	}

	private String strAppPath = "";


	/**
	 * このアプリケーションのパスを取得します。
	 * グリッドの画像判定、アプリケーション内のフォルダへアクセスする場合、
	 * アプリケーションルートが必要になる場合があります。
	 * @return アプリケーションルートを文字列で戻す
	 */
	public String getAppPath(){
		return this.strAppPath;
	}

	/**
	 * アプリケーションパスを設定します。
	 * 通常ログオン成功時でセットします。アプリ内のフォルダへアクセスする際、
	 * アプリケーションパスが必要になります。
	 * @param strAppPath アプリケーションパスy
	 */
	public void setAppPath(String strAppPath){
		this.strAppPath = strAppPath;
	}

	//デフォルトコンストラクタ
	public LoginBn(){

	}



}
