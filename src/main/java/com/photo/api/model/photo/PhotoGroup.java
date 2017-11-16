package com.photo.api.model.photo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PhotoGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	private String groupId;
	private String userId;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	private BigDecimal coins;
	private Integer isHot;
	private Integer status;
	private String headPhotoId;
	private Integer flag;
	private Integer abroad;
	private Double sale;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getHeadPhotoId() {
		return headPhotoId;
	}
	public void setHeadPhotoId(String headPhotoId) {
		this.headPhotoId = headPhotoId;
	}
	public Integer getAbroad() {
		return abroad;
	}
	public void setAbroad(Integer abroad) {
		this.abroad = abroad;
	}
	public BigDecimal getCoins() {
		return coins;
	}
	public void setCoins(BigDecimal coins) {
		this.coins = coins;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	@Override
	public String toString() {
		return "PhotoGroup [groupId=" + groupId + ", userId=" + userId + ", createUser=" + createUser + ", createTime="
				+ createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", coins=" + coins
				+ ", isHot=" + isHot + ", status=" + status + ", headPhotoId=" + headPhotoId + ", flag=" + flag
				+ ", abroad=" + abroad + ", sale=" + sale + "]";
	}
}
