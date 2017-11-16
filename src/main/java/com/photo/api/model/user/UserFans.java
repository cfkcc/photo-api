package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserFans implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static enum Status{
		No(0),					//不是粉丝
		Yes(1);					//是粉丝
		private int status;
		Status(int status) {
			this.status = status;
		}
		public int getStatus(){
			return status;
		}
	}
	private String fansId;
	private String userId;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	public String getFansId() {
		return fansId;
	}
	public void setFansId(String fansId) {
		this.fansId = fansId;
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
	@Override
	public String toString() {
		return "UserFans [fansId=" + fansId + ", userId=" + userId + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
