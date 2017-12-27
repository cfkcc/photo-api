package com.photo.api.model.pay;

import java.io.Serializable;
import java.util.Date;

public class PayChannel implements Serializable{
	
	private String channelId;
	private String channelName;
	private Integer isAbroad;
	private Date createTime;
	private Date updateTime;
	private String createUser;
	private String updateUser;
	private Integer systemType;
	private String appId;
	private String packageName;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Integer getIsAbroad() {
		return isAbroad;
	}
	public void setIsAbroad(Integer isAbroad) {
		this.isAbroad = isAbroad;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getSystemType() {
		return systemType;
	}
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	@Override
	public String toString() {
		return "PayChannel [channelId=" + channelId + ", channelName=" + channelName + ", isAbroad=" + isAbroad
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUser=" + createUser
				+ ", updateUser=" + updateUser + ", systemType=" + systemType + ", appId=" + appId + ", packageName="
				+ packageName + "]";
	}
}
