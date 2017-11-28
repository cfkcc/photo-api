package com.photo.api.service.pay.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.dao.pay.ProductInfoDao;
import com.photo.api.model.pay.ProductInfo;
import com.photo.api.service.pay.ProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	
	@Resource(name="productInfoDao")
	private ProductInfoDao productInfoDao;

	
	public ProductInfo findById(String proId) {
		return productInfoDao.findById(proId);
	}

	
	public List<ProductInfo> findListByChannel(String channelId) {
		return productInfoDao.findListByChannel(channelId);
	}

	
	public Page findByPage(Page page) {
		return productInfoDao.findByPage(page);
	}

}
