package com.photo.api.common.result;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.photo.api.common.error.ErrorCode;

public class ResponseMessage<T> {
	@JsonProperty("code")
	private String code;
	@JsonProperty("msg")
	private String msg;
	@JsonProperty("time")
	private Date time = Calendar.getInstance().getTime();//响应时间
	private T data;

	public ResponseMessage() {
		this.code = ErrorCode.SUCCESS.getCode();
		this.msg = ErrorCode.SUCCESS.getMessage();
	}

	public ResponseMessage(String code) {
		this.code = code;
	}
	
	public ResponseMessage(T data) {
		this(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
	}

	public ResponseMessage(String code, String msg,T data) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public ResponseMessage(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", msg=" + msg + ", time=" + time
				+ ", data=" + data + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
