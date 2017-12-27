package com.photo.api.common.util;

/**
 * 系统资源属性配置
 */
public class PayConfig {
	private PayConfig(){}
	public static final ReadPropertiesUtil CONFIG = new ReadPropertiesUtil("conf.alipay");
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
