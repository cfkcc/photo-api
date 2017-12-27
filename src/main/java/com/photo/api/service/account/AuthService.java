package com.photo.api.service.account;

import com.photo.api.model.user.Auth;

/**
 * 第三方登录鉴权
 * @author liup
 * 2016年3月28日
 */
public interface AuthService {

	/**
	 * 通过code调用第三方api返回accessToken
	 * @param code
	 * @return
	 */
	public Auth auth(String code);
	
	/**
	 * 第三方鉴权
	 * @param oid
	 * @param accessToken
	 * @param type
	 * @return
	 */
	public boolean auth(String oid, String accessToken, String sysType,String packagename);
	
	
	
}
