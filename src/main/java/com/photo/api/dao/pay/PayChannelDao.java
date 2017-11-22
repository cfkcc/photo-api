package com.photo.api.dao.pay;

import java.util.List;

import com.photo.api.dao.PageDao;
import com.photo.api.model.pay.PayChannel;

public interface PayChannelDao extends PageDao<PayChannel> {
	/**
	 * 根据渠道ID获取渠道信息
	 * @param channelId
	 * @return
	 */
	public PayChannel findById(String channelId);
	/**
	 * 根据渠道名称获取渠道信息
	 * @param channelName
	 * @return
	 */
	public PayChannel findByName(String channelName);
	/**
	 * 获取所有的渠道
	 * @return
	 */
	public List<PayChannel> findAll();
}
