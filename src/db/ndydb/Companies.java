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

public class Companies extends PgDao {

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
				String sql = "select 	 	" 	+
						"categorynamejp" 		+		//1
						",categorynamecn	" 	+		//2
						",categorynameen" 	+		//3
						",addr1jp" 	+		//4
						",addr2jp" 	+		//5
						",addr3jp"	+		//6
						", zipjp	"	+		//7
						",addr1cn" 	+ 		//8
						",addr2cn" 	+		//9
						", addr3cn 	"	+ 		//10
						",zipcn"	+ 		//11
						",addr1en  	"	+ 		//12
						",addr2en 	" 	+ 		//13
						",addr3en 	" 	+		//14
						",zipen" 	+		//15
						", access1jp 	" 	+		//16
						", access2jp	"   +  //17
						", access1cn "+		//18
						", access2cn "+		//19
						", access1en "+		//20
						", access2en "+		//21
						", tel "+		//22
						", fax "+		//23
						", email "+		//24
						", homeurl "+		//25
						",linkname1" +   //26
						",linkurl1" +   //27
						",linkname2" +   //28
						",linkurl2" +   //29
						",linkname3" +   //30
						",linkurl3" +   //31
						",dispnum" +   //32
						",regdate" +   //33
						",duedate" +   //34
						",deleteflg" +   //35
						",insdatetime" +   //36
						",insuser" +   //37
						",insmachine" +   //38
						",upddatetime" +   //39
						",upduser" +   //40
						",updmachine		" +   //41
						"from companies		 " +
						"where companyid=?" ;//key[0]

				//接続を設定する
				conn = this.openConnect();

				//プリペアードステートメントのパラメータをセットする。
				stmt = this.setSqlStmtParams(conn, sql, keys);
				//Select文の実行
				rs = stmt.executeQuery();

				//Beanオブジェクトを作成する
				CompaniesBn bn = new CompaniesBn();

				//Select実行の結果をCompaniesBeanにセットする
				while(rs.next()) {
					bn.setCategorynamejp(rs.getString(1));
					bn.setCategorynamecn(rs.getString(2));
					bn.setCategorynameen(rs.getString(3));
					bn.setAddr1jp(rs.getString(4));
					bn.setAddr2jp(rs.getString(5));
					bn.setAddr3jp(rs.getString(6));
					bn.setZipjp(rs.getString(7));
					bn.setAddr1cn(rs.getString(8));
					bn.setAddr2cn(rs.getString(9));
					bn.setAddr3cn(rs.getString(10));
					bn.setZipcn(rs.getString(11));
					bn.setAddr1en(rs.getString(12));
					bn.setAddr2en(rs.getString(13));
					bn.setAddr3en(rs.getString(14));
					bn.setZipen(rs.getString(15));
					bn.setAccess1jp(rs.getString(16));
					bn.setAccess2jp(rs.getString(17));
					bn.setAccess1cn(rs.getString(18));
					bn.setAccess2cn(rs.getString(19));
					bn.setAccess1en(rs.getString(20));
					bn.setAccess2en(rs.getString(21));
					bn.setTel(rs.getString(22));
					bn.setFax(rs.getString(23));
					bn.setEmail(rs.getString(24));
					bn.setHomeurl(rs.getString(25));
					bn.setLinkname1(rs.getString(26));
					bn.setLinkurl1(rs.getString(27));
					bn.setLinkname2(rs.getString(28));
					bn.setLinkurl2(rs.getString(29));
					bn.setLinkname3(rs.getString(30));
					bn.setLinkurl3(rs.getString(31));
					bn.setDispnum(rs.getString(32));
					bn.setRegdate(bn.toString(java.sql.Types.DATE,rs.getString(33)));
					bn.setDuedate(bn.toString(java.sql.Types.DATE,rs.getString(34)));
					bn.setDeleteflg(rs.getString(35));
					bn.setInsdatetime(rs.getString(36));
					bn.setInsuser(rs.getString(37));
					bn.setInsmachine(rs.getString(38));
					bn.setUpddatetime(rs.getString(39));
					bn.setUpduser(rs.getString(40));
					bn.setUpdmachine(rs.getString(41));
//					bn.setCompanyid(rs.getString(1));
					bn.setCompanyid(keys[0]);
				}
				//lstBn に保存する
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
				Connection 	conn 	= null;
				PreparedStatement 	stmt 	= null;
				//出力用のリストオブジェクトの作成
				List<IBean> lstBn = new ArrayList<IBean>();
				try {
					//Update文を構成する
					String sql = "update	companies	set		 " +
							"categorynamejp  		= ?" 		+		//[0]
							", categorynamecn			= ?" 	+		//[1]
							", categorynameen 			= ?" 	+		//[2]
							", addr1jp 			= ?" 	+		//[3]
							", addr2jp 			= ?" 	+		//[4]
							", addr3jp 			= ?"	+		//[5]
							", zipjp	   		= ?"	+		//[6]
							", addr1cn 			= ?" 	+ 		//[7]
							", addr2cn 	   = ?" 	+		//[8]
							", addr3cn 			= ?"	+ 		//[9]
							", zipcn 			= ?"	+ 		//[10]
							", addr1en  			= ?"	+ 		//[11]
							", addr2en 			= ?" 	+ 		//[12]
							", addr3en 		= ?" 	+		//[13]
							", zipen 		= ?" 	+		//[14]
							", access1jp 			= ?" 	+		//[15]
							", access2jp			= ?"   +  //[16]
							", access1cn 		= ?"+		//[17]
							", access2cn 		= ?"+		//[18]
							", access1en 		= ?"+		//[19]
							", access2en 		= ?"+		//[20]
							", tel 		= ?"+		//[21]
							", fax 		= ?"+		//[22]
							", email 		= ?"+		//[23]
							", homeurl 		= ?"+		//[24]
							",linkname1		= ?" +   //[25]
							",linkurl1		= ?" +   //[26]
							",linkname2		= ?" +   //[27]
							",linkurl2		= ?" +   //[28]
							",linkname3		= ?" +   //[29]
							",linkurl3		= ?" +   //[30]
							",dispnum		= ?" +   //[31]
							",regdate		= ?" +   //[32]
							",duedate		= ?" +   //[33]
							",deleteflg		= ?" +   //[34]
							",upddatetime		= ?" +   //[35] 更新日
							",upduser		= ?" +   //[36]
							",updmachine	= ?		" +   //[37]
							"where	companyid	 = ?" ;//38]

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//更新日時をセットする
					columnValues[35] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);
					stmt.executeUpdate();

