package com.photo.api.common.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 读取属性文件类
 * 
 */
public class ReadPropertiesUtil
{
	
	private ResourceBundle  r  = null;
	private final String filaname;
	private Logger logger=Logger.getLogger(this.getClass());
	
	public ReadPropertiesUtil(String filename)
	{
		this.filaname=filename;
		this.init();
	}
	
	
	/**
	 * 读取string值
	 * @param key 属性文件中key值
	 * @return 字符串内容
	 */
	public String getStringValue(String key)
	{
		if(r.containsKey(key))
		{
			String value=r.getString(key).trim();
			logger.debug("key["+key+"] value["+value+"]");
			return value;
		}
		logger.warn("key["+key+"] 不存在!");
		return null;
	}
	
	public String getStringValue(String key,String defaultValue){
		String value = getStringValue(key);
		return value == null ? defaultValue : value;
	}
	
	/**
	 * 读取string值
	 * @param key 属性文件中key值
	 * @return 字符串内容
	 */
	public Integer getIntValue(String key)
	{
		String v=this.getStringValue(key);
		if(!RegexUtil.isNumber(v))
		{
			return null;
		}
		return Integer.valueOf(v);
	}
	
	/**
	 * 
	 * @Description 获取int值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Integer getIntValue(String key,Integer defaultValue){
		Integer value = getIntValue(key);
		return value == null?defaultValue:value;
	}
	
	/**
	 * 读取string值
	 * @param key 属性文件中key值
	 * @return 字符串内容
	 */
	public boolean getBooleanValue(String key)
	{
		return "true".equals(this.getStringValue(key));
	}
	
	/**
	 * 
	 * @Description 读取boolean 值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	
	public boolean getBooleanValue(String key,boolean defaultValue)
	{
		String value = this.getStringValue(key);
		return value == null ? defaultValue : "true".equals(value);
	}
	
	
	public void init(){
		r = ResourceBundle.getBundle(this.filaname, Locale.CHINESE);
		if(logger.isDebugEnabled()){
			logger.debug("初始化["+this.filaname+"] values["+r.toString()+"]");
		}
		
		
	}

}
