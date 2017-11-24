package com.photo.api.common.util;

import java.util.Random;
import java.util.UUID;

public final class CommonUtil {
	public static String getUUID(){
    	UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString().replaceAll("-", "");
    	return uuidStr; 
    }
	
	public static String getRandomString(int length, int type) { //length表示生成字符串的长度  
	    String base0 = "0123456789";
	    String base1 = "abcdefghijklmnopqrstuvwxyz";
	    String base2 = "abcdefghijklmnopqrstuvwxyz0123456789";
	    String base = "";
	    if(type == 0)
	    {
	    	base = base0;
	    }
	    if(type == 1)
	    {
	    	base = base1;
	    }
	    if(type == 2)
	    {
	    	base = base2;
	    }
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
}
