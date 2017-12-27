package com.photo.api.model.appinfo;

import java.util.Date;

public class AppInfo {
	/**
	 * 应用id
	 */
	private String appInfo;
	/**
	 * app管理员在BulePay申请，可修改。CP管理员可查看，不可修改。
	 */
	private String payId;
	/**
	 * 应用名称
	 */
	private String appname;
	/**
	 * cp的id,关联到当前登录的CP账号。
	 */
	private String cpId;
	/**
	 * 结果回调地址
	 */
	private String callUrl;
	/**
	 * 状态。0:待审核，1:审核成功,2:删除，3：审核失败
	 */
	private Integer status;
	/**
	 * apple状态。0:待审核，1:审核成功,2:删除，3：审核失败
	 */
	private Integer statusApple;
	/**
	 * gp状态。0:待审核，1:审核成功,2:删除，3：审核失败
	 */
	private Integer statusGp;
	/**
	 * 应用分类id
	 */
	private Integer appTypeId;
	/**
	 * 应用分类名称
	 */
	private String appTypeName;
	/**
	 * 应用简介
	 */
	private String descContent;
	/**
	 * 应用截图
	 */
	private String appScreen;
	/**
	 * 应用图标
	 */
	private String iconUrl;
	/**
	 * 宣传视频
	 */
	private String videoUrl;
	/**
	 * 宣传图片
	 */
	private String promoteImage;
	/**
	 * 首页推荐图
	 */
	private String recommendImage;
	/**
	 * 应用包名
	 */
	private String packageName;
	/**
	 * 下载地址
	 */
	private String downloadUrl;
	/**
	 * app大小字节数
	 */
	private Long appSize;
	/**
	 * facebook包名
	 */
	private String fbPackageName;
	/**
	 * facebook应用id
	 */
	private String fbAppId;
	/**
	 * facebook应用密钥
	 */
	private String fbAppKey;
	/**
	 * google应用id
	 */
	private String ggAppId;
	/**
	 * google应用密钥
	 */
	private String ggAppKey;
	/**
	 * 创建时间
	 */
	private Date createTime;
	public String getAppInfo() {
		return appInfo;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getCallUrl() {
		return callUrl;
	}
	public void setCallUrl(String callUrl) {
		this.callUrl = callUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatusApple() {
		return statusApple;
	}
	public void setStatusApple(Integer statusApple) {
		this.statusApple = statusApple;
	}
	public Integer getStatusGp() {
		return statusGp;
	}
	public void setStatusGp(Integer statusGp) {
		this.statusGp = statusGp;
	}
	public Integer getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}
	public String getAppTypeName() {
		return appTypeName;
	}
	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
	public String getDescContent() {
		return descContent;
	}
	public void setDescContent(String descContent) {
		this.descContent = descContent;
	}
	public String getAppScreen() {
		return appScreen;
	}
	public void setAppScreen(String appScreen) {
		this.appScreen = appScreen;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPromoteImage() {
		return promoteImage;
	}
	public void setPromoteImage(String promoteImage) {
		this.promoteImage = promoteImage;
	}
	public String getRecommendImage() {
		return recommendImage;
	}
	public void setRecommendImage(String recommendImage) {
		this.recommendImage = recommendImage;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public Long getAppSize() {
		return appSize;
	}
	public void setAppSize(Long appSize) {
		this.appSize = appSize;
	}
	public String getFbPackageName() {
		return fbPackageName;
	}
	public void setFbPackageName(String fbPackageName) {
		this.fbPackageName = fbPackageName;
	}
	public String getFbAppId() {
		return fbAppId;
	}
	public void setFbAppId(String fbAppId) {
		this.fbAppId = fbAppId;
	}
	public String getFbAppKey() {
		return fbAppKey;
	}
	public void setFbAppKey(String fbAppKey) {
		this.fbAppKey = fbAppKey;
	}
	public String getGgAppId() {
		return ggAppId;
	}
	public void setGgAppId(String ggAppId) {
		this.ggAppId = ggAppId;
	}
	public String getGgAppKey() {
		return ggAppKey;
	}
	public void setGgAppKey(String ggAppKey) {
		this.ggAppKey = ggAppKey;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "AppInfo [appInfo=" + appInfo + ", payId=" + payId + ", appname=" + appname + ", cpId=" + cpId
				+ ", callUrl=" + callUrl + ", status=" + status + ", statusApple=" + statusApple + ", statusGp="
				+ statusGp + ", appTypeId=" + appTypeId + ", appTypeName=" + appTypeName + ", descContent="
				+ descContent + ", appScreen=" + appScreen + ", iconUrl=" + iconUrl + ", videoUrl=" + videoUrl
				+ ", promoteImage=" + promoteImage + ", recommendImage=" + recommendImage + ", packageName="
				+ packageName + ", downloadUrl=" + downloadUrl + ", appSize=" + appSize + ", fbPackageName="
				+ fbPackageName + ", fbAppId=" + fbAppId + ", fbAppKey=" + fbAppKey + ", ggAppId=" + ggAppId
				+ ", ggAppKey=" + ggAppKey + ", createTime=" + createTime + "]";
	}
}
