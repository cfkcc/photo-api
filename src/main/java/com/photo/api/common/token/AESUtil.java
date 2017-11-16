package com.photo.api.common.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {

    static Logger log = LoggerFactory.getLogger(AESUtil.class);



    private static final String CIPHER_ALGORITHM ="AES/CBC/PKCS5Padding"; // 兼容客户端android，ios的加密算法

    private static final String CIPHER_KEY_STR = "X1O86gcFoBWhqPaJ"; // 兼容客户端android，ios的加密秘钥

    private static final String ENCRYPT_IVPARAMETER = "0392039203920300";//CBC模式，需要一个向量iv偏移量



    /**
     * 兼容加密算法加密
     * @param encData
     * @return
     */
    public static String encryptData(String encData){
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] raw = CIPHER_KEY_STR.getBytes();
            SecretKeySpec skeySpec  = new SecretKeySpec(raw, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ENCRYPT_IVPARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE,skeySpec,ivParameterSpec);
            byte[] bytes = cipher.doFinal(encData.getBytes("utf-8"));
            return Base64Utils.encodeToString(bytes);
        } catch (Exception e) {
           log.error(e.getMessage(),e);
        }
        return null;
    }

    public static String decryptData(String sStr){
        try{
            byte[] raw = CIPHER_KEY_STR.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ENCRYPT_IVPARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64Utils.decodeFromString(sStr);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
    

}
