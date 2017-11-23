package com.photo.api.model.version;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable{

	private String versionId;
	private Integer status;
	private String versionCode;
	private String systemType;
	private String downloadUrl;
	private String description;
	private Integer isMust;
	private String md5;
	private String appType;
	private String packageName;
	private String channel;
	private String tips;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	private Integer flag;
	private Integer ban;
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsMust() {
		return isMust;
	}
	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getBan() {
		return ban;
	}
	public void setBan(Integer ban) {
		this.ban = ban;
	}
	@Override
	public String toString() {
		return "Version [versionId=" + versionId + ", status=" + status + ", versionCode=" + versionCode
				+ ", systemType=" + systemType + ", downloadUrl=" + downloadUrl + ", description=" + description
				+ ", isMust=" + isMust + ", md5=" + md5 + ", appType=" + appType + ", packageName=" + packageName
				+ ", channel=" + channel + ", tips=" + tips + ", createUser=" + createUser + ", createTime="
				+ createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", flag=" + flag + ", ban="
				+ ban + "]";
	}
	
}
