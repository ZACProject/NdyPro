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

public class Bizcalendar extends PgDao {

	@Override
	public List<IBean> select(String[] keys) throws SQLException, InputErrorException {
		//変数を宣言する
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;

				//出力用のリストオブジェクトの作成
				List<IBean> lstBn = new ArrayList<IBean>();
				try {
					//Select文を構成する
					String sql = "select 	" 	+
							" description 	" 		+		//1
							", openflg 		" 	+		//2
							", deleteflg 	" 	+		//3
							", insdatetime 	"	+ 		//4
							", insuser  	"	+ 		//5
							", insmachine 	" 	+ 		//6
							", upddatetime 	" 	+		//7
							", upduser 		" 	+		//8
							", updmachine 	" 	+		//9
							"from bizcalendar		"   +
							"where  companyid = ? " +    //[keys0]
							"AND bizday = ?";		//keys[1]

					//接続を設定する
					conn = this.openConnect();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, keys);
					//Select文の実行
					rs = stmt.executeQuery();

					//Beanオブジェクトを作成する
					BizcalendarBn bn = new BizcalendarBn();

					//Select実行の結果をBizcalendarBeanにセットする
					while(rs.next()) {
						bn.setCompanyid(keys[0]);
						bn.setBizday(keys[1]);
						bn.setDescription(rs.getString(1));
						bn.setOpenflg(rs.getString(2));
						bn.setDeleteflg(rs.getString(3));
						bn.setInsdatetime(rs.getString(4));
						bn.setInsuser(rs.getString(5));
						bn.setInsmachine(rs.getString(6));
						bn.setUpddatetime(rs.getString(7));
						bn.setUpduser(rs.getString(8));
						bn.setUpdmachine(rs.getString(9));
					}
					//lstBnに保存する
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
				Connection conn = null;
				PreparedStatement stmt = null;
				//出力用のリストオブジェクトの作成
				List<IBean> lstBn = new ArrayList<IBean>();
				try {
					//Update文を構成する
					String sql = "update bizcalendar " +
							"set "				+
							" description 		= ? "		+	//[0]
							", openflg 			= ? "		+	//[1]
							", deleteflg 		= ? "		+	//[2]
							", upddatetime 		= ? " 		+	//[3]	//更新日
							", upduser 			= ? " 		+	//[4]
							", updmachine 		= ? " 		+	//[5]
							" where companyid 		= ? " +  //[6]
							"AND bizday		= ?";		 	//[7]

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//更新日時をセットする
					columnValues[3] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);
					stmt.executeUpdate();

					//Updateを実行する
					int resultCnt = stmt.executeUpdate();

					//カテゴリBeanのオブジェクト作成する。
					BizcalendarBn bn = new BizcalendarBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。
					if(resultCnt == 1) {
						//送信されたパラメータをBizcalendarBnにセットする

						bn.setCompanyid(columnValues[6]);
						bn.setBizday(columnValues[7]);
						bn.setDescription(columnValues[0]);
						bn.setOpenflg(columnValues[1]);
						bn.setDeleteflg(columnValues[2]);
						bn.setUpddatetime(columnValues[3]);
						bn.setUpduser(columnValues[4]);
						bn.setUpdmachine(columnValues[5]);
						//lstBnに保存する
						lstBn.add(bn);
					}

					//BizcalendarBnオブジェクトを含んだリストを戻す
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
					String sql = "insert into bizcalendar " 		+
							"( companyid 	"		+			//[0]
							", bizday " 		+			//[1]
							", description " 		+ 			//[2]
							", openflg 	" 	+			//[3]
							", deleteflg 	"	+ 			//[4]
							", insdatetime 	"	+ 			//[5]		//登録日
							", insuser  	"	+ 			//[6]
							", insmachine 	" 	+ 			//[7]
							") values " 		+
							"(?,?,?,?"		+
							",?,?,?,?)" 	;

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//登録日をセットする
					columnValues[5] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);

					//インサート文を実行する
					int resultCnt = stmt.executeUpdate();

					//カテゴリBeanのオブジェクト作成sる。
					BizcalendarBn bn = new BizcalendarBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。

