package com.photo.api.dao.pay.impl;

import java.util.List;

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
	public List<PayOrder> findByPayId(String payId) {
		return (List<PayOrder>) findList("findByPayId", payId);
	}

	@Override
	public PayOrder findByTransactionId(String transactionId) {
		return findOne("findByTransactionId", transactionId);
	}

}
