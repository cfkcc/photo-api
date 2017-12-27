package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserLoginRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String loginRecordId;
	private String UserId;
	private Date createTime;
	private String createUser;
	private String loginIp;
	private Integer loginType;
	private String token;
	public String getLoginRecordId() {
		return loginRecordId;
	}
	public void setLoginRecordId(String loginRecordId) {
		this.loginRecordId = loginRecordId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "UserLoginRecord [loginRecordId=" + loginRecordId + ", UserId=" + UserId + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", loginIp=" + loginIp + ", loginType=" + loginType + ", token="
				+ token + "]";
	}
}
