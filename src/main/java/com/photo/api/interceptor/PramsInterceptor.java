package com.photo.api.interceptor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PramsInterceptor implements HandlerInterceptor{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
    	// 所有请求第一个进入的方法  
        String reqURL = request.getRequestURL().toString();  
        String ip = request.getRemoteHost ();  
          
        InputStream  is = request.getInputStream ();  
        StringBuilder responseStrBuilder = new StringBuilder ();  
        BufferedReader streamReader = new BufferedReader (new InputStreamReader (is,"UTF-8"));  
        String inputStr;  
        while ((inputStr = streamReader.readLine ()) != null)  
         responseStrBuilder.append (inputStr);  
        System.out.println("请求参数: " + responseStrBuilder.toString ());  
        String parmeter = responseStrBuilder.toString();  
           
       long startTime = System.currentTimeMillis();  
       request.setAttribute("startTime", startTime);  
       if (handler instanceof HandlerMethod) {  
           StringBuilder sb = new StringBuilder(1000);  
           sb.append("-----------------------").append(System.currentTimeMillis()).append("-------------------------------------\n");  
           HandlerMethod h = (HandlerMethod) handler;  
           //Controller 的包名  
           sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");  
           //方法名称  
           sb.append("Method    : ").append(h.getMethod().getName()).append("\n");  
           //请求方式  post\put\get 等等  
           sb.append("RequestMethod    : ").append(request.getMethod()).append("\n");  
           //所有的请求参数  
           sb.append("Params    : ").append(parmeter).append("\n");  
           //部分请求链接  
           sb.append("URI       : ").append(request.getRequestURI()).append("\n");  
            //完整的请求链接  
           sb.append("AllURI    : ").append(reqURL).append("\n");  
           //请求方的 ip地址  
           sb.append("request IP: ").append(ip).append("\n");  
           logger.info(sb.toString());  
             
       }  
//       修改request中的参数并保存到request中   
       request.setAttribute("parmeter_json", parmeter);   
        return true;
    }
    
    private String ConvertObjectArrToStr(Object [] arr) {
        String result = "";
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                if (!"".equals(String.valueOf(arr[i]))) {
                    result += String.valueOf(arr[i]) + ",";
                }
            }
            if (!"".equals(result)) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
