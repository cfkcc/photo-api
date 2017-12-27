package com.photo.api.common.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class SignRSAUtils {
	private static final String ALGORITHM = "RSA";

    private static final String SIGN_ALGORITHMS = "SHA256withRSA";
//    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return new String(Base64.encode(signed));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void main(String[] args) {
    	String privateKey = PayConfig.getString("alipay.rsa2.private");
    	String content = "{\"a\":\"123\"}";
		String sign = SignRSAUtils.sign(content, privateKey);
		System.out.println("sign = "+sign);
	}
}
