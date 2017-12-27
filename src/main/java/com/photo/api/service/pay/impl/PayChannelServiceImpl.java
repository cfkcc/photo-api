package com.photo.api.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.pay.PayChannelDao;
import com.photo.api.model.pay.PayChannel;
import com.photo.api.service.pay.PayChannelService;

@Service("payChannelService")
public class PayChannelServiceImpl implements PayChannelService {
	
	@Resource(name="payChannelDao")
	private PayChannelDao payChannelDao;

	@Override
	public List<PayChannel> findListByParams(String systemType, Integer isAbroad, String appId,
			String packageName) {
		return payChannelDao.findListByParams(systemType, isAbroad, appId, packageName);
	}

	@Override
	public PayChannel findById(String channelId) {
		return payChannelDao.findById(channelId);
	}

	
}
