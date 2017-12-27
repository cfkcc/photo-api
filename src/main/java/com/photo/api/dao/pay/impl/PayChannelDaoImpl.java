package com.photo.api.dao.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayChannelDao;
import com.photo.api.model.pay.PayChannel;

@Repository("payChannelDao")
public class PayChannelDaoImpl extends PageDaoAbstract<PayChannel> implements PayChannelDao {

	@Override
	public PayChannel findById(String channelId) {
		return findOne("findById", channelId);
	}
	
	@Override
	public List<PayChannel> findListByParams(String systemType, Integer isAbroad, String appId,
			String packageName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemType", systemType);
		params.put("isAbroad", isAbroad);
		params.put("appId", appId);
		params.put("packageName", packageName);
		return (List<PayChannel>) findList("findListByParams", params);
	}

}
