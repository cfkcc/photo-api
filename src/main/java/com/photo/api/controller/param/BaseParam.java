package com.photo.api.controller.param;

import java.io.Serializable;

public class BaseParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String sysType;
	private String channel;
	private String sysVersion;
	private String appVersion;
	private String clientIp;
	private String sysLanguage;
	private String packageName;
	private String tt;
	private String clientId;
	private String clientAreaCode;
	private String token;
	private String uid;
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSysVersion() {
		return sysVersion;
	}
	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getSysLanguage() {
		return sysLanguage;
	}
	public void setSysLanguage(String sysLanguage) {
		this.sysLanguage = sysLanguage;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientAreaCode() {
		return clientAreaCode;
	}
	public void setClientAreaCode(String clientAreaCode) {
		this.clientAreaCode = clientAreaCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
