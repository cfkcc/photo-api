package com.photo.api.common.param;

import java.io.Serializable;

public class BaseParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String sysType;
	private String systemVersion;
	private String appVersion;
	private String packageName;
	private String tt;
	private String areaCode;
	private String token;
	private String uid;
	private String appId;
	@Override 
	public String toString() {
		return "BaseParam{" + "appId='" + appId + '\'' + ", sysType='" + sysType + '\'' + ", systemVersion='" + systemVersion
				+ '\'' + ", appVersion='" + appVersion + '\''
				+ ", packageName='" + packageName + '\'' + ", tt='" + tt + '\''
				+ ", areaCode='" + areaCode + '\'' + ", token='" + token + '\'' + ", uid='" + uid + '\'' + '}';
	}
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	

}
