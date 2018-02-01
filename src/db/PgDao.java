package db;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public abstract class PgDao extends Dao {

	/**
	 * パラメータ化されたSQL文よりプリペアードクエリを構成します。
	 * @param cn 接続コネクション
	 * @param sql パラメータSQL
	 * @param columnValues パラメータクエリ値
	 * @return PreparedStatementオブジェクト
	 * @throws SQLException SQLエラー例外
	 * @throws InputErrorException 値がセットできない場合に独自例外
	 */
	protected PreparedStatement setSqlStmtParams(Connection cn ,String sql,String[] columnValues) throws SQLException,InputErrorException{

		PreparedStatement ps = cn.prepareStatement(sql);

		ParameterMetaData metaData = ps.getParameterMetaData();

		for (int i= 1;i<=metaData.getParameterCount() ;i++) {
			int sqlType = metaData.getParameterType(i);
			int index = i-1;
			switch (sqlType) {
				case java.sql.Types.INTEGER :

					//空文字はnull扱いする
					if( columnValues[index].equals("")) {
						ps.setNull(i,sqlType);
					}else {
						try {
							//整数化できないときは独自例外をスローする。
							ps.setInt(i, Integer.parseInt(columnValues[index]));
						}catch(SQLException ex){
							throw ex;
						}catch(Exception e){
							InputErrorException err = new InputErrorException();
							err.setMessage("パラメータ列番、" + i + "でエラー発生。[" + columnValues[index] +"]" + "INTEGER型に問題があります。");
							throw err;
						}
					}
					break;

				case java.sql.Types.BIGINT :

					//空文字はnull扱いする
					if( columnValues[index].equals("")){
						ps.setNull(i,sqlType);
					}else {
						try {
							//整数化できないときは独自例外をスローする。
							ps.setLong(i, Long.parseLong(columnValues[index]));
						}catch(SQLException ex){
							throw ex;
						}catch(Exception e){
							InputErrorException err = new InputErrorException();
							err.setMessage("パラメータ列番、" + i + "でエラー発生。[" +  columnValues[index] +"]" + "BIGINT型に問題があります。");
							throw err;
						}
					}
					break;

				case java.sql.Types.NUMERIC :
					//空文字はnull扱いする
					if( columnValues[index].equals("")){
						ps.setNull(i,sqlType);
					}else {
						try {
							//数値化できないときは独自例外をスローする。
							ps.setDouble(i, Double.parseDouble(columnValues[index]));
						}catch(SQLException ex){
							throw ex;
						}catch(Exception e){
							InputErrorException err = new InputErrorException();
							err.setMessage("パラメータ列番、" + i + "でエラー発生。[" +  columnValues[index] +"]" + "NUMERIC型に問題があります。");
							throw err;
						}
					}
					break;

				case java.sql.Types.VARCHAR :

					//PreparedMetaDate取得では、字数の上限チェックを行うことができません
					//int colSize = metaDataget.getColumnDisplaySize(int i);
					//上記のメソッドやその他のメソッドでも文字サイスが取得できないため
					//字数チェックを行う場合は、入力時にかけていただくようお願いいたします。
					try {
						//文字化できないときは独自例外をスローする。
						ps.setString(i, columnValues[index]);
					}catch(SQLException ex){
						throw ex;
					}catch(Exception e){
						InputErrorException err = new InputErrorException();
						err.setMessage("パラメータ列番、" + i + "でエラー発生。[" +  columnValues[index] +"]" + "VARCHAR型に問題があります。");
						throw err;
					}

					break;

				case java.sql.Types.DATE :
					//空文字はnull扱いする
					if( columnValues[index].equals("")){
						ps.setNull(i,sqlType);
					}else {

						try{
							Date date =new Date();
							//入力された文字列を日付型に変換する
							date=DateUtil.toCalendar(columnValues[index]).getTime();
							ps.setDate(i, new java.sql.Date(date.getTime()));
						}catch(SQLException ex){
							throw ex;
						}catch(Exception e){
							InputErrorException err = new InputErrorException();
							err.setMessage("パラメータ列番、" + i + "でエラー発生。[" +  columnValues[index] +"]" + "DATE型に問題があります。");
							throw err;
						}
					}

					break;

				case java.sql.Types.TIMESTAMP :
					//空文字はnull扱いする
					if( columnValues[index].equals("")){
						ps.setNull(i,sqlType);
					}else {
						Calendar cal = Calendar.getInstance();
						cal=DateUtil.toCalendar(columnValues[index]);
						try{
							Timestamp ts =new Timestamp(cal.getTimeInMillis());
							ps.setTimestamp(i, ts);
						}catch(SQLException ex){
							throw ex;
						}catch(Exception e){
							InputErrorException err = new InputErrorException();
							err.setMessage("パラメータ列番、" + i + "でエラー発生。[" +  columnValues[index] +"]" + "TIMESTAMP型に問題があります。");
							throw err;
						}
					}
					break;

			}
		}
		return ps;
	}

}
