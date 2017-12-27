package com.photo.api.service.pay;

import java.util.List;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.ProductInfo;

public interface ProductInfoService {
	/**
	 * 根据app包名和渠道及appId获取产品列表
	 * @param channelId 支付渠道
	 * @param appId app唯一识别码
	 * @param packageName app包名
	 * @return
	 */
	public List<ProductInfo> findProductInfoList(String channelId,String appId, String packageName);
	/**
	 * 根据产品Id获取产品信息
	 * @param productId
	 * @return
	 */
	public ProductInfo findById(String productId);
	/**
	 * 根据参数获取产品信息
	 * @param productId
	 * @param channelId
	 * @param appId
	 * @param packageName
	 * @return
	 */
	public ProductInfo findByParams(String productId, String channelId,String appId, String packageName);
	
	/**
	 * 分页获取产品
	 * @param page
	 * @return
	 */
	public Page findByPage(Page page);
	
}
 