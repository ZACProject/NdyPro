package db;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Beanの抽象クラスです。テーブル用のBeanオブジェクトを生成する場合、
 * このクラスを継承してください。 管理列のSetter,Getterを実装します。
 * Beanクラスのプロパティはすべて文字列で内部変数保持します。
 * @author Hideo Shimizu
 * @version 1.0
 * @since 1.0
 */
public abstract class Bean implements IBean{

	private String deleteflg		= "";
	private String insdatetime		= "";
	private String insuser			= "";
    private String insmachine		= "";
    private String upddatetime		= "";
    private String upduser			= "";
    private String updmachine		= "";

    /**
     * 削除フラグを戻す
     * @return 削除フラグ
     */
	public String getDeleteflg() {
		return deleteflg;
	}

	/**
	 * 削除フラグを設定する
	 * @param deleteflg 削除フラグ
	 */
	public void setDeleteflg(String deleteflg) {
		this.deleteflg = deleteflg;
	}

	/**
	 * 登録日時を戻す
	 * @return 登録日時
	 */
	public String getInsdatetime() {
		return this.insdatetime;
	}

	/**
	 * 登録日時を設定する
	 * * @param insdatetime
	 */
	public void setInsdatetime(String insdatetime) {
		this.insdatetime = insdatetime;
	}

	/**
	 * 登録者を戻す
	 * @return 登録者
	 */
	public String getInsuser() {
		return insuser;
	}

	/**
	 * 登録者を設定する
	 * @param insuser 登録者
	 */
	public void setInsuser(String insuser) {
		this.insuser = insuser;
	}

	/**
	 * 登録端末を戻す
	 * @return 登録端末
	 */
	public String getInsmachine() {
		return insmachine;
	}

	/**
	 * 登録端末を設定する
	 * @param insmachine 登録端末
	 */
	public void setInsmachine(String insmachine) {
		this.insmachine = insmachine;
	}

	/**
	 * 更新日時を戻す
	 * @return 更新日時
	 */
	public String getUpddatetime() {
		return this.upddatetime;
	}

	/**
	 * 更新時間を設定する
	 * @param upddatetime 更新日時
	 */
	public void  setUpddatetime(String upddatetime){
		this.upddatetime = upddatetime;
	}

	/**
	 * 更新者を戻す
	 * @return 更新者
	 */
	public String getUpduser() {
		return upduser;
	}

	/**
	 * 更新者を設定する
	 * @param upduser 更新者
	 */
	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}

	/**
	 * 更新端末を戻す
	 * @return 更新端末
	 *
	 */
	public String getUpdmachine() {
		return updmachine;
	}
	/**
	 * 更新端末を設定する
	 * @param updmachine 更新端末
	 */
	public void setUpdmachine(String updmachine) {
		this.updmachine = updmachine;
	}

	public String toString(int type,String value) throws InputErrorException {

		if(type == java.sql.Types.DATE){

			Calendar cal = DateUtil.toCalendar(value);

			String str = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());

			return str;

		}
		return value;

	}

}
