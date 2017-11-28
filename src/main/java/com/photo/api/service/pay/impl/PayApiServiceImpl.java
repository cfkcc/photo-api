package com.photo.api.service.pay.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.ProductInfo;
import com.photo.api.service.pay.PayApiService;
import com.photo.api.service.pay.ProductInfoService;

@Service("payApiService")
public class PayApiServiceImpl implements PayApiService {
	
	@Resource(name="productInfoService")
	private ProductInfoService productInfoService;

	
	public Map<String, Object> findOrdersByPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void addPayOrder() {
		// TODO Auto-generated method stub
		
	}

	
	public Map<String, Object> findProductsByChannelId(String channelId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ProductInfo> productList = productInfoService.findListByChannel(channelId);
		if (productList!=null && !productList.isEmpty()) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Iterator<ProductInfo> it = productList.iterator();
			while(it.hasNext()){
				ProductInfo p =it.next();
				Map<String, Object> map = new HashMap<String, Object>();
//				result.put("price", p.getProductPrice());
//				result.put("coins", p.getCoins());
				map.put("productId", p.getProductId());
//				result.put("currency", p.getCurrency());
				map.put("title", p.getCoins()+"="+p.getCurrency()+p.getProductPrice());
				list.add(map);
			}
			result.put("list", list);
		}
		return result;
	}

}
