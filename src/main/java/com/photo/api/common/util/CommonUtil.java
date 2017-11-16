package com.photo.api.common.util;

import java.util.UUID;

public final class CommonUtil {
	public static String getUUID(){
    	UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString().replaceAll("-", "");
    	return uuidStr; 
    }
}
