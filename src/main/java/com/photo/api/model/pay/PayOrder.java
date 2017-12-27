package com.photo.api.model.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 应用id
	 */
	private String appId;
	/**
	 * app包名
	 */
	private String packageName;
	private String userId;
	/**
	 * 商品id
	 */
	private String productId;
	private BigDecimal orderAmount;
	private String channelId;
	/**
	 * 订单描述
	 */
	private String orderDesc;
	/*
	 * 订单状态.0:待支付，1:支付中，2:支付成功，3:支付失败,4:支付取消
	 */
	private Integer orderState;
	/**
	 * 发货状态：-1 发货失败, 0 待发货, 1 发货中, 2 发货成功
	 */
	private Integer deliverState;
	/**
	 * 交易结果状态码
	 */
	private String payStatusCode;
	/**
	 * 交易结果状态名称
	 */
	private String payStatusMsg;
	/**
	 * 苹果交易号
	 */
	private String transactionId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 完成时间
	 */
	private Date endTime;
	/**
	 * 终端类型，android使用AND，IOS使用iOS
	 */
	private String systemType;
	/**
	 * 补单，0 未补单 1 已补单
	 */
	private Integer replenishStates;
	/**
	 * 补单描述
	 */
	private String replenishDesc;
	/**
	 * 公用回传参数
	 */
	private String passbackParams;
	/**
	 * 支付类型
	 */
	private Integer payType;		//1:google;2:weixin;3:zhifubao
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	public String getPayStatusCode() {
		return payStatusCode;
	}
	public void setPayStatusCode(String payStatusCode) {
		this.payStatusCode = payStatusCode;
	}
	public String getPayStatusMsg() {
		return payStatusMsg;
	}
	public void setPayStatusMsg(String payStatusMsg) {
		this.payStatusMsg = payStatusMsg;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	public Integer getReplenishStates() {
		return replenishStates;
	}
	public void setReplenishStates(Integer replenishStates) {
		this.replenishStates = replenishStates;
	}
	public String getReplenishDesc() {
		return replenishDesc;
	}
	public void setReplenishDesc(String replenishDesc) {
		this.replenishDesc = replenishDesc;
	}
	public String getPassbackParams() {
		return passbackParams;
	}
	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	@Override
	public String toString() {
		return "PayOrder [orderNo=" + orderNo + ", appId=" + appId + ", packageName=" + packageName + ", userId="
				+ userId + ", productId=" + productId + ", orderAmount=" + orderAmount + ", channelId=" + channelId
				+ ", orderDesc=" + orderDesc + ", orderState=" + orderState + ", deliverState=" + deliverState
				+ ", payStatusCode=" + payStatusCode + ", payStatusMsg=" + payStatusMsg + ", transactionId="
				+ transactionId + ", createTime=" + createTime + ", endTime=" + endTime + ", systemType=" + systemType
				+ ", replenishStates=" + replenishStates + ", replenishDesc=" + replenishDesc + ", passbackParams="
				+ passbackParams + "]";
	}
}
