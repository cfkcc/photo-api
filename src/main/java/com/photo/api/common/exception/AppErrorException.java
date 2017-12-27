package	com.photo.api.common.exception;

import com.photo.api.common.error.ErrorCode;

/**
 * 数据访问异常
 * @date 2016年2月29日
 */
public class AppErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorCode = ErrorCode.CODE_ERROR.getCode();


	public AppErrorException(Throwable exception) {
		super(exception);
	}
	public AppErrorException(Throwable exception,String msg) {
		super(msg,exception);
	}
	public AppErrorException(Throwable exception,String errorCode,String errorMsg) {
		super(errorMsg,exception);
		this.errorCode= errorCode;
	}
	
	public AppErrorException(String errorCode,String errorMsg) {
		super(errorMsg);
		this.errorCode= errorCode;
		
	}
	
	public AppErrorException(String message){
		super(message);
	}
	
	public String getCode() {
		return errorCode==null?"":errorCode;
	}
	public String getMessage(){
		return super.getMessage();
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
