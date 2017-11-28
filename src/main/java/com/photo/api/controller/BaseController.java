package com.photo.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class BaseController {
    protected HttpServletRequest request;

    protected String getRequestUserId(){
        Object attribute = request.getAttribute("uid");
        if(attribute != null){
            return String.valueOf(attribute);
        }
        return null;
    }


    protected String getClientIp(){
        String clientIp = "";
        if(request != null){
            clientIp = request.getHeader("X-Real-IP");
            if(StringUtils.isNoneBlank(clientIp) && !"unknown".equalsIgnoreCase(clientIp)){
                return clientIp;
            }
            clientIp = request.getHeader("X-Forwarded-For");
            if(StringUtils.isNoneBlank(clientIp) && !"unknown".equalsIgnoreCase(clientIp)){
                int index = clientIp.indexOf(",");
                if(index != -1){
                    return clientIp.substring(0,index);
                }else{
                    return clientIp;
                }
            }else{
                return request.getRemoteAddr();
            }
        }
        return clientIp;
    }
}
