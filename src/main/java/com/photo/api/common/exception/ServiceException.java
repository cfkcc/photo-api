package com.photo.api.common.exception;

import com.photo.api.common.error.ErrorCode;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -6690245865432215153L;

	private String errCode = ErrorCode.CODE_ERROR.getCode();

	public ServiceException(String errMsg, Throwable throwable) {
		super(errMsg, throwable);
	}

	public ServiceException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
	}

	public ServiceException(String errCode, String errMsg, Throwable throwable) {
		super(errMsg, throwable);
		this.errCode = errCode;
	}

	public ServiceException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getEnMessage(), throwable);
		this.errCode = errorCode.getCode();
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
