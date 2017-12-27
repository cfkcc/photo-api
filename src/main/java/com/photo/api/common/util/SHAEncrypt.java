package com.photo.api.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncrypt {
	public static String encodeSHA512(String code, String token, String uid, String encodeParams){
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			encodeStr += code;
			encodeStr += token;
			encodeStr += uid;
			encodeStr += encodeParams;
			messageDigest.update(encodeStr.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	public static String encodeSHA512(String str, String uri){
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			encodeStr += str;
			encodeStr += uri;
			messageDigest.update(encodeStr.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	private static String byte2Hex(byte[] bytes){
		 StringBuffer stringBuffer = new StringBuffer();
		 String temp = null;
		 for (int i=0;i<bytes.length;i++){
		  temp = Integer.toHexString(bytes[i] & 0xFF);
		  if (temp.length()==1){
		  //1得到一位的进行补0操作
		  stringBuffer.append("0");
		  }
		  stringBuffer.append(temp);
		 }
		 return stringBuffer.toString();
	}
}
