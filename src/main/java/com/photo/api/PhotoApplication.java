package com.photo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@ComponentScan(basePackages={"com.photo.api"})
@ImportResource({"classpath*:conf/spring.xml"})
@SpringBootApplication
public class PhotoApplication {
	
/*	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }*/
	/**
     * 添加第三方json工具
     * 1、需要再pom.xml加入相关以来
     * 2、需要再APP 继承 WeWebMvcConfigurerAdapter  重写configureMessageConverters
     * 3、或者使用bean注入fastJsonHttpMessageConverters
     * 
     * 
     * 配置fastjson支持两种方法
     * 一：1、启动继承 WebMvcConfigurerAdapter 2、覆盖方法configureMessageConverters
     * 二：使用bean注入fastJsonHttpMessageConverters
     * 这里使用@Bean注入 HttpMessageConverters
     * @Description 
     * @author Administrator
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1、定义convert转换消息对象
        FastJsonHttpMessageConverter fasConverter  = new  FastJsonHttpMessageConverter();
        //2、添加fastJson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、再convert中添加配置信息
        fasConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasConverter;
        return new HttpMessageConverters(converter); 
    }
	public static void main(String[] args) {
		SpringApplication.run(PhotoApplication.class, args);
	}

}
