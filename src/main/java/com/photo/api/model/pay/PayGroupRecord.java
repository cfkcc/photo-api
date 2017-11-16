package com.photo.api.model.pay;

import java.io.Serializable;
import java.util.Date;

public class PayGroupRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	private String buyRecordId;
	private Integer status;
	private Date createTime;
	private String createUser;
	private String groupId;
	private Integer count;
	public String getBuyRecordId() {
		return buyRecordId;
	}
	public void setBuyRecordId(String buyRecordId) {
		this.buyRecordId = buyRecordId;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
