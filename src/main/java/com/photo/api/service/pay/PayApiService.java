package com.photo.api.service.pay;

import java.util.Map;

import com.photo.api.common.util.Page;

public interface PayApiService {
	/**
	 * 分页查询支付流水 
	 * @param page
	 * @return
	 */
	public Map<String, Object> findOrdersByPage(Page page);
	/**
	 * 购买金币产生流水
	 */
	public void addPayOrder();
	/**
	 * 根据渠道ID获取该渠道的产品信息
	 * @param channelId
	 * @return
	 */
	public Map<String, Object> findProductsByChannelId(String channelId);
	
}
