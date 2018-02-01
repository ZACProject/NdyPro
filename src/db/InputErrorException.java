package db;

public class InputErrorException extends Exception {

	/**
	 * 入力に関するエラーなど独自定義を定義します。
	 * @author Hideo Shimizu
	 * @version 1.0
	 * @since 1.0
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String strMessage;

	public InputErrorException(){

	}

	public InputErrorException(String strErrorMsg){
		this();
		this.strMessage = strErrorMsg;
	}

	public void setMessage(String strMessage) {
		this.strMessage = strMessage;
	}

	public String getMessage() {
		return strMessage;
	}

}
