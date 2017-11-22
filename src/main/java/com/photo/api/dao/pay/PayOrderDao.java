package com.photo.api.dao.pay;

import java.util.List;

import com.photo.api.dao.PageDao;
import com.photo.api.model.pay.PayOrder;

public interface PayOrderDao extends PageDao<PayOrder> {
	/**
	 * 根据订单号获取订单信息
	 * @param orderNo
	 * @return
	 */
	public PayOrder findById(String orderNo);
	/**
	 * 查询用户的所有订单
	 * @param userId
	 * @return
	 */
	public List<PayOrder> findListByUserId(String userId);
	/**
	 * 根据支付的产品ID获取所有的订单
	 * @param payId
	 * @return
	 */
	public List<PayOrder> findByPayId(String payId);
	/**
	 * 根据苹果的交易号获取订单信息
	 * @param transactionId
	 * @return
	 */
	public PayOrder findByTransactionId(String transactionId);

}
