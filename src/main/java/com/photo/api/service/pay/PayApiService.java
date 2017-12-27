package com.photo.api.service.pay;

import java.util.Map;

public interface PayApiService {
	/**
	 * 根据参数获取相应的产品列表
	 * @param appId
	 * @param packageName
	 * @param sysType
	 * @param isAbroad
	 * @param channelId
	 * @return
	 */
	public Map<String, Object> findProductList(Integer pageIndex, Integer pageSize, String appId, String packageName, String channelId);
	
	/**
	 * 获取支付所需的信息
	 * @param channelId
	 * @param userId
	 * @param productId
	 * @param appId
	 * @param packageName
	 * @return
	 */
	public Map<String, Object> pay(String channelId, String userId, String productId, String appId, String packageName);
	/**
	 * 获取支付渠道
	 * @param appId
	 * @param packageName
	 * @param systemType
	 * @param isAbroad
	 * @return
	 */
	public Map<String, Object> findChannelList(String appId, String packageName, String systemType, Integer isAbroad);
	/**
	 * 第三方接口回调
	 * @param payStatusCode 第三方发货情况
	 * @param callBackType 第三方类型: 1:QQ,2:微信,3:微博,4:facebook,5:twitter,6:google,7:line,8:instagram
	 * @param orderNo 支付流水订单号
	 * @return
	 */
	public void callBack(String payStatusCode, String transactionId, String orderDesc, String orderNo);
	/**
	 * 分页获取支付订单流水记录
	 * @param pageIndex 页码
	 * @param pageSize 页容量
	 * @param appId app id
	 * @param packageName app 包名
	 * @param uid 用户id
	 * @return
	 */
	public Map<String, Object> findPayRecords(Integer pageIndex, Integer pageSize, String appId, String packageName, String uid);

}
