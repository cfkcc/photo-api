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

	@Override
	public List<ProductInfo> findProductInfoList(String channelId,String appId, String packageName) {
		List<ProductInfo> productInfos = productInfoDao.findProductInfoList(channelId, appId, packageName);
        /*if (productInfos != null && !productInfos.isEmpty()) {
            Date now = new Date(), start = null, end = null;
            for (ProductInfo productInfo : productInfos) {
                start = productInfo.getDiscountStartTime();
                end = productInfo.getDiscountEndTime();
                if (start != null && end != null) {
                    productInfo.setDiscountable(start.before(now) && end.after(now));
                } else if (start != null) {
                    productInfo.setDiscountable(start.before(now));
                } else if (end != null) {
                    productInfo.setDiscountable(end.after(now));
                }
            }
        }*/
        return productInfos;
	}

	@Override
	public ProductInfo findById(String productId) {
		return productInfoDao.findById(productId);
	}

	@Override
	public Page findByPage(Page page) {
		return productInfoDao.findByPage(page);
	}

	@Override
	public ProductInfo findByParams(String productId, String channelId, String appId, String packageName) {
		return productInfoDao.findByParams(productId, channelId, appId, packageName);
	}

}
