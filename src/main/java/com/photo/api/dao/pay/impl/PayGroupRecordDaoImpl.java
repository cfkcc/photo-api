package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayGroupRecordDao;
import com.photo.api.model.pay.PayGroupRecord;

@Repository("payGroupRecordDao")
public class PayGroupRecordDaoImpl extends PageDaoAbstract<PayGroupRecord> implements PayGroupRecordDao {

}
