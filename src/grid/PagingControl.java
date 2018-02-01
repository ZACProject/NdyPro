package grid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagingControl {

	/**
	 * 現在開始行
	 */
	protected int currentPage;

	/**
	 * 現在のページを取得する
	 * @return
	 */
	public int getCurrentPage() {

		if(this.totalCount == 0) {
			this.currentPage =0;
		}
		return this.currentPage;
	}

	/**
	 * 現在のページ
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 最大ページ数の取得
	 * @return トータルページから最大ページを戻す
	 */
	public int getMaxPage() {

		int	intMaxPage = 0;
		if ((this.totalCount % this.pageSize) == 0){
				intMaxPage = this.totalCount / this.pageSize;
		}else{
			intMaxPage = (this.totalCount / this.pageSize) + 1;
		}
		return intMaxPage;
	}

	protected int offsetRec;

	/**
	 * オフセット位置の取得（先頭行から末尾までの行位置）
	 * @return オフセット位置を戻す
	 */
	public int getOffsetRec() {
		int offset ;
		if(this.currentPage <= 0) {
			offset = 0;
		}else {
			 offset = (this.currentPage -1) * this.pageSize;
		}
		return offset;
	}

	/**
	 * データの件数
	 */
	protected int totalCount;

	/**
	 * グリッドのトータル件数を設定する。
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * ページサイズ
	 */
	protected int pageSize;

	/**
	 * ページサイズのゲッターメソッド
	 * @return グリッドのページサイズを戻す
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ページサイズのセッターメソッド
	 * @param pageSize グリッドのページサイズを設定する。
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 *ページングサイズコントロールのコンストラクタです。
	 *データ件数とページ初期値をセットします。
	 */
	public PagingControl() {
		this.pageSize = 5;
		this.currentPage = 1;
	}

	/**
	 * ページを進める
	 */
	public void moveNext() {

		if(this.totalCount ==0) {
			this.currentPage =0;
		}else if( (this.currentPage +1) <= this.getMaxPage()){
			this.currentPage ++;
		}else {
			this.currentPage = this.getMaxPage();
		}
	}

	/**
	 * ページを戻す
	 */
	public void movePrevious() {

		if(this.totalCount ==0) {
			this.currentPage =0;
		}else if( (this.currentPage -1) >= 1){

			this.currentPage --;
		}else {
			this.currentPage = 1;
		}
	}

	/**
	 * 最初のページへ
	 */
	public void moveFirst() {

		if(this.totalCount ==0) {
			this.currentPage =0;
		}else {
			this.currentPage =1;
		}
	}

	/**
	 * 最後のページへ
	 */
	public void moveLast() {

		if(this.totalCount ==0) {
			this.currentPage =0;
		}else {
			this.currentPage = this.getMaxPage();
		}
	}


	/**
	 * SqlSourceで取得したレコード数を求めるヘルパーメソッド
	 * @return レコード数を戻す
	 * @throws SQLException SQL文にErrorがある場合,SQLExceptionがスローされます。
	 */
	public int setTotalRecCount(Connection cn,String Sql) throws SQLException{
		int		  intRet  	= 0;
		Statement stmt 		= null;
		ResultSet rs	 	= null;
		try{
			stmt = 	cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs 	=	stmt.executeQuery(Sql);
			//最後まで移動して件数を求める
			rs.last();
			intRet= rs.getRow();
			return this.totalCount = intRet;

		}catch (SQLException eSql){
			throw eSql;
		}finally {
			if (rs!= null){
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}



	/**
	 * 現在のページから開始行を求める
	 * @return
	 */
	public int getStartRecord() {

		int intStartRowNum = 0;
		if( this.currentPage == 1) {
			return intStartRowNum;
		}else {
			return ((this.currentPage -1) * this.pageSize);
		}
	}




	public static void main(String[] args) {

		/*
		 * ページングテスト
		 *
		 * */

		PagingControl pg = new PagingControl();
		pg.setTotalCount(20);

		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage()+ "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage()+ "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.moveNext();
		System.out.println(pg.getCurrentPage()+ "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());
		pg.movePrevious();
		System.out.println(pg.getCurrentPage() + "  " + pg.getOffsetRec());

	}

}
