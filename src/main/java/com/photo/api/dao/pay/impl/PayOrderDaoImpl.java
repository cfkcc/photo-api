package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayOrderDao;
import com.photo.api.model.pay.PayOrder;

@Repository("payOrderDao")
public class PayOrderDaoImpl extends PageDaoAbstract<PayOrder> implements PayOrderDao {

}
