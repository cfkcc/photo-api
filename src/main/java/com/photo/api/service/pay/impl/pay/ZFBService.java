package com.photo.api.service.pay.impl.pay;

import com.photo.api.common.util.SystemConfig;

public class ZFBService {
	
	protected static final String aesKey = SystemConfig.getString("http.aes.key");
	protected static final String aesIv = SystemConfig.getString("http.aes.iv");

}
