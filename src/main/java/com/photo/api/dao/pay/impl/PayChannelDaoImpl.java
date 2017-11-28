package com.photo.api.dao.pay.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayChannelDao;
import com.photo.api.model.pay.PayChannel;

@Repository("payChannelDao")
public class PayChannelDaoImpl extends PageDaoAbstract<PayChannel> implements PayChannelDao {

	
	public PayChannel findById(String channelId) {
		return findOne("findById", channelId);
	}

	
	public List<PayChannel> findAll() {
		return (List<PayChannel>) findList("findAll");
	}

	
	public PayChannel findByName(String channelName) {
		return findOne("findByName", channelName);
	}

}
