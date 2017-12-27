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
	 * 根据系统类型和海外标识获取渠道列表
	 * @param systemType 
	 * @param isAbroad
	 * @param appId
	 * @param packageName
	 * @return
	 */
	public List<PayChannel> findListByParams(String systemType, Integer isAbroad, String appId, String packageName);
}
