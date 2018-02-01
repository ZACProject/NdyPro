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

public class Rooms extends PgDao {

	@Override
	public List<IBean> select(String[] keys) throws SQLException, InputErrorException {
		//変数を宣言する
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			String sql = "select		" +
								" categorynamejp" + //1
								",categorynamecn" + //2
								",categorynameen" + //3
								",categorysubnamejp" + //4
								",categorysubnamecn" + //5
								",categorysubnameen" + //6
								",descripjp" + //7
								",descripcn" + //8
								",descripen" + //9
								",supplementjp" +//10
								",supplementcn" +//11
								",supplementen" +//12
								",pricejp" + //13
								",pricecn" + //14
								",priceen" + //15
								",runitnamejp" + //16
								",runitnamecn" + //17
								",runitnameen" + //18
								",capactiy" + //19
								",capactiy2" + //20
								",punitnamejp" + //21
								",punitnamecn" + //22
								",punitnameen" + //23
								",linkname1" + //24
								",linkurl1" + //25
								",linkname2" + //26
								",linkurl2" + //27
								",linkname3" + //28
								",linkurl3" + //29
								",dispnum" + //30
								",regdate" + //31
								",duedate" + //32
								",deleteflg" + //33
								",insdatetime" + //34
								",insuser" + //35
								",insmachine" + //36
								",upddatetime" + //37
								",upduser" + //38
								",updmachine	" + //39
								"from rooms " +
								"where roomid=?	"+  //key[0]
								 "AND companyid=?" ;//key[1]


			//接続を設定する
			conn = this.openConnect();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, keys);

			//Select文の実行
			rs = stmt.executeQuery();

			//RoomsBeanオブジェクトを作成する
			RoomsBn bn = new RoomsBn();

