package com.photo.api.model.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String userId;
	private String payId;
	private String productId;
	private BigDecimal orderAmount;
	private String payChannelId;
	private String orderDesc;
	private Integer orderState;
	private Integer deliverState;
	private String operator;
	private Integer payStatusCode;
	private String payStatusMsg;
	private String traansactionId;
	private BigDecimal price;
	private String currency;
	private Date createTime;
	private Date endTime;
	private String systemType;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPayChannelId() {
		return payChannelId;
	}
	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getDeliverState() {
		return deliverState;
	}
	public void setDeliverState(Integer deliverState) {
		this.deliverState = deliverState;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getPayStatusCode() {
		return payStatusCode;
	}
	public void setPayStatusCode(Integer payStatusCode) {
		this.payStatusCode = payStatusCode;
	}
	public String getPayStatusMsg() {
		return payStatusMsg;
	}
	public void setPayStatusMsg(String payStatusMsg) {
		this.payStatusMsg = payStatusMsg;
	}
	public String getTraansactionId() {
		return traansactionId;
	}
	public void setTraansactionId(String traansactionId) {
		this.traansactionId = traansactionId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	@Override
	public String toString() {
		return "PayOrder [orderNo=" + orderNo + ", userId=" + userId + ", payId=" + payId + ", productId=" + productId
				+ ", orderAmount=" + orderAmount + ", payChannelId=" + payChannelId + ", orderDesc=" + orderDesc
				+ ", orderState=" + orderState + ", deliverState=" + deliverState + ", operator=" + operator
				+ ", payStatusCode=" + payStatusCode + ", payStatusMsg=" + payStatusMsg + ", traansactionId="
				+ traansactionId + ", price=" + price + ", currency=" + currency + ", createTime=" + createTime
				+ ", endTime=" + endTime + ", systemType=" + systemType + "]";
	}
	
}
