package com.photo.api.service.pay;

import java.util.List;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.PayOrder;

public interface PayOrderService {
	/**
     * addPayOrder:保存订单
     * @param payOrder
     * @return
     */
	public PayOrder addPayOrder(PayOrder payOrder);
	/**
	 * updatePayOrder:更新订单
	 * @param payOrder
	 * @return
	 */
	public PayOrder updatePayOrder(PayOrder payOrder);
	
	/**
	 * 根据参数获取支付
	 * @param appId
	 * @param packageName
	 * @param channelId
	 * @return
	 */
	public List<PayOrder> findPayOrderList(String appId, String packageName, String channelId);
	/**
	 * 分页查询支付账单
	 * @param page
	 * @return
	 */
	public Page findByPage(Page page);
	/**
	 * 根据账单流水号获取账单信息
	 * @param orderNo
	 * @return
	 */
	public PayOrder findById(String orderNo);

}
