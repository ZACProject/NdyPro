package db.ndydb;

import db.Bean;

public class UsersBn extends Bean {

	private String userid 		= "";
	private String username	= "";
	private String password	= "";
	private String linkname	= "";
	private String linkurl		= "";
	private String firstname	= "";
	private String lastname	= "";
	private String userlevel	= "";
	private String company		= "";
	private String dept		= "";
	private String regdate		= "";
	private String duedate		= "";

	/**
	 * ユーザーＩＤ
	 * @return ユーザーＩＤを戻す
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * ユーザーＩＤを設定する
	 * @param userid ユーザーＩＤを設定する
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * ユーザー名を戻す
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * ユーザー名を設定する。
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * ユーザーパスワードを戻す
	 * @return ユーザーパスワードを戻す
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ユーザーパスワードを設定する
	 * @param password  ユーザーパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * リンク名を戻す
	 * @return リンク名を戻す
	 */
	public String getLinkname() {
		return linkname;
	}

	/**
	 * リンク名を設定する
	 * @param linkname リンク名
	 */
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	/**
	 * リンクＵＲＬを戻す
	 * @return リンクＵＲＬを戻す
	 */
	public String getLinkurl() {
		return linkurl;
	}

	/**
	 * リンクＵＲＬを設定する
	 * @param linkurl リンクＵＲＬ
	 */
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	/**
	 * ユーザー名前を戻す
	 * @return ユーザー名前を戻す
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * ユーザー名前を設定する
	 * @param firstname ユーザー名
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * ユーザー姓を戻す
	 * @return ユーザー姓を戻す
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * ユーザー姓を設定する
	 * @param lastname ユーザー姓
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * ユーザー権限を戻す
	 * @return ユーザー権限を戻す
	 */
	public String getUserlevel() {
		return userlevel;
	}

	/**
	 * ユーザー権限を設定する
	 * @param userlevel ユーザー権限
	 */
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	/**
	 * 会社名を戻す
	 * @return 会社名
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 会社名を設定する
	 * @param company 会社名
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 組織名を戻す
	 * @return 組織名を戻す
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * 組織名を設定する
	 * @param dept 組織名
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * 開始日を戻す
	 * @return	開始日
	 */
	public String getRegdate() {
		return regdate;
	}

	/**
	 * 開始日を設定する
	 * @param regdate 開始日
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	/**
	 * 期限日を戻す
	 * @return 期限日を戻す
	 */
	public String getDuedate() {
		return duedate;
	}

	/**
	 * 期限日を設定する
	 * @param duedate 期限日
	 */
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}





	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
