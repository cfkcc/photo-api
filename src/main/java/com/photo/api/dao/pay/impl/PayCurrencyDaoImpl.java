package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayCurrencyDao;
import com.photo.api.model.pay.PayCurrency;

@Repository("payCurrencyDao")
public class PayCurrencyDaoImpl extends PageDaoAbstract<PayCurrency> implements PayCurrencyDao {

}
