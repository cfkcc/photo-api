package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserEmailCode implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static enum Status{
		Unused(0),						//未使用的
		Unvalid(1),						//无效的或过期的
		Used(-1);						//已使用的
		private int status;
		Status(int status) {
			this.status = status;
		}
		public int getStatus(){
			return status;
		}
	}
	private String codeId;
	private String userId;
	private Integer status;
	private Integer type;
	private String email;
	private String code;
	private Date createTime;
	private Date expireTime;
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	@Override
	public String toString() {
		return "UserEmailCode [codeId=" + codeId + ", userId=" + userId + ", status=" + status + ", type=" + type
				+ ", email=" + email + ", code=" + code + ", createTime=" + createTime + ", expireTime=" + expireTime
				+ "]";
	}
}
