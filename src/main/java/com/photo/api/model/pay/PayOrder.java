package com.photo.api.model.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	private String payOrderId;
	private String userId;
	private BigDecimal amount;
	private Integer payType;
	private Integer payChannel;
	private String payClassify;
	private String paySystemType;
	private String groupId;
	private Integer status;
	private Date createTime;
	private String transactionId;
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}
	public String getPayClassify() {
		return payClassify;
	}
	public void setPayClassify(String payClassify) {
		this.payClassify = payClassify;
	}
	public String getPaySystemType() {
		return paySystemType;
	}
	public void setPaySystemType(String paySystemType) {
		this.paySystemType = paySystemType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
