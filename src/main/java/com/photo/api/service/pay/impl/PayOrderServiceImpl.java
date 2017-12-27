package com.photo.api.service.pay.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.photo.api.common.enums.DataBaseStatusType;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.AppErrorException;
import com.photo.api.common.util.GenerateCodeUtil;
import com.photo.api.common.util.Page;
import com.photo.api.dao.pay.PayOrderDao;
import com.photo.api.model.pay.PayOrder;
import com.photo.api.model.pay.ProductInfo;
import com.photo.api.service.appinfo.AppInfoService;
import com.photo.api.service.pay.PayOrderService;
import com.photo.api.service.pay.ProductInfoService;

@Service("payOrderService")
public class PayOrderServiceImpl implements PayOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderServiceImpl.class);
	
	@Resource(name="payOrderDao")
	private PayOrderDao payOrderDao;
	@Resource(name="productInfoService")
	private ProductInfoService productInfoService;
	@Resource(name="appInfoService")
	private AppInfoService appInfoService;

	@Override
	public PayOrder addPayOrder(PayOrder payOrder) {
		 if (payOrder == null) {
	            throw new AppErrorException(ErrorCode.CODE_PARAM_ERROR.getCode(), ErrorCode.CODE_PARAM_ERROR.getEnMessage());
	        }
	        if (StringUtils.isBlank(payOrder.getProductId())) {
	            throw new AppErrorException(ErrorCode.CODE_PARAM_ERROR.getCode(), ErrorCode.CODE_PARAM_ERROR.getEnMessage());
	        }
	        ProductInfo productInfo = productInfoService.findById(payOrder.getProductId());
	        if (productInfo == null) {
	            LOGGER.warn("productId:{} not exists", payOrder.getProductId());
	            throw new AppErrorException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
	        }
	        String orderNo = GenerateCodeUtil.createOrderNo();
	        payOrder.setOrderNo(orderNo);
	        payOrder.setCreateTime(new Date());
	        //创建订单时，表示产品处于支付中
	        payOrder.setOrderState(DataBaseStatusType.USING.getStatus());
	        //没有支付成功，产品处于待支付状态
	        payOrder.setDeliverState(DataBaseStatusType.NO_USING.getStatus());
	        LOGGER.info("save payOrder {}", payOrder);
	        add(payOrder);
	        return payOrder;
	}

	/*@Override
	public PayOrder updateByAppleCallback(PayOrder payOrder) {
		LOGGER.info("apple callback params: {}", payOrder);
        PayOrder queryOrder = findByOrderNo(payOrder.getOrderNo());
        if (queryOrder == null) {
            throw new ProcessException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
        }
        String payChannel = queryOrder.getPayChannel();
        String transactionId = payOrder.getTransactionId();
        if ("Google Play".equalsIgnoreCase(payChannel)
                && (StringUtils.isBlank(transactionId) || !transactionId.matches("^GPA\\.\\d{4}-\\d{4}-\\d{4}-\\d{5}$"))
                && !String.valueOf(OrderStateEnum.PAY_FAILED.getStateCode()).equals(payOrder.getOrderState())) { // Google Play 流水号校验
            throw new ProcessException(ErrorCode.INVOKE_SERVICE_ERROR.getCode(), ErrorCode.INVOKE_SERVICE_ERROR.getEnMessage());
        }
        int status = queryOrder.getOrderState();
        if (OrderStateEnum.PAYMENT_IN.getStateCode() == status || OrderStateEnum.WAIT_PAY.getStateCode() == status) {
            int updateStatus = payOrder.getOrderState();
            int orderState = OrderStateEnum.WAIT_PAY.getStateCode();
            boolean sendCpFlag = false;
            if (updateStatus == OrderStateEnum.PAY_SUCCESS.getStateCode()) {
                orderState = BluePayStatusCodeEnum.CODE_200.getCode();
                sendCpFlag = true;
            } else if (updateStatus == OrderStateEnum.PAYMENT_IN.getStateCode()) {
                orderState = BluePayStatusCodeEnum.CODE_201.getCode();
            } else if (updateStatus == OrderStateEnum.PAY_FAILED.getStateCode()) {
                orderState = BluePayStatusCodeEnum.CODE_202.getCode();
                payOrder.setEndTime(new Date());
            } else if (updateStatus == OrderStateEnum.PAY_CANCEL.getStateCode()) {
                orderState = BluePayStatusCodeEnum.CODE_203.getCode();
                payOrder.setEndTime(new Date());
            }
            if (StringUtils.isBlank(payOrder.getChargeType())) {
                payOrder.setChargeType(payChannel);
            }
            if (StringUtils.isBlank(payOrder.getOperator())) {
                payOrder.setOperator(payChannel);
            }
            payOrder.setOrderState(updateStatus);
            payOrder.setTransactionId(payOrder.getTransactionId());
            if (sendCpFlag) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", orderState);
                map.put("msg", BluePayStatusCodeEnum.getMsg(orderState));
                map.put("appId", queryOrder.getAppId());
                map.put("userId", queryOrder.getUserId());
                map.put("orderId", payOrder.getOrderNo());
                map.put("price", payOrder.getPrice());
                map.put("payChannel", queryOrder.getPayChannel());
                map.put("currency", payOrder.getCurrency());
                map.put("cpInfo", queryOrder.getCpInfo());
                map.put("productId", queryOrder.getProductId());
                AppInfo appInfo = appInfoService.findById(queryOrder.getAppId());
                LOGGER.info("-------------------回调参数发送到cp开始-------------------------");
                String callResult = callbackCp(appInfo.getCallUrl(), map, 2);
                if (CpStateEnum.CP_SUCCESS.getCode().equals(callResult)) {
                    payOrder.setEndTime(new Date());
                    payOrder.setDeliverState(DeliverStateEnum.DELIVER_SUCCESS.getStateCode());
                } else if (CpStateEnum.CP_PARAM_ERROR.getCode().equals(callResult)) {
                    payOrder.setDeliverState(DeliverStateEnum.DELIVER_FAILED.getStateCode());
                }
            }
            update(payOrder);
            return findByOrderNo(payOrder.getOrderNo());
        }
        return queryOrder;
	}
	
	private String callbackCp(String callbackUrl, Map<String, Object> params, int retryTimes) {
        LOGGER.info("callback cp params: {}", params);
        retryTimes = retryTimes < 0 ? 0 : retryTimes;
        String result = null;
        for (int i = 0; i <= retryTimes; i++) {
            LOGGER.info("-------------------回调参数发送到cp开始 第{}次回调-------------------------", i + 1);
            try {
                String callback = HttpClientUtils.httpPostRequest(callbackUrl, params);
                LOGGER.info("callback cp result------>" + callback);
                LOGGER.info("-------------------回调参数发送到cp结束 第{}次回调-------------------------", i + 1);
                JSONObject jsonObject = JSONObject.parseObject(callback);
                result = jsonObject.get("code").toString();
                return result;
            } catch (Exception e) {
                LOGGER.error("CP第{}次回调失败", i + 1);
            }
        }
        return result;
    }

	@Override
	public PayOrder updateByBluepayCallback(PayRecord payRecord) {
		LOGGER.info("bluepay callback params: {}", payRecord);
        String t_id = payRecord.gettId();
        PayOrder payOrder = findByOrderNo(t_id);
        if (payOrder == null) {
            throw new ProcessException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
        }
        int status = payOrder.getOrderState();
        if (OrderStateEnum.PAYMENT_IN.getStateCode() == status || OrderStateEnum.WAIT_PAY.getStateCode() == status) {
            payRecord.setCreateTime(new Date());
            payRecordService.add(payRecord);
            PayOrder updateOrder = new PayOrder();
            int updateStatus = payRecord.getStatus();
            int orderState = OrderStateEnum.WAIT_PAY.getStateCode();
            boolean sendCpFlag = false;
            if (updateStatus == BluePayStatusCodeEnum.CODE_200.getCode()) {
                orderState = OrderStateEnum.PAY_SUCCESS.getStateCode();
                sendCpFlag = true;
            } else if (updateStatus == BluePayStatusCodeEnum.CODE_201.getCode()) {
                orderState = OrderStateEnum.PAYMENT_IN.getStateCode();
            } else {
                orderState = OrderStateEnum.PAY_FAILED.getStateCode();
                updateOrder.setEndTime(new Date());
            }
            updateOrder.setOrderNo(payOrder.getOrderNo());
            updateOrder.setOrderState(orderState);
            updateOrder.setOperator(payRecord.getOperator());
            updateOrder.setPayStatusCode(updateStatus);
            updateOrder.setPayStatusMsg(BluePayStatusCodeEnum.getMsg(updateStatus));
            BigDecimal price = BigDecimal.ZERO;
            if (Consts.CURRENCY_THB.equalsIgnoreCase(payRecord.getCurrency())) {
            	price = BigDecimal.valueOf(Arith.div(payRecord.getPrice().doubleValue(), 100d));
            } else {
            	price = BigDecimal.valueOf(payRecord.getPrice().doubleValue());
            }
            updateOrder.setPrice(price);
            updateOrder.setChargeType(payRecord.getInterfaceType());
            updateOrder.setCurrency(payRecord.getCurrency());
            updateOrder.setTransactionId(payRecord.getBtId());
            updateOrder.setPayId(payRecord.getProductId());
            
            // 根据pay_channel、currency、price查询商品
            ProductInfo conProductInfo = new ProductInfo();
            conProductInfo.setPayChannel(payOrder.getPayChannel());
            conProductInfo.setCurrency(payRecord.getCurrency());
            conProductInfo.setProductPrice(updateOrder.getPrice());
            conProductInfo.setAppId(payOrder.getAppId());
            LOGGER.info(
                    "query productInfo condition payChannel: {}, currency: {}, price: {}, appId: {}",
                    payOrder.getPayChannel(),
                    payRecord.getCurrency(),
                    updateOrder.getPrice(),
                    payOrder.getAppId());
            ProductInfo queryProductInfo = productInfoService.findByParams(payOrder.getPayChannel(),payRecord.getCurrency(),updateOrder.getPrice(),payOrder.getAppId());
            if (queryProductInfo != null) {
                updateOrder.setProductId(queryProductInfo.getProductId());
            } else {
            	update(updateOrder);
                return findByOrderNo(payOrder.getOrderNo());
            }
            if (sendCpFlag) {
                LOGGER.info("-------------------回调参数发送到cp开始-------------------------");
                AppInfo appInfo = appInfoService.findById(payOrder.getAppId());
                if (appInfo != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", updateStatus);
                    map.put("msg", updateOrder.getPayStatusMsg());
                    map.put("appId", payOrder.getAppId());
                    map.put("userId", payOrder.getUserId());
                    map.put("orderId", payOrder.getOrderNo());
                    map.put("price", updateOrder.getPrice());
                    map.put("payChannel", payOrder.getPayChannel());
                    map.put("currency", updateOrder.getCurrency());
                    map.put("cpInfo", payOrder.getCpInfo());
                    map.put("productId", updateOrder.getProductId());
                    String callResult = callbackCp(appInfo.getCallUrl(), map, 2);
                    if (CpStateEnum.CP_SUCCESS.getCode().equals(callResult)) {
                        updateOrder.setEndTime(new Date());
                        updateOrder.setDeliverState(DeliverStateEnum.DELIVER_SUCCESS.getStateCode());
                    } else if (CpStateEnum.CP_PARAM_ERROR.getCode().equals(callResult)) {
                        updateOrder.setDeliverState(DeliverStateEnum.DELIVER_FAILED.getStateCode());
                    }
                }
            }
            // 更新订单状态
            update(updateOrder);
            return findByOrderNo(updateOrder.getOrderNo());
        }
        return payOrder;
	}*/
	
	private void add(PayOrder payOrder){
		payOrderDao.add(payOrder);
	}
	@Override
	public PayOrder findById(String orderNo){
		return payOrderDao.findById(orderNo);
	}
	
	private void update(PayOrder payOrder){
		payOrderDao.update(payOrder);
	}

	@Override
	public List<PayOrder> findPayOrderList(String appId, String packageName, String channelId) {
		return payOrderDao.findPayOrderList(appId, packageName, channelId);
	}

	@Override
	public Page findByPage(Page page) {
		return payOrderDao.findByPage(page);
	}

	@Override
	public PayOrder updatePayOrder(PayOrder payOrder) {
		PayOrder po  = this.findById(payOrder.getOrderNo());
		if (po == null) {
			throw new AppErrorException(ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getCode(), ErrorCode.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getEnMessage());
		}
		po.setOrderState(payOrder.getOrderState());
		po.setOrderDesc(payOrder.getOrderDesc());
		po.setDeliverState(payOrder.getDeliverState());
		po.setPayStatusCode(payOrder.getPayStatusCode());
		po.setPayStatusMsg(payOrder.getPayStatusMsg());
		po.setTransactionId(payOrder.getTransactionId());
		this.update(po);
		return po;
	}
}
