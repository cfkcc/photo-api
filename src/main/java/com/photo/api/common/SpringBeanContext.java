package com.photo.api.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 设置spring上下文，通过getBean()获取bean对象
 * 需在xml配置文件的第一入口处注入：<bean class="com.zed.common.util.SpringBeanContext" />
 * @author 天俊
 *
 */
public class SpringBeanContext implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(SpringBeanContext.class);
	
	private static ApplicationContext applicationContext;
	
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}
	
	public static ApplicationContext getContext(){
		return applicationContext;
	}
	
	
	public static <T> T  getBean(String beanId,Class<T> clazz) {
		 if(applicationContext!=null){
			 try{
				 return applicationContext.getBean(beanId, clazz);
			 }catch(BeansException e){
				 logger.info(e.getMessage());
			 }
			 
		 }
		 
		 return null;
	 }

}
