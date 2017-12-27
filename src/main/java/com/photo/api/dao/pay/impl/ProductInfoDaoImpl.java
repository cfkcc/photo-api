package com.photo.api.dao.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<ProductInfo> findProductInfoList(String channelId,String appId, String packageName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("packageName", packageName);
		param.put("channelId", channelId);
		param.put("appId", appId);
		return (List<ProductInfo>) findList("findProductInfoList", param);
	}

	@Override
	public ProductInfo findByParams(String productId, String channelId, String appId, String packageName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("packageName", packageName);
		param.put("channelId", channelId);
		param.put("appId", appId);
		param.put("productId", productId);
		return findOne("findByParams", param);
	}

}
