package com.photo.api.service.pay;

import java.util.List;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.ProductInfo;

public interface ProductInfoService {
	
	public ProductInfo findById(String proId);
	
	public List<ProductInfo> findListByChannel(String channelId);
	
	public Page findByPage(Page page);

}
 