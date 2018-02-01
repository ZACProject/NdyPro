package db;

import java.sql.SQLException;
import java.util.List;

/**
* IDaoインターフェース
* @author Hideo Shimizu
* @version 1.0
* @since 1.0
*/
public interface IDao {

	/**
	 * select文を実行します。
	 * @param keys 主キーを文字配列で設定します。
	 * @return 実行結果をBeanのリストで戻します。件数がない場合は、サイズゼロで判断します。
	 * @throws InputErrorException 独自例外スロー
	 */
	public List<IBean> select(String[] keys) throws SQLException,InputErrorException;

	/**
	 * update文を実行します。
	 * @param columnsValues update文のパラメータ？に合わせて、文字配列を設定します。
	 * @return 実行結果をBeanのリストで戻します。リストに件数がない場合は、失敗サイズゼロします。
	 * @throws InputErrorException 独自例外スロー
	 */
	public List<IBean> update(String[] columnValues) throws SQLException,InputErrorException;

	/**
	 * insert文を実行します
	 * @param columnsValues columnsValues insert文のパラメータ？に合わせて、文字配列を設定します。
	 * @return 実行結果をBeanのリストで戻します。リストに件数がない場合は、失敗サイズゼロします。
	 * @throws InputErrorException 独自例外スロー
	 */
	public List<IBean> insert(String[] columnValues) throws SQLException,InputErrorException;

	/**
	 * delete文を実行します
	 * @param columnsValues keys 主キーを文字配列で設定します。
	 * @return 実行結果をBeanのリストで戻します。件数がない場合は、正常実行（削除された）空のBeanオブジェクト1件含んだリストを戻します。
	 * 失敗した場合は、リストゼロ件とします。
	 * @throws InputErrorException 独自例外スロー
	 */
	public List<IBean> delete(String[] keys) throws SQLException,InputErrorException;

	/**
	 * 論理削除（update）文を実行します。通常は、削除フラグ deleteflg を'false'にセットします。
	 * @param columnsValues update文のパラメータ？に合わせて、文字配列を設定します。
	 * @return 実行結果をBeanのリストで戻します。リストに件数がない場合は、失敗サイズゼロします。
	 * @throws InputErrorException 独自例外スロー
	 */
	public List<IBean> softdelete(String[] columnValues) throws SQLException,InputErrorException;

}