					//Updateを実行する
					int resultCnt = stmt.executeUpdate();

					//カンパニーBeanのオブジェクト作成する。
					CompaniesBn bn = new CompaniesBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。

					if(resultCnt == 1) {
						//送信されたパラメータをCompaniesBeanにセットする
						bn.setCompanyid(columnValues[38]);
						bn.setCategorynamejp(columnValues[0]);
						bn.setCategorynamecn(columnValues[1]);
						bn.setCategorynameen(columnValues[2]);
						bn.setAddr1jp(columnValues[3]);
						bn.setAddr2jp(columnValues[4]);
						bn.setAddr3jp(columnValues[5]);
						bn.setZipjp(columnValues[6]);
						bn.setAddr1cn(columnValues[7]);
						bn.setAddr2cn(columnValues[8]);
						bn.setAddr3cn(columnValues[9]);
						bn.setZipcn(columnValues[10]);
						bn.setAddr1en(columnValues[11]);
						bn.setAddr2en(columnValues[12]);
						bn.setAddr3en(columnValues[13]);
						bn.setZipen(columnValues[14]);
						bn.setAccess1jp(columnValues[15]);
						bn.setAccess2jp(columnValues[16]);
						bn.setAccess1cn(columnValues[17]);
						bn.setAccess2cn(columnValues[18]);
						bn.setAccess1en(columnValues[19]);
						bn.setAccess2en(columnValues[20]);
						bn.setTel(columnValues[21]);
						bn.setFax(columnValues[22]);
						bn.setEmail(columnValues[23]);
						bn.setHomeurl(columnValues[24]);
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
						bn.setUpddatetime(columnValues[35]);
						bn.setUpduser(columnValues[36]);
						bn.setUpdmachine(columnValues[37]);

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
					String sql = "insert into companies" +
							" ( companyid" 		+		//[0]
							", categorynamejp" 		+		//[1]
							", categorynamecn" 	+		//[2]
							", categorynameen" 	+		//[3]
							", addr1jp" 	+		//[4]
							", addr2jp" 	+		//[5]
							", addr3jp"	+		//[6]
							", zipjp"	+		//[7]
							", addr1cn" 	+ 		//[8]
							", addr2cn" 	+		//[9]
							", addr3cn"	+ 		//[10]
							", zipcn"	+ 		//[11]
							", addr1en"	+ 		//[12]
							", addr2en" 	+ 		//[13]
							", addr3en" 	+		//[14]
							", zipen" 	+		//[15]
							", access1jp" 	+		//[16]
							", access2jp"   +  //[17]
							", access1cn"+		//[18]
							", access2cn"+		//[19]
							", access1en"+		//[20]
							", access2en"+		//[21]
							", tel"+		//[22]
							", fax "+		//[23]
							", email"+		//[24]
							", homeurl"+		//[25]
							",linkname1" +   //[26]
							",linkurl1" +   //[27]
							",linkname2" +   //[28]
							",linkurl2" +   //[29]
							",linkname3" +   //[30]
							",linkurl3" +   //[31]
							",dispnum" +   //[32]
							",regdate" +   //[33]
							",duedate" +   //[34]
							",deleteflg" +   //[35]
							",insdatetime" +   //[36] 登録日
							",insuser" +   //[37]
							",insmachine)" +   //[38]
							"values "   +
							"(?,?,?,?,?," + "?,?,?,?,?," +
							"?,?,?,?,?," + "?,?,?,?,?," +
							"?,?,?,?,?," + "?,?,?,?,?," +
							"?,?,?,?,?," + "?,?,?,?)";

					//接続を設定する
					conn = this.openConnect();

					//?マークに対する、プリペアードパラメータ全て設定する。
					stmt = conn.prepareStatement(sql);

					//登録日をセットする
					columnValues[36] = this.getSqlTimespanStringNow();

					//プリペアードステートメントのパラメータをセットする。
					stmt = this.setSqlStmtParams(conn, sql, columnValues);

					//インサート文を実行する
					int resultCnt = stmt.executeUpdate();

					//カンパニーBeanのオブジェクト作成sる。
					CompaniesBn bn = new CompaniesBn();

					//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
					//Beanオブジェクトを追加する。
					//resultCntに1以上設定されている場合は更新完了とする。
					//通常は、主キーで更新するので1が戻されるように実装する。

					if(resultCnt == 1) {
						//送信されたパラメータをCompaniesBeanにセットする
						bn.setCompanyid(columnValues[0]);
						bn.setCategorynamejp(columnValues[1]);
						bn.setCategorynamecn(columnValues[2]);
						bn.setCategorynameen(columnValues[3]);
						bn.setAddr1jp(columnValues[4]);
						bn.setAddr2jp(columnValues[5]);
						bn.setAddr3jp(columnValues[6]);
						bn.setZipjp(columnValues[7]);
						bn.setAddr1cn(columnValues[8]);
						bn.setAddr2cn(columnValues[9]);
						bn.setAddr3cn(columnValues[10]);
						bn.setZipcn(columnValues[11]);
						bn.setAddr1en(columnValues[12]);
						bn.setAddr2en(columnValues[13]);
						bn.setAddr3en(columnValues[14]);
						bn.setZipen(columnValues[15]);
						bn.setAccess1jp(columnValues[16]);
						bn.setAccess2jp(columnValues[17]);
						bn.setAccess1cn(columnValues[18]);
						bn.setAccess2cn(columnValues[19]);
						bn.setAccess1en(columnValues[20]);
						bn.setAccess2en(columnValues[21]);
						bn.setTel(columnValues[22]);
						bn.setFax(columnValues[23]);
						bn.setEmail(columnValues[24]);
						bn.setHomeurl(columnValues[25]);
						bn.setLinkname1(columnValues[26]);
						bn.setLinkurl1(columnValues[27]);
						bn.setLinkname2(columnValues[28]);
						bn.setLinkurl2(columnValues[29]);
						bn.setLinkname3(columnValues[30]);
						bn.setLinkurl3(columnValues[31]);
						bn.setDispnum(columnValues[32]);
						bn.setRegdate(bn.toString(java.sql.Types.DATE,columnValues[33]));
						bn.setDuedate(bn.toString(java.sql.Types.DATE,columnValues[34]));
						bn.setDeleteflg(columnValues[35]);
						bn.setInsdatetime(columnValues[36]);
						bn.setInsuser(columnValues[37]);
						bn.setInsmachine(columnValues[38]);
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
					String sql = "delete from companies " 	+
								"WHERE companyid = ? ";

					conn = this.openConnect();

					stmt = this.setSqlStmtParams(conn, sql, keys);

					int resultCnt = stmt.executeUpdate();

					//カンパニーBeanのオブジェクト作成sる。
					CompaniesBn bn = new CompaniesBn();

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
			String sql = "update	companies	set		" +
					"  categorynamejp  		= ?" 		+		//[0]
					", categorynamecn			= ?" 	+		//[1]
					", categorynameen 			= ?" 	+		//[2]
					", addr1jp 			= ?" 	+		//[3]
					", addr2jp 			= ?" 	+		//[4]
					", addr3jp 			= ?"	+		//[5]
					", zipjp	   		= ?"	+		//[6]
					", addr1cn 			= ?" 	+ 		//[7]
					", addr2cn 	   = ?" 	+		//[8]
					", addr3cn 			= ?"	+ 		//[9]
					", zipcn 			= ?"	+ 		//[10]
					", addr1en  			= ?"	+ 		//[11]
					", addr2en 			= ?" 	+ 		//[12]
					", addr3en 		= ?" 	+		//[13]
					", zipen 		= ?" 	+		//[14]
					", access1jp 			= ?" 	+		//[15]
					", access2jp			= ?"   +  //[16]
					", access1cn 		= ?"+		//[17]
					", access2cn 		= ?"+		//[18]
					", access1en 		= ?"+		//[19]
					", access2en 		= ?"+		//[20]
					", tel 		= ?"+		//[21]
					", fax 		= ?"+		//[22]
					", email 		= ?"+		//[23]
					", homeurl 		= ?"+		//[24]
					",linkname1		= ?" +   //[25]
					",linkurl1		= ?" +   //[26]
					",linkname2		= ?" +   //[27]
					",linkurl2		= ?" +   //[28]
					",linkname3		= ?" +   //[29]
					",linkurl3		= ?" +   //[30]
					",dispnum		= ?" +   //[31]
					",regdate		= ?" +   //[32]
					",duedate		= ?" +   //[33]
					",deleteflg		= ?" +   //[34] 削除フラグ
					",upddatetime		= ?" +   //[35] 更新日
					",upduser		= ?" +   //[36]
					",updmachine		= ?		" +   //[37]
					"where	companyid	= ?" ;//38]

			//接続を設定する
			conn = this.openConnect();

			//?マークに対する、プリペアードパラメータ全て設定する。
			stmt = conn.prepareStatement(sql);

			//削除フラグを設定する
			columnValues[34] = "true";

			//更新日時をセットする
			columnValues[35] = this.getSqlTimespanStringNow();

			//プリペアードステートメントのパラメータをセットする。
			stmt = this.setSqlStmtParams(conn, sql, columnValues);
			stmt.executeUpdate();

			//Updateを実行する
			int resultCnt = stmt.executeUpdate();

			//カンパニーBeanのオブジェクト作成する。
			CompaniesBn bn = new CompaniesBn();

			//成功判定（正常に更新されているかどうか）すればリストオブジェクトに
			//Beanオブジェクトを追加する。
			//resultCntに1以上設定されている場合は更新完了とする。
			//通常は、主キーで更新するので1が戻されるように実装する。
			if(resultCnt == 1) {
				bn.setCompanyid(columnValues[38]);
				bn.setCategorynamejp(columnValues[0]);
				bn.setCategorynamecn(columnValues[1]);
				bn.setCategorynameen(columnValues[2]);
				bn.setAddr1jp(columnValues[3]);
				bn.setAddr2jp(columnValues[4]);
				bn.setAddr3jp(columnValues[5]);
				bn.setZipjp(columnValues[6]);
				bn.setAddr1cn(columnValues[7]);
				bn.setAddr2cn(columnValues[8]);
				bn.setAddr3cn(columnValues[9]);
				bn.setZipcn(columnValues[10]);
				bn.setAddr1en(columnValues[11]);
				bn.setAddr2en(columnValues[12]);
				bn.setAddr3en(columnValues[13]);
				bn.setZipen(columnValues[14]);
				bn.setAccess1jp(columnValues[15]);
				bn.setAccess2jp(columnValues[16]);
				bn.setAccess1cn(columnValues[17]);
				bn.setAccess2cn(columnValues[18]);
				bn.setAccess1en(columnValues[19]);
				bn.setAccess2en(columnValues[20]);
				bn.setTel(columnValues[21]);
				bn.setFax(columnValues[22]);
				bn.setEmail(columnValues[23]);
				bn.setHomeurl(columnValues[24]);
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
				bn.setUpddatetime(columnValues[35]);
				bn.setUpduser(columnValues[36]);
				bn.setUpdmachine(columnValues[37]);

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
		Companies comp = new Companies();
		//insertTest(lst, comp);
		selectTest(lst, comp);
		//updateTest(lst, comp);
		//deleteSoftTest(lst, comp);
		deleteTest(lst, comp);
		selectTest(lst, comp);
	}
	//insert 確認
	public static void insertTest(List<IBean> lst, Companies comp) {
		/*String[] colmnInsert = {
				"006" ,
				"001ソリューション㈱" ,
				"001解决方案有限公司",
				" 001solutionCompany" ,
				"川崎市麻生区1-1" ,
				"川崎市麻生区1-2" ,
				"川崎市麻生区1-3" ,
				"215-0004" ,
				"cn川崎市麻生区1-1" ,
				"cn川崎市麻生区1-2" ,
				"cn川崎市麻生区1-3" ,
				"213200" ,
				 "kawasakiasaoku1-1",
				"kawasakiasaoku1-2" ,
				"kawasakiasaoku1-3" ,
				"897570" ,
				"アクセス1" ,
				"アクセス2" ,
				"访问1" ,
				"访问2" ,
				"access1"  ,
				"access2" ,
				"044-123-4567" ,
				"044-765-4321" ,
				"company@yahoo.co.jp" ,
				"www.company.com" ,
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
				"wang",
				"pc001" };*/
		String[] colmnInsert = {
				"kinba",
				"金馬ホテル",
				"金马酒店",
				"Golden horse hotel",
				"東京都台東区駒形2丁目6－3",
				"東京都台東区駒形2丁目6－2",
				"東京都台東区駒形2丁目6－1",
				"111-0043",
				"东京都台东区驹行2丁目6－3",
				"东京都台东区驹行2丁目6－2",
				"东京都台东区驹行2丁目6－1",
				"112-0043",
				"2-6-3, Komagata, Taito District, Tokyo",
				"2-6-2, Komagata, Taito District, Tokyo",
				"2-6-1, Komagata, Taito District, Tokyo",
				"113-0043",
				"アクセス1" ,
				"アクセス2" ,
				"访问1" ,
				"访问2" ,
				"access1"  ,
				"access2" ,
				"044-123-4567" ,
				"044-765-4321" ,
				"company@yahoo.co.jp" ,
				"www.company.com" ,
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
				"wang",
				"pc001"	};
		try {
			 lst = comp.insert(colmnInsert);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//update 確認
	public static void updateTest(List<IBean> lst, Companies comp) {

		/*String[] colmnUpdate =  {

				"001ソリューション㈱_update" ,
				"001解决方案有限公司_update",
				" 001solutionCompany_update" ,
				"川崎市麻生区1-1_update" ,
				"川崎市麻生区1-2_update" ,
				"川崎市麻生区1-3_update" ,
				"215-0005" ,
				"cn川崎市麻生区1-1_update" ,
				"cn川崎市麻生区1-2_update" ,
				"cn川崎市麻生区1-3_update" ,
				"213201" ,
				 "kawasakiasaoku1-1_update",
				"kawasakiasaoku1-2_update" ,
				"kawasakiasaoku1-3_update" ,
				"897571" ,
				"アクセス1_update" ,
				"アクセス2_update" ,
				"访问1_update" ,
				"访问2_update" ,
				"access1_update"  ,
				"access2_update" ,
				"042-123-4567" ,
				"042-765-4321" ,
				"company@yahoo.co.jp_update" ,
				"www.company.com_update" ,
				"linkurl1_update" ,
				"linkurl1_name_update" ,
				"linkurl2_update" ,
				"linkurl2_name" ,
				"linkurl3_update" ,
				"linkurl3_name_update" ,
				"4" ,
				"2017-11-10",
				"2017/12/31",
				"false",
				"",
				"wang_update",
				"pc002",
				"001" ,};*/
		String[] colmnUpdate = {
				"金馬ホテル民宿",
				"金马酒店民宿",
				"Golden horse hotel_up",
				"東京都台東区駒形2丁目6－3_up",
				"東京都台東区駒形2丁目6－2_up",
				"東京都台東区駒形2丁目6－1_up",
				"111-0043",
				"东京都台东区驹行2丁目6－3_up",
				"东京都台东区驹行2丁目6－2_up",
				"东京都台东区驹行2丁目6－1_up",
				"112-0043",
				"2-6-3_up, Komagata, Taito District, Tokyo",
				"2-6-2_up, Komagata, Taito District, Tokyo",
				"2-6-1_up, Komagata, Taito District, Tokyo",
				"113-0043",
				"アクセス1" ,
				"アクセス2" ,
				"访问1" ,
				"访问2" ,
				"access1"  ,
				"access2" ,
				"044-123-4567" ,
				"044-765-4321" ,
				"company@yahoo.co.jp" ,
				"www.company.com" ,
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
				"2017/12/11",
				"wang",
				"pc001",
				"kinba"
				};
		try {
			 lst = comp.update(colmnUpdate);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//select 確認
	public static void selectTest(List<IBean> lst, Companies comp) {
		String[] colmnSelect = {"002"};
		try {
			 lst = comp.select(colmnSelect);
//			１が戻ればOK
//			System.out.println("lst.size()=" + lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("lst.size()=" + lst.size());
		System.out.print(     ((CompaniesBn)lst.get(0)).getCompanyid()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getCategorynamejp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getCategorynamecn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getCategorynameen()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr1jp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr2jp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr3jp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getZipjp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr1cn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr2cn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr3cn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getZipcn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr1en()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr2en()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAddr3en()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getZipen()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess1jp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess2jp()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess1cn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess2cn()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess1en()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getAccess2en()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getTel()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getFax()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getEmail()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getHomeurl()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkname1()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkurl1()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkname2()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkurl2()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkname3()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getLinkurl3()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getDispnum() +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getRegdate()+"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getDuedate()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getDeleteflg()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getInsdatetime()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getInsuser()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getInsmachine()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getUpddatetime()  +"\t"   );
		System.out.print(     ((CompaniesBn)lst.get(0)).getUpduser()  +"\t"   );
		System.out.println(     ((CompaniesBn)lst.get(0)).getUpdmachine()  +"\t"   );
	}

	//deleteSoft 確認
	public static void deleteSoftTest(List<IBean> lst, Companies comp) {
		String[] colmnDeleteSoft = {
				"001ソリューション㈱_update" ,
				"001解决方案有限公司_update",
				" 001solutionCompany_update" ,
				"川崎市麻生区1-1_update" ,
				"川崎市麻生区1-2_update" ,
				"川崎市麻生区1-3_update" ,
				"215-0005" ,
				"cn川崎市麻生区1-1_update" ,
				"cn川崎市麻生区1-2_update" ,
				"cn川崎市麻生区1-3_update" ,
				"213201" ,
				 "kawasakiasaoku1-1_update",
				"kawasakiasaoku1-2_update" ,
				"kawasakiasaoku1-3_update" ,
				"897571" ,
				"アクセス1_update" ,
				"アクセス2_update" ,
				"访问1_update" ,
				"访问2_update" ,
				"access1_update"  ,
				"access2_update" ,
				"042-123-4567" ,
				"042-765-4321" ,
				"company@yahoo.co.jp_update" ,
				"www.company.com_update" ,
				"linkurl1_update" ,
				"linkurl1_name_update" ,
				"linkurl2_update" ,
				"linkurl2_name" ,
				"linkurl3_update" ,
				"linkurl3_name_update" ,
				"4" ,
				"2017-11-10",
				"2017/12/31",
				"false",
				"",
				"wang_DeleteSoft",
				"pc002_DeleteSoft",
				"002" ,};
		try {
			 lst = comp.softdelete(colmnDeleteSoft);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//delete 確認
	public static void deleteTest(List<IBean> lst, Companies comp) {
		String[] colmnDelete = {"004"};
		try {
			 lst = comp.delete(colmnDelete);
//			１が戻ればOK
//			System.out.println(lst.size());
		} catch (InputErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
