package db.ndydb;

import db.Bean;

/**
 * カテゴリのBeanクラス。Beanクラスを継承します。
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 *
 */
public class CategoriesBn extends Bean {


	private String categoryid 		= "";
	private String categoryname 	= "";
	private String description 	= "";
	private String linkname		= "";
	private String linkurl			= "";
	private String regdate		= "";
	private String duedate		= "";

	/**
	 * カテゴリIDを戻す
	 * @return カテゴリID
	 */
	public String getCategoryid() {
		return categoryid;
	}

	/**
	 * カテゴリIDを設定する。
	 * @param categoryid カテゴリID
	 */
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * カテゴリ名を戻す
	 * @return カテゴリ名
	 */
	public String getCategoryname() {
		return categoryname;
	}

	/**
	 * カテゴリ名を設定する
	 * @param categoryname
	 */
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	/**
	 * 説明を戻す
	 * @return 説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明を設定する
	 * @param description 説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * リンク名を戻す
	 * @return リンク名
	 */
	public String getLinkname() {
		return linkname;
	}

	/**
	 * リンク名を設定する。
	 * @param linkname リンク名
	 */
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	/**
	 * リンクURLを戻す
	 * @return リンクURL
	 */
	public String getLinkurl() {
		return linkurl;
	}

	/**
	 * リンクURLを設定する
	 * @param linkurl リンクURL
	 */
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	/**
	 * 開始日を戻す
	 * @return 開始日
	 */
	public String getRegdate() {
		return regdate;
	}

	/**
	 * 開始日を設定する
	 * @param regdate
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	/**
	 * 期限を戻す
	 * @return 期限
	 */
	public String getDuedate() {
		return duedate;
	}

	/**
	 * 期限を設定する
	 * @param duedate 期限
	 */
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}


}
