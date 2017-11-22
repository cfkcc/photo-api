package com.photo.api.dao.pay.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.pay.ProductInfoDao;
import com.photo.api.model.pay.ProductInfo;

@Repository("productInfoDao")
public class ProductInfoDaoImpl extends PageDaoAbstract<ProductInfo> implements ProductInfoDao {

	@Override
	public ProductInfo findById(String productId) {
		return findOne("findById", productId);
	}

	@Override
	public List<ProductInfo> findListByChannel(String payChannelId) {
		return (List<ProductInfo>) findList("findListByChannel", payChannelId);
	}

}
