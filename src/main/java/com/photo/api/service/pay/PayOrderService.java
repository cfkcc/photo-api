package com.photo.api.service.pay;

import java.util.List;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.PayOrder;

public interface PayOrderService {
	/**
	 * 根据流水号查询流水
	 * @param orderNo
	 * @return
	 */
	public PayOrder findById(String orderNo);
	/**
	 * 分页查询流水记录
	 * @param page
	 * @return
	 */
	public Page findByPage(Page page);
	/**
	 * 获取所有的流水记录
	 * @param userId
	 * @return
	 */
	public List<PayOrder> findListByUserId(String userId);
	/**
	 * 添加流水记录
	 * @param payOrder
	 */
	public void addPayOrder(PayOrder payOrder);

}
