package com.photo.api.common.util;

/**
 * 第三方授权系统资源属性配置
 * @author liup
 * 2016年3月25日
 */
public class AuthConfig {
	private AuthConfig(){}
	public static final ReadPropertiesUtil CONFIG = new ReadPropertiesUtil("conf.auth");
	/**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public static String getString(String key)
	{
		return CONFIG.getStringValue(key);
	}
	/**
	 * 获取整型
	 * @param key
	 * @return
	 */
	public static int getInt(String key)
	{
		return CONFIG.getIntValue(key);
	}
	/**
	 * 获取boolean型
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key)
	{
		return CONFIG.getBooleanValue(key);
	}
}
