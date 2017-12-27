package com.photo.api.aspect;

import com.alibaba.fastjson.JSON;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.AppErrorException;
import com.photo.api.common.exception.ServiceException;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.result.ResultMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice(basePackages = "com.photo.api.controller")
public class ResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		return new ServletServerHttpRequest(servletRequest);
	}

	protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		return new ServletServerHttpResponse(response);
	}

	@ExceptionHandler(Throwable.class)
	public void exceptionHandler(NativeWebRequest request, Throwable e) {
		ServletServerHttpRequest servletServerRequest = new ServletServerHttpRequest(
				request.getNativeRequest(HttpServletRequest.class));
		ServletServerHttpResponse servletServerResponse = new ServletServerHttpResponse(
				request.getNativeResponse(HttpServletResponse.class));
		/**
		 * 响应内容
		 */
		ResponseMessage<Void> responseMessage = new ResponseMessage<Void>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
		boolean check = false;
		Throwable ex = e;
		for(int i=0;i<3;i++){
			if (ex instanceof ServiceException) {
				ServiceException exception = (ServiceException) ex;
				responseMessage = new ResponseMessage<Void>(ErrorCode.CODE_SERVICE_ERRER.getCode(), ErrorCode.CODE_SERVICE_ERRER.getMessage());
				log.error("Service exception",exception.getMessage());
				check= true;
				break;
			} else if (ex instanceof AppErrorException) {
				responseMessage = new ResponseMessage<Void>(ErrorCode.INVOKE_SERVICE_ERROR.getCode(), ErrorCode.INVOKE_SERVICE_ERROR.getMessage());
				log.error("AppErrorException exception",ex);
				check= true;
				break;
			}
			ex = ex.getCause();
			if(null==ex){
				break;
			}
		}
		if (!check) {
			responseMessage = new ResponseMessage<Void>(ErrorCode.CODE_ERROR.getCode(), ErrorCode.CODE_ERROR.getMessage());
			log.error("error exception",e);
		}

		String result = JSON.toJSONString(responseMessage);
		MappingJacksonValue bodyContainer = new MappingJacksonValue(responseMessage);
		beforeBodyWriteInternal(bodyContainer, MediaType.APPLICATION_JSON_UTF8, null, servletServerRequest,
				servletServerResponse);
		HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		try {
			response.getOutputStream().write(result.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse res) {
		// 返回结果统一处理
		if(bodyContainer.getValue() instanceof ResultMap){
			bodyContainer.setValue(((ResultMap)bodyContainer.getValue()).getResult());
		}
		
		if (!(bodyContainer.getValue() instanceof ResponseMessage)) {// 响应消息处理
			bodyContainer.setValue(new ResponseMessage<Object>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), bodyContainer.getValue()));
		}

		res.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");



	}

}
