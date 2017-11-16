package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserLike implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static enum Status{
		Unlike(0),					//未赞
		Like(1);					//已赞
		private int status;
		Status(int status) {
			this.status = status;
		}
		public int getStatus(){
			return status;
		}
	}

	private String likerId;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private String userId;
	public String getLikerId() {
		return likerId;
	}
	public void setLikerId(String likerId) {
		this.likerId = likerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserLike [likerId=" + likerId + ", status=" + status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", userId=" + userId + "]";
	}
}