			//Select実行の結果をRoomsBeanにセットする
			while(rs.next()) {
				bn.setCategorynamejp(rs.getString(1));
				bn.setCategorynamecn(rs.getString(2));
				bn.setCategorynameen(rs.getString(3));
				bn.setCategorysubnamejp(rs.getString(4));
				bn.setCategorysubnamecn(rs.getString(5));
				bn.setCategorysubnameen(rs.getString(6));
				bn.setDescripjp(rs.getString(7));
				bn.setDescripcn(rs.getString(8));
				bn.setDescripjp(rs.getString(9));
				bn.setSupplementjp(rs.getString(10));
				bn.setSupplementcn(rs.getString(11));
				bn.setSupplementen(rs.getString(12));
				bn.setPricejp(rs.getString(13));
				bn.setPricecn(rs.getString(14));
				bn.setPriceen(rs.getString(15));
				bn.setRunitnamejp(rs.getString(16));
				bn.setRunitnamecn(rs.getString(17));
				bn.setRunitnameen(rs.getString(18));
				bn.setCapactiy(rs.getString(19));
				bn.setCapactiy2(rs.getString(20));
				bn.setPunitnamejp(rs.getString(21));
				bn.setPunitnamecn(rs.getString(22));
				bn.setPunitnameen(rs.getString(23));
				bn.setLinkname1(rs.getString(24));
				bn.setLinkurl1(rs.getString(25));
				bn.setLinkname2(rs.getString(26));
				bn.setLinkurl2(rs.getString(27));
				bn.setLinkname3(rs.getString(28));
				bn.setLinkurl3(rs.getString(29));
				bn.setDispnum(rs.getString(30));
				bn.setRegdate(bn.toString(java.sql.Types.DATE,rs.getString(31)));
				bn.setDuedate(bn.toString(java.sql.Types.DATE,rs.getString(32)));
				bn.setDeleteflg(rs.getString(33));
				bn.setInsdatetime(rs.getString(34));
				bn.setInsuser(rs.getString(35));
				bn.setInsmachine(rs.getString(36));
				bn.setUpddatetime(rs.getString(37));
				bn.setUpduser(rs.getString(38));
				bn.setUpdmachine(rs.getString(39));
//				bn.setRoomid(rs.getString(1));
//				bn.setCompanyid(rs.getString(1));
				bn.setRoomid(keys[0]);
				bn.setCompanyid(keys[1]);
			}
			//リストに保存する
			lstBn.add(bn);
			return lstBn;
		}catch(SQLException e) {
			throw e;
		}finally {
			this.closeResultSet(rs);
			this.closeStatement(stmt);
			this.closeConnect(conn);
		}
	}

	@Override
	public List<IBean> update(String[] columnValues) throws SQLException, InputErrorException {
		//変数を宣言する
		Connection 	conn 	= null;
		PreparedStatement 	stmt 	= null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();
		try {
			//Update文を構成する
			String sql = "update	rooms	" +
					"set  categorynamejp 		= ? " + //[0]
					",categorynamecn 		= ? " + //[1]
					",categorynameen 		= ? " + //[2]
					",categorysubnamejp 		= ? " + //[3]
					",categorysubnamecn 		= ? " + //[4]
					",categorysubnameen 		= ? " + //[5]
					",descripjp 		= ? " + //[6]
					",descripcn 		= ? " + //[7]
					",descripen 		= ? " + //[8]
					",supplementjp 		= ? " +//[9]
					",supplementcn 		= ? " +//[10]
					",supplementen 		= ? " +//[11]
					",pricejp 		= ? " + //[12]
					",pricecn 		= ? " + //[13]
					",priceen 		= ? " + //[14]
					",runitnamejp 		= ? " + //[15]
					",runitnamecn 		= ? " + //[16]
					",runitnameen 		= ? " + //[17]
					",capactiy 		= ? " + //[18]
					",capactiy2 		= ? " + //[19]
					",punitnamejp 		= ? " + //[20]
					",punitnamecn 		= ? " + //[21]
					",punitnameen 		= ? " + //[22]
					",linkname1 		= ? " + //[23]
					",linkurl1 		= ? " + //[24]
					",linkname2 		= ? " + //[25]
					",linkurl2 		= ? " + //[26]
					",linkname3 		= ? " + //[27]
					",linkurl3 		= ? " + //[28]
					",dispnum 		= ? " + //[29]
					",regdate 		= ? " + //[30]
					",duedate 		= ? " + //[31]
					",deleteflg 		= ? " + //[32]
					",upddatetime 		= ? " + //[33]更新日
					",upduser 		= ? " + //[34]
					",updmachine 	= ? 		" + //[35]
					"where roomid		= ?	" + //[36]
					"AND companyid = ?";   //[37]

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//更新日時をセットする
			columnValues[33] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);
			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カテゴリBeanのオブジェクト作成する。
			RoomsBn bn = new RoomsBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。

			if(resultCnt == 1) {
				//送信されたパラメータをRoomsBeanにセットする
				bn.setRoomid(columnValues[36]);
				bn.setCompanyid(columnValues[37]);
				bn.setCategorynamejp(columnValues[0]);
				bn.setCategorynamecn(columnValues[1]);
				bn.setCategorynameen(columnValues[2]);
				bn.setCategorysubnamejp(columnValues[3]);
				bn.setCategorysubnamecn(columnValues[4]);
				bn.setCategorysubnameen(columnValues[5]);
				bn.setDescripjp(columnValues[6]);
				bn.setDescripcn(columnValues[7]);
				bn.setDescripjp(columnValues[8]);
				bn.setSupplementjp(columnValues[9]);
				bn.setSupplementcn(columnValues[10]);
				bn.setSupplementen(columnValues[11]);
				bn.setPricejp(columnValues[12]);
				bn.setPricecn(columnValues[13]);
				bn.setPriceen(columnValues[14]);
				bn.setRunitnamejp(columnValues[15]);
				bn.setRunitnamecn(columnValues[16]);
				bn.setRunitnameen(columnValues[17]);
				bn.setCapactiy(columnValues[18]);
				bn.setCapactiy2(columnValues[19]);
				bn.setPunitnamejp(columnValues[20]);
				bn.setPunitnamecn(columnValues[21]);
				bn.setPunitnameen(columnValues[22]);
				bn.setLinkname1(columnValues[23]);
				bn.setLinkurl1(columnValues[24]);
				bn.setLinkname2(columnValues[25]);
				bn.setLinkurl2(columnValues[26]);
				bn.setLinkname3(columnValues[27]);
				bn.setLinkurl3(columnValues[28]);
				bn.setDispnum(columnValues[29]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE,columnValues[30]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE,columnValues[31]));
				bn.setDeleteflg(columnValues[32]);
				bn.setUpddatetime(columnValues[33]);
				bn.setUpduser(columnValues[34]);
				bn.setUpdmachine(columnValues[35]);

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

	@Override
	public List<IBean> insert(String[] columnValues) throws SQLException, InputErrorException {
		//変数を宣言する
		PreparedStatement stmt = null;
		Connection conn = null;
		//出力用のリストオブジェクトの作成
		List<IBean> lstBn = new ArrayList<IBean>();

		try {
			String sql = "insert	into	rooms(" +
						"roomid" + //[0]
						",companyid" + //[1]
						",categorynamejp" + //[2]
						",categorynamecn" + //[3]
						",categorynameen" + //[4]
						",categorysubnamejp" + //[5]
						",categorysubnamecn" + //[6]
						",categorysubnameen" + //[7]
						",descripjp" + //[8]
						",descripcn" + //[9]
						",descripen" + //[10]
						",supplementjp" +//[11]
						",supplementcn" +//[12]
						",supplementen" +//[13]
						",pricejp" + //[14]
						",pricecn" + //[15]
						",priceen" + //[16]
						",runitnamejp" + //[17]
						",runitnamecn" + //[18]
						",runitnameen" + //[19]
						",capactiy" + //[20]
						",capactiy2" + //[21]
						",punitnamejp" + //[22]
						",punitnamecn" + //[23]
						",punitnameen" + //[24]
						",linkname1" + //[25]
						",linkurl1" + //[26]
						",linkname2" + //[27]
						",linkurl2" + //[28]
						",linkname3" + //[29]
						",linkurl3" + //[30]
						",dispnum" + //[31]
						",regdate" + //[32]
						",duedate" + //[33]
						",deleteflg" + //[34]
						",insdatetime" + //[35]
						",insuser" + //[36]
						",insmachine		)" + //[37]
						"values(" +
						"?,?,?,?,?," + "?,?,?,?,?," +
						"?,?,?,?,?," + "?,?,?,?,?," +
						"?,?,?,?,?," + "?,?,?,?,?," +
						"?,?,?,?,?," + "?,?,?)" ;

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//登録日をセットする
			columnValues[35] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);

			//インサート文を実行する
			int resultCnt = stmt.executeUpdate();

			//RoomsBeanのオブジェクト作成sる。
			RoomsBn bn = new RoomsBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。

			if(resultCnt == 1) {
				//送信されたパラメータをCategoriesBeanにセットする
				bn.setRoomid(columnValues[0]);
				bn.setCompanyid(columnValues[1]);
				bn.setCategorynamejp(columnValues[2]);
				bn.setCategorynamecn(columnValues[3]);
				bn.setCategorynameen(columnValues[4]);
				bn.setCategorysubnamejp(columnValues[5]);
				bn.setCategorysubnamecn(columnValues[6]);
				bn.setCategorysubnameen(columnValues[7]);
				bn.setDescripjp(columnValues[8]);
				bn.setDescripcn(columnValues[9]);
				bn.setDescripjp(columnValues[10]);
				bn.setSupplementjp(columnValues[11]);
				bn.setSupplementcn(columnValues[12]);
				bn.setSupplementen(columnValues[13]);
				bn.setPricejp(columnValues[14]);
				bn.setPricecn(columnValues[15]);
				bn.setPriceen(columnValues[16]);
				bn.setRunitnamejp(columnValues[17]);
				bn.setRunitnamecn(columnValues[18]);
				bn.setRunitnameen(columnValues[19]);
				bn.setCapactiy(columnValues[20]);
				bn.setCapactiy2(columnValues[21]);
				bn.setPunitnamejp(columnValues[22]);
				bn.setPunitnamecn(columnValues[23]);
				bn.setPunitnameen(columnValues[24]);
				bn.setLinkname1(columnValues[25]);
				bn.setLinkurl1(columnValues[26]);
				bn.setLinkname2(columnValues[27]);
				bn.setLinkurl2(columnValues[28]);
				bn.setLinkname3(columnValues[29]);
				bn.setLinkurl3(columnValues[30]);
				bn.setDispnum(columnValues[31]);
				bn.setRegdate(bn.toString(java.sql.Types.DATE,columnValues[32]));
				bn.setDuedate(bn.toString(java.sql.Types.DATE,columnValues[33]));
				bn.setDeleteflg(columnValues[34]);
				bn.setInsdatetime(columnValues[35]);
				bn.setInsuser(columnValues[36]);
				bn.setInsmachine(columnValues[37]);
				//lstBnに保存する
				lstBn.add(bn);
			}
			return lstBn;
		}catch(SQLException e) {
			throw e;
		}finally {
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
			String sql = "delete	from		rooms	" 	+
						"where roomid = ?	AND	companyid = ?";

			conn = this.openConnect();

			stmt = this.setSqlStmtParams(conn, sql, keys);

			int resultCnt = stmt.executeUpdate();

			//RoomsBeanのオブジェクト作成sる。
			RoomsBn bn = new RoomsBn();

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
				Connection 	conn 	= null;
				PreparedStatement 	stmt 	= null;
				//出力用のリストオブジェクトの作成
				List<IBean> lstBn = new ArrayList<IBean>();
				try {
					String sql = "update	rooms		" +
							"set  categorynamejp 		= ? " + //[0]
							",categorynamecn 		= ? " + //[1]
							",categorynameen 		= ? " + //[2]
							",categorysubnamejp 		= ? " + //[3]
							",categorysubnamecn 		= ? " + //[4]
							",categorysubnameen 		= ? " + //[5]
							",descripjp 		= ? " + //[6]
							",descripcn 		= ? " + //[7]
							",descripen 		= ? " + //[8]
							",supplementjp 		= ? " +//[9]
							",supplementcn 		= ? " +//[10]
							",supplementen 		= ? " +//[11]
							",pricejp 		= ? " + //[12]
							",pricecn 		= ? " + //[13]
							",priceen 		= ? " + //[14]
							",runitnamejp 		= ? " + //[15]
							",runitnamecn 		= ? " + //[16]
							",runitnameen 		= ? " + //[17]
							",capactiy 		= ? " + //[18]
							",capactiy2 		= ? " + //[19]
							",punitnamejp 		= ? " + //[20]
							",punitnamecn 		= ? " + //[21]
							",punitnameen 		= ? " + //[22]
							",linkname1 		= ? " + //[23]
							",linkurl1 		= ? " + //[24]
							",linkname2 		= ? " + //[25]
							",linkurl2 		= ? " + //[26]
							",linkname3 		= ? " + //[27]
							",linkurl3 		= ? " + //[28]
							",dispnum 		= ? " + //[29]
							",regdate 		= ? " + //[30]
							",duedate 		= ? " + //[31]
							",deleteflg 		= ? " + //[32]// 削除フラグ
							",upddatetime 		= ? " + //[33]更新日
							",upduser 		= ? " + //[34]
							",updmachine 	= ? 		" + //[35]
							"where roomid		= ?	" + //[36]
							"AND companyid = ?";   //[37]

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//削除フラグを設定する
					columnValues[32] = "true";

					//更新日時をセットする
					columnValues[33] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);
					stmt.executeUpdate();

					//Updateを実行する
					int resultCnt = stmt.executeUpdate();

					//カテゴリBeanのオブジェクト作成する。
					RoomsBn bn = new RoomsBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。

					if(resultCnt == 1) {
						//送信されたパラメータをRoomsBeanにセットする
						bn.setRoomid(columnValues[36]);
						bn.setCompanyid(columnValues[37]);
						bn.setCategorynamejp(columnValues[0]);
						bn.setCategorynamecn(columnValues[1]);
						bn.setCategorynameen(columnValues[2]);
						bn.setCategorysubnamejp(columnValues[3]);
						bn.setCategorysubnamecn(columnValues[4]);
						bn.setCategorysubnameen(columnValues[5]);
						bn.setDescripjp(columnValues[6]);
						bn.setDescripcn(columnValues[7]);
						bn.setDescripjp(columnValues[8]);
						bn.setSupplementjp(columnValues[9]);
						bn.setSupplementcn(columnValues[10]);
						bn.setSupplementen(columnValues[11]);
						bn.setPricejp(columnValues[12]);
						bn.setPricecn(columnValues[13]);
						bn.setPriceen(columnValues[14]);
						bn.setRunitnamejp(columnValues[15]);
						bn.setRunitnamecn(columnValues[16]);
						bn.setRunitnameen(columnValues[17]);
						bn.setCapactiy(columnValues[18]);
						bn.setCapactiy2(columnValues[19]);
						bn.setPunitnamejp(columnValues[20]);
						bn.setPunitnamecn(columnValues[21]);
						bn.setPunitnameen(columnValues[22]);
						bn.setLinkname1(columnValues[23]);
						bn.setLinkurl1(columnValues[24]);
						bn.setLinkname2(columnValues[25]);
						bn.setLinkurl2(columnValues[26]);
						bn.setLinkname3(columnValues[27]);
						bn.setLinkurl3(columnValues[28]);
						bn.setDispnum(columnValues[29]);
						bn.setRegdate(bn.toString(java.sql.Types.DATE,columnValues[30]));
						bn.setDuedate(bn.toString(java.sql.Types.DATE,columnValues[31]));
						bn.setDeleteflg(columnValues[32]);
						bn.setUpddatetime(columnValues[33]);
						bn.setUpduser(columnValues[34]);
						bn.setUpdmachine(columnValues[35]);

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
		Rooms rooms = new Rooms();
		//insertTest(lst, rooms);
		//selectTest(lst, rooms);
		//updatetTest(lst, rooms);
		//deleteSoftTest(lst, rooms);
		deleteTest(lst, rooms);
		selectTest(lst, rooms);


	}
	//insert 確認
	public static void insertTest(List<IBean> lst, Rooms rooms) {
		String[] colmnInsert = {
				"401",
				"015",
				"部屋01",
				"標準間",
				"room01",
				"サブ部屋01",
				"子標準間",
				"subroom01",
				"ディスクラベル",
				"房間描述",
				"description",
				"補足説明01",
				"补充说明",
				"supplementen",
				"10000",
				"600",
				"88",
				"部屋単位",
				"房间单位",
				"runit",
				"4",
				"2",
				"定員単位",
				"定员单位",
				"punitname",
				"linkurl1" ,
				"linkurl1_name" ,
				"linkurl2" ,
				"linkurl2_name" ,
				"linkurl3" ,
				"linkurl3_name" ,
				"3" ,
				"2017-11-10",
				"2017/12/31",
				"false",
				"",
				"wang1001",
				"pc1001" };

		try {
			 lst = rooms.insert(colmnInsert);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//update 確認
	public static void updatetTest(List<IBean> lst, Rooms rooms) {
		String[] colmnUpdate = {
				"部屋_up",
				"標準間_up",
				"room01_up",
				"サブ部屋01_up",
				"子標準間_up",
				"subroom01_up",
				"ディスクラベル_up",
				"房間描述_up",
				"description_up",
				"補足説明01_up",
				"补充说明_up",
				"supplementen_up",
				"200001",
				"12001",
				"1761",
				"部",
				"房房房房房",
				"r",
				"31",
				"11",
				"定員単位_upd",
				"定员单位_upd1",
				"punitname",
				"linkurl1_upd1" ,
				"linkurl1_name_u1" ,
				"linkurl2_upd1" ,
				"linkurl2_name_u1" ,
				"linkurl3_upd1" ,
				"linkurl3_name_u1" ,
				"31" ,
				"2017-11-11",
				"2017/12/30",
				"false",
				"",
				"wang1001_upd1",
				"pc1001_upd1",
				"401",
				"015"};
		try {
			 lst = rooms.update(colmnUpdate);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//select 確認
	public static void selectTest(List<IBean> lst, Rooms rooms) {
		String[] colmnSelect = {"401","015"};
		try {
			 lst = rooms.select(colmnSelect);
			//１が戻ればOK
			//System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("lst.size()=" + lst.size());
		System.out.print(     ((RoomsBn)lst.get(0)).getRoomid()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCompanyid()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorynamejp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorynamecn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorynameen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorysubnamejp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorysubnamecn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCategorysubnameen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDescripjp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDescripcn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDescripen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getSupplementjp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getSupplementcn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getSupplementen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPricejp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPricecn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPriceen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getRunitnamejp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getRunitnamecn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getRunitnameen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCapactiy()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getCapactiy2()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPunitnamejp()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPunitnamecn()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getPunitnameen()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkname1()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkurl1()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkname2()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkurl2()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkname3()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getLinkurl3()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDispnum() +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getRegdate()+"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDuedate()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getDeleteflg()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getInsdatetime()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getInsuser()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getInsmachine()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getUpddatetime()  +"\t"   );
		System.out.print(     ((RoomsBn)lst.get(0)).getUpduser()  +"\t"   );
		System.out.println(     ((RoomsBn)lst.get(0)).getUpdmachine()  +"\t"   );
	}

	//deleteSoft 確認
	public static void deleteSoftTest(List<IBean> lst, Rooms rooms) {
		String[] colmnDeleteSoft = {"部屋01_upd",
				"標準間_upd",
				"room01_upd",
				"サブ部屋01_upd",
				"子標準間_upd",
				"subroom01_upd",
				"ディスクラベル_upd",
				"房間描述_upd",
				"description_upd",
				"補足説明01_upd",
				"补充说明_upd",
				"supplementen_upd",
				"20000",
				"1200",
				"176",
				"部屋単位u",
				"房间单位u",
				"runiu",
				"3",
				"1",
				"定員単位_upd",
				"定员单位_upd",
				"punitnameu",
				"linkurl1_upd" ,
				"linkurl1_name_u" ,
				"linkurl2_upd" ,
				"linkurl2_name_u" ,
				"linkurl3_upd" ,
				"linkurl3_name_u" ,
				"3" ,
				"2017-11-10",
				"2017/12/31",
				"true",
				"",
				"wang1001_upd",
				"pc1001_upd",
				"401",
				"015"};
		try {
			 lst = rooms.softdelete(colmnDeleteSoft);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//delete 確認
	public static void deleteTest(List<IBean> lst, Rooms rooms) {
		String[] colmnDelete = {"401","015"};
		try {
			 lst = rooms.delete(colmnDelete);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
