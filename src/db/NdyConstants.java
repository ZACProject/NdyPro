/**
 *
 */
package db;

/**
 * システムに全般に対する定義を記述します。
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 *
 */
public class NdyConstants {

	/**
	 * Ndyのカレントバージョン番号
	 */
	public static final String CURRENT_VERSION = "0.0.0.4";

	/**
	 * PostgreSql接続定義定数
	 */

	/**
	 * Postgresqlドライバー接続文字列
	 */
	public static final String PG_DRIVERNAME 	= "org.postgresql.Driver";
	/**
	 * サーバー IPアドレス 今回はデモ用なのでローカルホストで。
	 */
	public static final String PG_SERVER		= "localhost";

	/**
	 * Postgresqlで使用するデータベース名 Ndy
	 */
	public static final String PG_DBNAME		= "Ndy";

	/**
	 * Jdbc接続文字列
	 */
	public static final String PG_URL 			= "jdbc:postgresql://" + PG_SERVER + ":5432/" + PG_DBNAME;

	/**
	 * 接続ユーザー情報 root
	 */
	public static final String PG_USER 			= "root";

	/**
	 * 接続ユーザー パスワード
	 */
	public static final String PG_PASSWORD   	= "root";





}
