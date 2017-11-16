package com.photo.api.common.result;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.photo.api.common.error.ErrorCode;

public class ResponseMessage<T> {
	@JsonProperty("status")
	private String status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("timestamp")
	private Date time = Calendar.getInstance().getTime();//响应时间
	private T data;

	public ResponseMessage() {
		this.status = ErrorCode.SUCCESS.getCode();
		this.message = ErrorCode.SUCCESS.getMessage();
	}

	public ResponseMessage(String code) {
		this.status = code;
	}

	public ResponseMessage(T data) {
		this(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
	}

	public ResponseMessage(String code, String message,T data) {
		this.status = code;
		this.data = data;
		this.message = message;
	}

	public ResponseMessage(String code, String message) {
		this.status = code;
		this.message = message;
	}
	

	@Override
	public String toString() {
		return "ResponseMessage [status=" + status + ", message=" + message + ", timestamp=" + time
				+ ", data=" + data + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
