package com.photo.api.dao.pay;

import java.util.List;

import com.photo.api.dao.PageDao;
import com.photo.api.model.pay.ProductInfo;

public interface ProductInfoDao extends PageDao<ProductInfo> {
	/**
	 * 根据商品ID获取商品信息
	 * @param proId
	 * @return
	 */
	public ProductInfo findById(String proId);
	/**
	 * 根据渠道ID获取所有商品的信息
	 * @param channelId
	 * @return
	 */
	public List<ProductInfo>  findListByChannel(String channelId);

}
