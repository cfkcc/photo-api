package com.photo.api.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PayUtil {
	/**
     * 生成订单号
     * 
     * @return
     */
    public static String getTradeNo() {
        // 自增8位数 00000001
        return "TNO" + DateUtil.dates2String(new Date()) + "00000001";
    }

    /**
     * 退款单号
     * 
     * @return
     */
    public static String getRefundNo() {
        // 自增8位数 00000001
        return "RNO" + DateUtil.dates2String(new Date()) + "00000001";
    }

    /**
     * 退款单号
     * 
     * @return
     */
    public static String getTransferNo() {
        // 自增8位数 00000001
        return "TNO" + DateUtil.dates2String(new Date()) + "00000001";
    }

    /**
     * 返回客户端ip
     * 
     * @param request
     * @return
     */
    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取服务器的ip地址
     * 
     * @param request
     * @return
     */
    public static String getLocalIp(HttpServletRequest request) {
        return request.getLocalAddr();
    }

    /**
     * 创建支付随机字符串
     * 
     * @return
     */
    public static String getNonceStr() {
        return CommonUtil.getRandomString(32, 1);
    }

    /**
     * 支付时间戳
     * 
     * @return
     */
    public static String payTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 返回签名编码拼接url
     * 
     * @param params
     * @param isEncode
     * @return
     */
    public static String getSignEncodeUrl(Map<String, String> map, boolean isEncode) {
        String sign = map.get("sign");
        String encodedSign = "";
        if (!map.isEmpty()) {
            map.remove("sign");
            List<String> keys = new ArrayList<String>(map.keySet());
            // key排序
            Collections.sort(keys);

            StringBuilder authInfo = new StringBuilder();

            boolean first = true;// 是否第一个
            for (String key: keys) {
                if (first) {
                    first = false;
                } else {
                    authInfo.append("&");
                }
                authInfo.append(key).append("=");
                if (isEncode) {
                    try {
                        authInfo.append(URLEncoder.encode(map.get(key), AlipayConstants.CHARSET_UTF8));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    authInfo.append(map.get(key));
                }
            }

            try {
                encodedSign = authInfo.toString() + "&sign=" + URLEncoder.encode(sign, AlipayConstants.CHARSET_UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return encodedSign.replaceAll("\\+", "%20");
    }

    /**
     * 对支付参数信息进行签名
     * 
     * @param map
     *            待签名授权信息
     * 
     * @return
     * @throws UnsupportedEncodingException 
     * @throws SignatureException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public static String getSign(Map<String, String> map, String rsaKey) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        boolean first = true;
        for (String key : keys) {
            if (first) {
                first = false;
            } else {
                authInfo.append("&");
            }
            authInfo.append(key).append("=").append(map.get(key)); 
        }

        return SignRSAUtils.sign(authInfo.toString(), rsaKey);
    }
    
  /*  public static void main(String[] args) {
//    	app_id=2014072300007148&biz_content={"button":[{"actionParam":"ZFB_HFCZ","actionType":"out","name":"话费充值"},{"name":"查询","subButton":[{"actionParam":"ZFB_YECX","actionType":"out","name":"余额查询"},{"actionParam":"ZFB_LLCX","actionType":"out","name":"流量查询"},{"actionParam":"ZFB_HFCX","actionType":"out","name":"话费查询"}]},{"actionParam":"http://m.alipay.com","actionType":"link","name":"最新优惠"}]}&charset=GBK&method=alipay.mobile.public.menu.add&sign_type=RSA2&timestamp=2014-07-24 03:07:50&version=1.0
    	Map<String, String> map = new HashMap<String, String>();
    	JSONArray ja = new JSONArray();
    	JSONObject json1 = new JSONObject();
    	json1.put("actionParam", "ZFB_HFCZ");
    	json1.put("actionType", "out");
    	json1.put("name", "话费充值");
    	ja.add(json1);
    	
    	JSONObject json3 = new JSONObject();
    	json3.put("name", "查询");
    	JSONArray ja2 = new JSONArray();
    	JSONObject json4 = new JSONObject();
    	json4.put("actionParam", "ZFB_YECX");
    	json4.put("actionType", "out");
    	json4.put("name", "余额查询");
    	ja2.add(json4);
    	JSONObject json5 = new JSONObject();
    	json5.put("actionParam", "ZFB_LLCX");
    	json5.put("actionType", "out");
    	json5.put("name", "流量查询");
    	ja2.add(json5);
    	JSONObject json6 = new JSONObject();
    	json6.put("actionParam", "ZFB_HFCX");
    	json6.put("actionType", "out");
    	json6.put("name", "话费查询");
    	ja2.add(json6);
    	json3.put("subButton", ja2);
    	ja.add(json3);
    	
    	JSONObject json2 = new JSONObject();
    	json2.put("actionParam", "http://m.alipay.com");
    	json2.put("actionType", "link");
    	json2.put("name", "最新优惠");
    	ja.add(json2);
    	
    	
    	JSONObject json7 = new JSONObject();
    	json7.put("button", ja.toString());
    	
    	map.put("app_id", "2014072300007148");
    	map.put("biz_content", json7.toString());
    	map.put("charset", "GBK");
//    	map.put("format", "json");
    	map.put("method", "alipay.mobile.public.menu.add");
    	map.put("sign_type", "RSA2");
    	map.put("timestamp", "2014-07-24 03:07:50");
    	map.put("version", "1.0");
    	String sign = PayUtil.getSign(map, AlipayUtil.APP_PRIVATE_KEY);
    	map.put("sign", sign);
    	System.out.println("sign = "+sign);
    	String orderStr = PayUtil.getSignEncodeUrl(map, true);
    	System.out.println("orderStr = "+orderStr);
    	
	}*/
}
