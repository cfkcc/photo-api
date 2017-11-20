package com.photo.api.model.pay;

import java.io.Serializable;
import java.util.Date;

public class PayChannel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String channelId;
	private String channelName;
	private String channelDesc;
	private Date createTime;
	private Date updateTime;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelDesc() {
		return channelDesc;
	}
	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
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
		return "PayChannel [channelId=" + channelId + ", channelName=" + channelName + ", channelDesc=" + channelDesc
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
