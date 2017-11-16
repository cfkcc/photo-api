package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.PayGroupDao;
import com.photo.api.model.pay.PayGroup;

@Repository("payGroupDao")
public class PayGroupDaoImpl extends PageDaoAbstract<PayGroup> implements PayGroupDao {

}
