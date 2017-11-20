package com.photo.api.dao.pay.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.ProductInfoDao;
import com.photo.api.model.pay.ProductInfo;

@Repository("productInfoDao")
public class ProductInfoDaoImpl extends PageDaoAbstract<ProductInfo> implements ProductInfoDao {

}
