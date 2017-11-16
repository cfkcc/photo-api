package com.photo.api.model.photo;

import java.io.Serializable;
import java.util.Date;

public class PhotoBuyRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	public static enum Status{
		No(0),					//未购买
		Yes(1);					//已购买
		private int status;
		Status(int status) {
			this.status = status;
		}
		public int getStatus(){
			return status;
		}
	}
	public static enum 	Choice{
		Single(0),					//单张购买
		Group(1);					//套图购买
		private int choice;
		Choice(int choice) {
			this.choice = choice;
		}
		public int getChoices(){
			return choice;
		}
	}
	private String photoId;
	private String userId;
	private Integer status;
	private Date createTime;
	private Integer choice;
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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
	public Integer getChoice() {
		return choice;
	}
	public void setChoice(Integer choice) {
		this.choice = choice;
	}
}
