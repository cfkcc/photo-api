package com.photo.api.aspect;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.result.ResultMap;


@ControllerAdvice(basePackages = "com.photo.api.controller")
public class ResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
		// 返回结果统一处理
		if(bodyContainer.getValue() instanceof ResultMap){
			bodyContainer.setValue(((ResultMap)bodyContainer.getValue()).getResult());
		}
		
		if (!(bodyContainer.getValue() instanceof ResponseMessage)) {// 响应消息处理
			bodyContainer.setValue(new ResponseMessage<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), bodyContainer.getValue()));
		}
		
		response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
	}

	
}
