package com.photo.api.dao.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayOrderDao;
import com.photo.api.model.pay.PayOrder;

@Repository("payOrderDao")
public class PayOrderDaoImpl extends PageDaoAbstract<PayOrder> implements PayOrderDao {

	@Override
	public PayOrder findById(String orderNo) {
		return findOne("findById", orderNo);
	}

	@Override
	public List<PayOrder> findListByUserId(String userId) {
		return (List<PayOrder>) findList("findListByUserId", userId);
	}

	@Override
	public PayOrder findByTransactionId(String transactionId) {
		return findOne("findByTransactionId", transactionId);
	}

	@Override
	public List<PayOrder> findPayOrderList(String appId, String packageName, String channelId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("packageName", packageName);
		params.put("channelId", channelId);
		return (List<PayOrder>) findList("findPayOrderList", params);
	}

}
