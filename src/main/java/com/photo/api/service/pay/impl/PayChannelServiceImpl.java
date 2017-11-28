package com.photo.api.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import com.photo.api.common.util.Page;
import com.photo.api.dao.pay.PayChannelDao;
import com.photo.api.model.pay.PayChannel;
import com.photo.api.service.pay.PayChannelService;

public class PayChannelServiceImpl implements PayChannelService {
	
	@Resource(name="payChannelDao")
	private PayChannelDao payChannelDao;

	
	public PayChannel findById(String cId) {
		return payChannelDao.findById(cId);
	}

	
	public Page findByPage(Page page) {
		return payChannelDao.findByPage(page);
	}

	
	public List<PayChannel> findList() {
		return payChannelDao.findAll();
	}

}
