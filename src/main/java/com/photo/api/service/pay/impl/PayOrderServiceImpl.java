package com.photo.api.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import com.photo.api.common.util.Page;
import com.photo.api.dao.pay.PayOrderDao;
import com.photo.api.model.pay.PayOrder;
import com.photo.api.service.pay.PayOrderService;

public class PayOrderServiceImpl implements PayOrderService {
	
	@Resource(name="payOrderDao")
	private PayOrderDao payOrderDao;

	@Override
	public PayOrder findById(String orderNo) {
		return payOrderDao.findById(orderNo);
	}

	@Override
	public Page findByPage(Page page) {
		return payOrderDao.findByPage(page);
	}

	@Override
	public List<PayOrder> findListByUserId(String userId) {
		return payOrderDao.findListByUserId(userId);
	}

	@Override
	public void addPayOrder(PayOrder payOrder) {
		payOrderDao.add(payOrder);
	}

}
