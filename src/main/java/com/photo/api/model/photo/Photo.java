package com.photo.api.model.photo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Photo implements Serializable{
	private static final long serialVersionUID = 1L;
	public static enum Free{
		Yes(0),					//免费
		No(1);					//收费
		private int status;
		Free(int status) {
			this.status = status;
		}
		public int getStatus(){
			return status;
		}
	}
	private String photoId;
	private String imgUrl;
	private String groupId;
	private String userId;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	private BigDecimal coins;
	private Integer isHot;
	private Integer status;
	private Integer flag;
	private Integer sort;
	private Integer isFree;
	private Double discount;
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
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
	public BigDecimal getCoins() {
		return coins;
	}
	public void setCoins(BigDecimal coins) {
		this.coins = coins;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsFree() {
		return isFree;
	}
	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", imgUrl=" + imgUrl + ", groupId=" + groupId + ", userId=" + userId
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + ", coins=" + coins + ", isHot=" + isHot + ", status=" + status
				+ ", flag=" + flag + ", sort=" + sort + ", isFree=" + isFree + ", discount=" + discount + "]";
	}
}
