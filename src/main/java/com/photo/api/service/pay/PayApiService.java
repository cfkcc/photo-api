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
	
}