					if(resultCnt == 1) {
						//送信されたパラメータをCategoriesBeanにセットする
						bn.setCompanyid(columnValues[0]);
						bn.setBizday(columnValues[1]);
						bn.setDescription(columnValues[2]);
						bn.setOpenflg(columnValues[3]);
						bn.setDeleteflg(columnValues[4]);
						bn.setInsdatetime(columnValues[5]);
						bn.setInsuser(columnValues[6]);
						bn.setInsmachine(columnValues[7]);
						//lstBn に保存する
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
			String sql = "delete from bizcalendar " 	+
						"WHERE companyid = ? " +
						"AND bizday = ?";

			conn = this.openConnect();

			stmt = this.setSqlStmtParams(conn, sql, keys);

			int resultCnt = stmt.executeUpdate();

			//BizcalendarBeanのオブジェクト作成sる。
			BizcalendarBn bn = new BizcalendarBn();

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
		}catch(SQLException e) {
			throw e;
		}finally {
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	@Override
	public List<IBean> softdelete(String[] columnValues) throws SQLException, InputErrorException {
		//変数を宣言する
				PreparedStatement stmt = null;
				Connection conn = null;
				//出力用のリストオブジェクトの作成
				List<IBean> lstBn = new ArrayList<IBean>();
				try {
					String sql = "update bizcalendar " +
							"set "				+
							" description 		= ? "		+	//[0]
							", openflg 			= ? "		+	//[1]
							", deleteflg 		= ? "		+	//[2]  //削除フラグ
							", upddatetime 		= ? " 		+	//[3]	//更新日
							", upduser 			= ? " 		+	//[4]
							", updmachine 		= ? " 		+	//[5]
							" where companyid 		= ?  " +//[6]
							"AND bizday = ?";		 	//[7]

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//削除フラグを設定する
					columnValues[2] = "true";

					//更新日時をセットする
					columnValues[3] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);
					stmt.executeUpdate();

					//Updateを実行する
					int resultCnt = stmt.executeUpdate();

					//BizcalendarBeanのオブジェクト作成する。
					BizcalendarBn bn = new BizcalendarBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。
					if(resultCnt == 1) {
						bn.setCompanyid(columnValues[6]);
						bn.setBizday(columnValues[7]);
						bn.setDescription(columnValues[0]);
						bn.setOpenflg(columnValues[1]);
						bn.setDeleteflg(columnValues[2]);
						bn.setUpddatetime(columnValues[3]);
						bn.setUpduser(columnValues[4]);
						bn.setUpdmachine(columnValues[5]);

						//lstBnに保存する
						lstBn.add(bn);
					}
					return lstBn;
				}catch (SQLException e) {
					throw e;
				} finally {
					this.closeStatement(stmt);
					this.closeConnect(conn);
				}
	}

	public static void main(String[] args){

//		// TODO 自動生成されたメソッド・スタブ
		List<IBean> lst = new ArrayList<IBean>();
		Bizcalendar bc = new Bizcalendar();



		insertTest(lst, bc);
		selectTest(lst, bc);
	   // updateTest(lst, bc);
		//deleteSoftTest(lst, bc);
		//deleteTest(lst, bc);
		//selectTest(lst, bc);

	}
	//insert 確認
	public static void insertTest(List<IBean> lst, Bizcalendar bc) {

		// String[] columns = {"006","18","本日正常営業","true","false","","wang","pc01"};
		   //String[] columns = {"07","20","予約システム","true","false","","ゆう","pc"};
		 // String[] columns = {"08","30","予約システム","true","false","","ゆう","pc"};
		// String[] columns= {"you","10","一月下旬から営業したい","true","false","","youying","pc"};
		String[] columns= {"ying","11","一月下旬から営業したい","true","false","","youying","pc"};
		// String[] columns = {"Z2010" ,"26" ,"一月下旬から営業する","true","false","游英","pc"};
		 try {
		 lst = bc.insert(columns);
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }catch(InputErrorException e) {
			 e.printStackTrace();
		 }
	}

	//update 確認
	public static void updateTest(List<IBean> lst, Bizcalendar bc) {

		//String[] colUpdate = {"本日正常営業update","true","false","","wang","pc01update","006","18"};
		// String [] colUpdate = {"予約システムup","false","false","","ゆうup","pc","07","20"};
			//String[] colUpdate = {"できれば、一月下旬で完結させたい","true","false","","youying","pc","ying","11"};
			String[] colUpdate= {"一月下旬から営業したいので、できれは、1月中旬で完成させたい","false","true","","xiaohong","pc3","you","10"};
		try {
			 lst = bc.update(colUpdate);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//select 確認
	public static void selectTest(List<IBean> lst, Bizcalendar bc) {
		String[] colSelect = {"Z2010","26"};
		try {
			 lst = bc.select(colSelect);
//			１が戻ればOK
//			System.out.println("lst.size()=" + lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("lst.size()=" + lst.size());
		System.out.print(     ((BizcalendarBn)lst.get(0)).getCompanyid()  +"\t"   );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getBizday()  +"\t"   );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getDescription()   +"\t"   );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getOpenflg()  +"\t"    );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getDeleteflg() +"\t"   ) ;
		System.out.print(     ((BizcalendarBn)lst.get(0)).getInsdatetime()  +"\t"    );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getInsuser()   +"\t"   );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getInsmachine() +"\t"     );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getUpddatetime() +"\t"     );
		System.out.print(     ((BizcalendarBn)lst.get(0)).getUpduser() +"\t"     );
		System.out.println(     ((BizcalendarBn)lst.get(0)).getUpdmachine() +"\t"     );

	}

	//deleteSoft 確認
	public static void deleteSoftTest(List<IBean> lst, Bizcalendar bc) {

		//String[] comnSoftdelete= {"本日正常営業softdelete","true","false","","wang","pc01softdelete","006","18"};
		 String [] comnSoftdelete = {"予約システムup","false","true","","ゆうup","pc","07","20"};
		try {
			 lst = bc.softdelete(comnSoftdelete);
//			１が戻ればOK
//			System.out.println("lst.size()=" + lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//delete 確認
	public static void deleteTest(List<IBean> lst, Bizcalendar bc) {
		//String[] colmnDelete = {"005","17"};
		String[] colmnDelete = {"you","10"};
		try {
			 lst = bc.delete(colmnDelete);
//			１が戻ればOK
//			System.out.println("lst.size()=" + lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}





}


//--------------------------------------------------------------------------------

//	}