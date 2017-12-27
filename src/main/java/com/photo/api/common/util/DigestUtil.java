package com.photo.api.common.util;

import org.apache.commons.lang.StringUtils;

public final class DigestUtil {
	/**
     * Creates a new instance of DigestUtil. 
     */
    private DigestUtil() {
        
    }
    
    /**
     * calcSHADigest:计算摘要
     * @author: Eric 
     * @param uid 用户id
     * @param token 令牌
     * @param code 随机码或时间戳
     * @param uri 请求url
     * @param params 请求参数
     * @return
     * @throws Exception
     */
    public static String calcSHADigest(String uid, String token, String code, String uri, String params) throws Exception {
        String encodeParams = Base64.encode(params.getBytes());
        int idx = -1;
        String uriLoc = (StringUtils.isNotBlank(uri) && (idx = uri.indexOf("?")) > -1) ? uri.substring(0, idx) : uri;
        String digest1 = SHAEncrypt.encodeSHA512(code, token, uid, encodeParams).toLowerCase();
        return SHAEncrypt.encodeSHA512(digest1, uriLoc).toLowerCase();
    }
    
    /**
     * compareSHADigest:对比摘要
     * @author: Eric 
     * @param uid 用户id
     * @param token 令牌
     * @param code 随机码或时间戳
     * @param uri 请求url
     * @param params 请求参数
     * @param authCode 请求摘要
     * @return
     * @throws Exception
     */
    public static boolean compareSHADigest(String uid, String token, String code, String uri, String params, String authCode) throws Exception {
        String digest = calcSHADigest(uid, token, code, uri, params);
        return digest.equals(authCode);
    }
    
    public static void main(String[] args) {
        String params = "{\"areaCode\":\"TH\",\"payChannel\":\"Google Play\"}", 
                uid = "5f3cbf98a71ef10a86566103556550", 
                token = "cd90e59baf0fad96c9515e262ee265dbc52cf881a31223722db10f989719ed76521be4b1d6c7060200d8cc9cba17311b", 
                tt = "b281aa8e-2c12-4b9d-9fbd-dc79e537646f", uri = "/game/product/getList.action";
        try {
            String digest = calcSHADigest(uid, token, tt, uri, params);
            System.out.println(digest);
            if (token.equals(digest)) {
				System.out.println("ok");
			}
            System.out.println("541e195e68144eb91bf0366ff4c4ad8a75ed1903e352ff0cdc0a0ad89537d7a1df8f7075d8a40e59addf0a8feba005880e1eb5bc426f9a83b769ede1f1f5b205");
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    /*	int idx = -1;
    	String uri = "/game/product/getList.action";
    	String uriLoc = (StringUtils.isNotBlank(uri) && (idx = uri.indexOf("?")) > -1) ? uri.substring(0, idx) : uri;
    	System.out.println("uriLoc = "+uriLoc);*/
    }
}
