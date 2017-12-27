package com.photo.api.controller.pay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.internal.util.AlipaySignature;
import com.photo.api.annotation.LoginSign;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.param.CommParam;
import com.photo.api.common.param.PayParam;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.util.AlipayUtil;
import com.photo.api.controller.BaseController;
import com.photo.api.controller.accont.AccountController;
import com.photo.api.service.pay.PayApiService;

@RestController
@RequestMapping("pay")
public class PayCotroller extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(PayCotroller.class);
	@Resource(name="payApiService")
	private PayApiService payApiService;
	
	/**
     * 请求支付
     * @param request
     * @param param
     * @return
     */
	@LoginSign(isNeedLogin=true)
    @RequestMapping("pay")
//    @ResponseBody
    public Object pay(CommParam param) {
    	return new ResponseMessage<Map<String, Object>>(payApiService.pay(param.getChannelId(), param.getUid(), param.getProductId(), param.getAppId(), param.getPackageName()));
    }
    
    
    /**
     * 获取产品列表
     * @param param
     * @return
     */
	@LoginSign(isNeedLogin=false)
    @RequestMapping("productList")
//    @ResponseBody
    public Object productList(CommParam param) {
    	return new ResponseMessage<Map<String, Object>>(payApiService.findProductList(param.getPageIndex(), param.getPageSize(), param.getAppId(), param.getPackageName(), param.getChannelId()));
    }
    
    /**
     * 获取支付渠道
     * @param param
     * @return
     */
	@LoginSign(isNeedLogin=false)
    @RequestMapping("channels")
//    @ResponseBody
    public Object channels(CommParam param) {
    	return new ResponseMessage<Map<String, Object>>(payApiService.findChannelList(param.getAppId(), param.getPackageName(), param.getSysType(), param.getAbroad()));
    }
    
    /**
     * 获取支付记录
     * @param param
     * @return
     */
	
    @LoginSign(isNeedLogin=true)
    @RequestMapping("payRecords")
    public Object payRecords(CommParam param) {
    	return new ResponseMessage<Map<String, Object>>(payApiService.findPayRecords(param.getPageIndex(), param.getPageSize(), param.getAppId(), param.getPackageName(), param.getUid()));
    }
    
    /**
     * 支付宝回调接口
     * @param request
     * @return
     */
    @LoginSign(isNeedLogin=false)
    @RequestMapping(value = { "/payCallBack" }, method = { RequestMethod.POST })
    @ResponseBody
    public String payCallBack(HttpServletRequest request) throws Exception {
    	//获取支付宝POST过来反馈信息  
        Map<String,String> params = new HashMap<String,String>();  
        Map requestParams = request.getParameterMap();  
        logger.info("requestParams:"+requestParams.toString());  
        logger.info("params:"+params.toString());  
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {  
            String name = (String) iter.next();  
            String[] values = (String[]) requestParams.get(name);  
            String valueStr = "";  
            for (int i = 0; i < values.length; i++) {  
                valueStr = (i == values.length - 1) ? valueStr + values[i]  
                        : valueStr + values[i] + ",";  
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化  
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");  
            params.put(name, valueStr);  
        }
          
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//  
        //商户订单号 
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");  

        //支付宝交易号    
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");  
        if(request.getParameter("trade_status")==null){  
        	logger.info("fail");  
            return "fail";  
        }  
        //交易状态  
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");  
        params.put("trade_status", trade_status);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//  
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY,  "UTF-8", "RSA2");          //调用SDK验证签名
        if(signVerified){//验证成功  
            //////////////////////////////////////////////////////////////////////////////////////////  
            //请在这里加上商户的业务逻辑程序代码  

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——  
              
            if(trade_status.equals("TRADE_FINISHED")){  
                //判断该笔订单是否在商户网站中已经做过处理  
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序  
                    //如果有做过处理，不执行商户的业务程序  
                      
                //注意：  
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知  
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的  
            } else if (trade_status.equals("TRADE_SUCCESS")){  
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序  
                //如果有做过处理，不执行商户的业务程序  
                //保存交易记录  
            	String body = params.get("body").toString();
                  
                //注意：  
                //付款完成后，支付宝系统发送该交易状态通知  
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的 
            	payApiService.callBack(trade_status, trade_no, body, out_trade_no);
            	logger.info("recharge result from aliPay: ["+params.toString()+"]");  
            }  
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——  
            return "success";  
//          out.println("success"); //请不要修改或删除  
        }else{
        	//验证失败  
            return "fail";  
//          out.println("fail");  
        }  
    }  

}
