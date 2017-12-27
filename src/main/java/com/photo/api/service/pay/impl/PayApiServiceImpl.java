package com.photo.api.service.pay.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.enums.PayType;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.AppErrorException;
import com.photo.api.common.util.Arith;
import com.photo.api.common.util.GenerateCodeUtil;
import com.photo.api.common.util.Page;
import com.photo.api.model.pay.PayChannel;
import com.photo.api.model.pay.PayOrder;
import com.photo.api.model.pay.ProductInfo;
import com.photo.api.model.user.User;
import com.photo.api.service.account.UserService;
import com.photo.api.service.pay.PayApiService;
import com.photo.api.service.pay.PayChannelService;
import com.photo.api.service.pay.PayOrderService;
import com.photo.api.service.pay.ProductInfoService;

@Service("payApiService")
public class PayApiServiceImpl implements PayApiService {
	@Resource(name="productInfoService")
	private ProductInfoService productInfoService;
	@Resource(name="payChannelService")
	private PayChannelService payChannelService;
	@Resource(name="payOrderService")
	private PayOrderService payOrderService;
	@Resource(name="userService")
	private UserService userService;
	@Override
	public Map<String, Object> findProductList(Integer pageIndex, Integer pageSize, String appId, String packageName, String channelId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page page = new Page();
		page.setPageNo(pageIndex);
		page.setPageSize(pageSize);
		page.setOrderBy(" product_price asc");
		page.getParams().put("appId", appId);
		page.getParams().put("packageName", packageName);
		page.getParams().put("channelId", channelId);
		
		page = productInfoService.findByPage(page);
		result.put("totalCount", page.getRowCount());
		List<ProductInfo> productList = (List<ProductInfo>) page.getRecords();
		if (productList!=null && !productList.isEmpty()) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Iterator<ProductInfo> it = productList.iterator();
			while(it.hasNext()){
				ProductInfo p =it.next();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("productId", p.getProductId());
				map.put("title", p.getCoins()+"="+p.getCurrency()+p.getProductPrice());
				list.add(map);
			}
			result.put("list", list);
		}
		return result;
	}
	
	@Override
	public Map<String, Object> pay(String channelId, String userId, String productId, String appId,
			String packageName) {
		User u = userService.findUserById(userId);
		if (u == null) {
			throw new AppErrorException(ErrorCode.CODE_AUTH_ERROR.getCode(), ErrorCode.CODE_AUTH_ERROR.getEnMessage());
		}
		//1、检查该渠道的产品是否存在
		ProductInfo pi  =productInfoService.findByParams(productId, channelId, appId, packageName);
		Map<String, Object> result = new HashMap<String, Object>();
		if (pi != null) {
			//2、如果产品存在，便产生充值消费记录
			PayOrder po = new PayOrder();
			String orderNo = GenerateCodeUtil.createOrderNo();
			po.setOrderNo(orderNo);
			po.setUserId(userId);
			po.setAppId(appId);
			//计算支付金额
			double orderAmount = Arith.mul(pi.getProductPrice().doubleValue(),(1-pi.getDiscountRatio().doubleValue()),2);
			po.setOrderAmount(BigDecimal.valueOf(orderAmount));
			po.setPackageName(packageName);
			po.setCreateTime(new Date());
			po.setOrderState(0);
			po.setChannelId(channelId);
			po.setProductId(productId);
			payOrderService.addPayOrder(po);
			result.put("orderNo", orderNo);
		}
		return result;
	}
	@Override
	public Map<String, Object> findChannelList(String appId, String packageName, String systemType, Integer isAbroad) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<PayChannel> payChannelList = payChannelService.findListByParams(systemType, isAbroad, appId, packageName);
		Iterator<PayChannel> it = payChannelList.iterator();
		result.put("totalCount", payChannelList.size());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while(it.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			PayChannel pc = it.next();
			map.put("channelId", pc.getChannelId());
			map.put("channelName", pc.getChannelName());
			list.add(map);
		}
		if (!list.isEmpty()) {
			result.put("list", list);
		}
		return result;
	}
	@Override
	public void callBack(String payStatusCode, String transactionId, String orderDesc, String orderNo) {
		PayOrder po = payOrderService.findById(orderNo);
		if (po == null) {
			throw new AppErrorException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
		}
		po.setOrderState(2);
		po.setOrderDesc(orderDesc);
		po.setDeliverState(2);
		po.setPayType(3);
		po.setTransactionId(transactionId);
		po.setPayStatusCode(payStatusCode);
		po.setPayStatusMsg("");
		po.setEndTime(new Date());
		payOrderService.updatePayOrder(po);
		ProductInfo pi = productInfoService.findById(po.getProductId());
		User u = userService.findUserById(po.getUserId());
		if (pi == null) {
			throw new AppErrorException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
		}
		u.setCoins(BigDecimal.valueOf(u.getCoins().doubleValue()+pi.getCoins().doubleValue()));
		userService.updateUser(u);
	}
	@Override
	public Map<String, Object> findPayRecords(Integer pageIndex, Integer pageSize, String appId, String packageName,
			String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Page page = new Page();
		page.setPageNo(pageIndex);
		page.setPageSize(pageSize);
		page.setOrderBy(" create_time desc");
		
		page.getParams().put("appId", appId);
		page.getParams().put("packageName", packageName);
		page.getParams().put("userId", uid);
		page = payOrderService.findByPage(page);
		result.put("totalCount", page.getRowCount());
		List<PayOrder> payOrderList = (List<PayOrder>) page.getRecords();
		Iterator<PayOrder> it = payOrderList.iterator();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while(it.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			PayOrder po = it.next();
			map.put("orderNo", po.getOrderNo());
			map.put("orderStatus", po.getOrderState());
			map.put("orderAmount", po.getOrderAmount());
			map.put("createTime", po.getCreateTime());
			list.add(map);
		}
		if (!list.isEmpty()) {
			result.put("list", list);
		}
		return result;
	}
}
