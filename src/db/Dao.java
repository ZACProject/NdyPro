package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;


/**
 * データアクセスクラスの基本機能を実装します。
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 */
public abstract class Dao implements IDao {

	/** コネクションです。 */
	protected DataSource source;

	/**
	 * データベース接続　ユーザー名
	 */
	protected String userName;

	/**
	 * データベース接続ユーザー名を取得する
	 * @return 接続するユーザー名を戻す
	 */
	public String getUser() {
		return this.userName;
	}

	/**
	 * データベース接続ユーザー名を設定する。
	 * @param userName データベース接続ユーザー
	 */
	public void setUser(String userName) {
		this.userName = userName;
	}

	/**
	 * データベース 接続パスワード
	 */
	protected String password;

	/**
	 * データベース接続パスワードを取得する
	 * @return 接続するパスワードを戻す
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * データベース接続パスワードを設定する
	 * @param password　データベース接続パスワード
	 */
	public void setPassword(String password) {
		this.password = password;

	}

	/**
	 * データベース接続文字列
	 */
	protected String connectionString;

	/**
	 * データベース接続文字列を取得する
	 * @return データベース接続文字列を戻す
	 */
	public String getConnectionString() {
		return this.connectionString;
	}

	/**
	 * データベース接続文字列を設定する。
	 * @param connectionString データベース接続文字列
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	/**
	 * コンストラクタ 接続情報を内部でセット
	 */
	public Dao() {

		super();
		try {
			Class.forName(NdyConstants.PG_DRIVERNAME);
			this.connectionString = NdyConstants.PG_URL;
			this.userName = NdyConstants.PG_USER;
			this.password = NdyConstants.PG_PASSWORD;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * 新しいjdbcItemDaoのインスタンスを生成します。
	 * @param source
	 */
	public Dao(DataSource source) {
		this.source = source;
	}

	/**
	 * 接続のコネクションを開く
	 * @return 接続コネクションを戻す
	 * @throws SQLException
	 */
	protected Connection openConnect() throws SQLException{

		Connection cn = null;
		//データベースに接続する
		try {
			cn = DriverManager.getConnection(this.getConnectionString(), this.userName, this.password);

			return cn;

		} catch (SQLException e) {
			//例外をスローする
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * コミットします。
	 * @param conn
	 */
	protected void commit(Connection conn) {
		if (conn != null) {
			try {
			  conn.commit();
			} catch (SQLException e) {
			  e.printStackTrace();
			}
		}
	}

	/**
	 * ロールバックします。
	 * @param conn
	 */
	protected void rollback(Connection conn) {
		if (conn != null) {
			try {
			  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * コネクションをクローズします。
	 * @param conn
	 */
	protected void closeConnect(Connection conn) {
		if (conn != null) {
			try {
			  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ステートメントをクローズします。
	 * @param statement
	 */
	protected void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
			  statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 結果セットをクローズします。
	 * @param rs
	 */
	protected void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
			  rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 現在日時をSQLタイムスタンプ型で取得する
	 * 管理列に使用する。
	 * @return 現在日時をタイムスタンプ型で戻す
	 */
	protected java.sql.Timestamp getSqlTimeStampNow() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * java.sql.Timestamp型を文字列に変換して出力する。
	 * @param timestamp java.sql.Timestamp型
	 * @param format フォーマット形式文字列
	 * @return 日時形式に変換された文字列
	 */
	public String formattedTimestamp(Timestamp timestamp, String format) {
       	//フォーマットが指定されているかどうか
    	if(format == null || format.equals("")) {
    		format = "yyyy/MM/dd HH:mm:ss.SSS";
    	}
        return new SimpleDateFormat(format).format(timestamp);
    }

	/**
	 * 現在日をSQLDate型で取得する（未使用）
	 * @return 現在日をDate型で戻す
	 */
	protected java.sql.Date getSqlDateNow() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date date2 = new java.sql.Date(cal.getTimeInMillis());
		return date2;

	}

	/**
	 * java.sql.Date型を文字列に変換して出力する。
	 * @param date java.sql.date型
	 * @param format フォーマット形式文字列
	 * @return 日付形式に変換された文字列
	 */
	public String formattedDate(java.sql.Date date, String format) {
       	//フォーマットが指定されているかどうか
    	if(format == null || format.equals("")) {
    		format = "yyyy/MM/dd ";
    	}
        return new SimpleDateFormat(format).format(date);
    }

	/**
	 * 日付の文字列を受け取り、java.sql.Date型に変換して出力するヘルパーmethod
	 * @param strDate 日付の文字列
	 * @return 変換されたjava.sql.Date型のオブジェクトを戻す
	 * @throws InputErrorException 独自例外インプットエラー
	 */
	protected java.sql.Date convStringToSqlDate(String strDate) throws InputErrorException{

		//正式に変換するには以下の手続きが必要
		java.util.Date d =  new java.util.Date();
		Calendar cal = DateUtil.toCalendar(strDate);
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date d2 = new java.sql.Date(cal.getTimeInMillis());

		return d2;

	}

	/**
	 * 現在日出力する
	 * @return し
	 */
	protected String getSqlDateStringNow() {
		Date date = new Date();
		// 表示形式を指定 その１"yyyy/MM/dd HH:mm:ss"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);

	}

	/**
	 * 現在日時を出力する
	 * @return
	 */
	protected String getSqlTimespanStringNow() {
		Date date = new Date();
		// 表示形式を指定 その１"yyyy/MM/dd HH:mm:ss"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		return sdf.format(date);

	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
