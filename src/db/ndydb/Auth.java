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
public class Auth extends Users implements IBean{

	//ログオン時に取得したほうが良い情報を保持します

	private String userId;				//ユーザーID
	private String userName;			//ログイン名
	private String remoteAddr;			//リモートアドレス 管理列insmachine updmachineにセットする
	private String loginDate;			//ログオンした時間　ロギングに使用する場合を想定
	private String loginName;			//表示用　	フルネーム　LastName + FirstName
	private String linkName;			//表示用画像arc属性に使用
	private String linkUrl;			//表示用画像リンク
	private String strAppPath = "";	//このサイトのアプリケーションパス
	private boolean blLogin = false;


	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * @param remoteAddr セットする remoteAddr
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * @return loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate セットする loginDate
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName セットする loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName セットする linkName
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl セットする linkUrl
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * @return blLogin
	 */
	public boolean isLogin() {
		return blLogin;
	}

	/**
	 * @param blLogin セットする blLogin
	 */
	public void setLogin(boolean blLogin) {
		this.blLogin = blLogin;
	}

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

	/**
	 * ログオン認証
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return List<IBean> ログイン成功 isLogin = true ログイン失敗 リストのSizeがゼロ
	 * @throws InputErrorException
	 * @throws SQLException
	 */
	public List<IBean> authCertification(String userName,String password) throws InputErrorException, SQLException {

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
			String sql = 	"select 		" 		+
								"userid 	" 		+		//1
								",username 	"		+		//2
								",linkname 	"		+		//3
								",linkurl 	"		+		//4
								",firstname "		+		//5
								",lastname 	"		+		//6
							"from users		"   	+
							"where username = ? "	+		//[0]
							"and password = ? ";			//[1]

			//接続を設定する
			conn = this.openConnect();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, keys);
			//Select文の実行
			rs = stmt.executeQuery();

			//Select実行の結果をCategoriesBeanにセットする
			while(rs.next()) {
				this.setUserId(rs.getString(1));
				this.setUserName(rs.getString(2));
				this.setLoginName(rs.getString(6) + rs.getString(5));
				this.setLinkName(rs.getString(3));
				this.setLinkUrl(rs.getString(4));
				this.setLogin(true);
				lstBn.add(this);
			}
			this.setLoginDate(this.getSqlTimespanStringNow());

			return lstBn;


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




	//デフォルトコンストラクタ
	public Auth(){

	}






}
