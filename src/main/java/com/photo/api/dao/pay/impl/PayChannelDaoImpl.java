package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayChannelDao;
import com.photo.api.model.pay.PayChannel;

@Repository("payChannelDao")
public class PayChannelDaoImpl extends PageDaoAbstract<PayChannel> implements PayChannelDao {

}
