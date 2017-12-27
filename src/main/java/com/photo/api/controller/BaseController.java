package com.photo.api.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.AppErrorException;
import com.photo.api.common.util.SystemConfig;

public class BaseController {
    protected HttpServletRequest request;
	protected static final String aesKey = SystemConfig.getString("http.aes.key");
	protected static final String aesIv = SystemConfig.getString("http.aes.iv");

/*    protected String getRequestUserId(){
        Object attribute = request.getAttribute("uid");
        if(attribute != null){
            return String.valueOf(attribute);
        }
        return null;
    }*/


    protected String getClientIp(){
        String clientIp = "";
        if(request != null){
            clientIp = request.getHeader("X-Real-IP");
            if(StringUtils.isNoneBlank(clientIp) && !"unknown".equalsIgnoreCase(clientIp)){
                return clientIp;
            }
            clientIp = request.getHeader("X-Forwarded-For");
            if(StringUtils.isNoneBlank(clientIp) && !"unknown".equalsIgnoreCase(clientIp)){
                int index = clientIp.indexOf(",");
                if(index != -1){
                    return clientIp.substring(0,index);
                }else{
                    return clientIp;
                }
            }else{
                return request.getRemoteAddr();
            }
        }
        return clientIp;
    }
    
    /**
	 * decrypt data by aes key
	 * 
	 * @param data
	 * @return
	 * @throws AppErrorException
	 */
	public String decryptData(String data) throws AppErrorException {
		String dataDecrypt = data;
		try {
			// decrypt data
			Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
			dataDecrypt = new String(cipher.doFinal(Hex.decodeHex(dataDecrypt
					.toCharArray())), "UTF-8");
//			log.debug("BaseAction[getCipher] aes key decode= " + dataDecrypt);
		} catch (Exception ex) {
			if (ex instanceof AppErrorException) {
				throw (AppErrorException) ex;
			} else {
//				log.error("BaseAction[decryptData] error:", ex);
				throw new AppErrorException(ErrorCode.CODE_AES_ERROR.getCode(),ErrorCode.CODE_AES_ERROR.getMessage());
			}
		}
		return dataDecrypt;
	}
	
	/**
	 * param type : Cipher.DECRYPT_MODE or Cipher.ENCRYPT_MODE
	 * 
	 * @param type
	 * @return
	 * @throws AppErrorException
	 */
	protected Cipher getCipher(int type) throws AppErrorException {
		try {

			// set key to SecretKeySpec
			byte[] aesKeyByte = Hex.decodeHex(aesKey.toCharArray());
			
			SecretKeySpec key = new SecretKeySpec(aesKeyByte, "AES");

			// set iv to IvParameterSpec
			byte[] aesKeyIvByte = Hex.decodeHex(aesIv.toCharArray());
			IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKeyIvByte);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(type, key, ivParameterSpec);
			return cipher;
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("BaseAction[getCipher] error:", e);
			throw new AppErrorException(ErrorCode.CODE_AES_ERROR.getCode(),ErrorCode.CODE_AES_ERROR.getMessage());
		}
	}
}
