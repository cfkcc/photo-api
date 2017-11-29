package com.photo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@ComponentScan(basePackages={"com.photo.api"})
@ImportResource({"classpath*:conf/spring.xml"})
@SpringBootApplication
public class PhotoApplication {
	
	@RequestMapping("/")
    String home() {
        return "Hello World!";
    }
	 
	public static void main(String[] args) {
		SpringApplication.run(PhotoApplication.class, args);
	}

}
