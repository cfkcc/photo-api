package com.photo.api.aspect;/**
 * Created by Dell on 2017/8/4.
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.photo.api.annotation.LoginSign;
import com.photo.api.common.constant.CommonConsts;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.ServiceException;
import com.photo.api.common.param.BaseParam;
import com.photo.api.common.token.AESUtil;
import com.photo.api.common.token.TokenID;

/**
 * 控制器切面，用于处理公共参数，记录日志
 **/
@Aspect
@Component
public class ParamsAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HttpServletRequest request;

    /**
     * 设置公共参数
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.photo.api.controller..*.*(..))")
    @Order(1)
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();

        Object[] objs = pjp.getArgs();
        if (null != objs) {
            for (Object obj : objs) {
                if (obj instanceof BaseParam) {
                	BaseParam bp = (BaseParam) obj;
                    //处理http头信息
                    setHeaderParams(bp, uuidStr);
                    //从userToken中解密出userId
                    setUserIdFromToken(bp);
                }
            }
        }
        String token = request.getHeader("token");
//        String paramsStr = getRequestJsonString(request);
//        logger.info("url={},uuid={},token={},收到请求参数：{}", request.getRequestURI(),uuidStr,token, paramsStr);
        logger.info("url={},uuid={},token={},收到请求参数：{}", request.getRequestURI(),uuidStr,token, Arrays.toString(objs));
        Object re = null;
        try {
            re = pjp.proceed();
        }catch (ServiceException e){
            logger.info("url={},uuid={},token={},执行ServiceException异常：{}", request.getRequestURI(),uuidStr,token, e.getMessage());
            throw e;
        }
        catch (Exception e) {
            logger.info("url={},uuid={},token={},执行异常：{}", request.getRequestURI(),uuidStr,token, e.getMessage());
            throw e;
        } finally {
            logger.info("url={},uuid={},token={},响应结果：{}", request.getRequestURI(),uuidStr,token, (null==re?"":re.toString()));
        }

        return re;
    }
    
    @Around(value = "@annotation(loginSign)")
    @Order(2)
    public Object loginCheckAround(ProceedingJoinPoint pjp,LoginSign loginSign) throws ServiceException,Throwable {
        if(loginSign.isNeedLogin()){//若要求必须登录，则验证是否存在userId
            Object[] objs = pjp.getArgs();
            boolean isLogin = false;
            if (null != objs) {
                for (Object obj : objs) {
                    try {
                        if (obj instanceof BaseParam) {
                        	BaseParam bp = (BaseParam) obj;
                            if (StringUtils.isNotEmpty(bp.getUid())) {
                                isLogin = true;
                                break;
                            }
                        } else {
                            Class clazz = obj.getClass();
                            Method method = clazz.getMethod("getUid");
                            if (null != method) {
                                Object value = method.invoke(obj);
                                if (null != value && StringUtils.isNotEmpty(value.toString())) {
                                    isLogin = true;
                                    break;
                                }
                            }
                        }
                    } catch (NoSuchMethodException e) {
                       logger.info("验证是否登录异常NoSuchMethodException:{}",e.getMessage());
                    } catch (IllegalAccessException e) {
                        logger.info("验证是否登录异常IllegalAccessException:{}",e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.info("验证是否登录异常InvocationTargetException:{}",e.getMessage());
                    }
                }
            }
            if(!isLogin){
                throw new ServiceException(ErrorCode.CODE_AUTH_ERROR.getCode(),"请登录！");
            }
        }
        return pjp.proceed();
    }

    /**
     * 从Token中解密出userId
     * @param obj
     */
    public  void setUserIdFromToken(BaseParam obj){
        try {
            if(null!=obj&&null!=obj.getToken()){
                logger.info("待解密的字符串为：{}",obj.getToken());
                String str = AESUtil.decryptData(obj.getToken());
                if(StringUtils.isBlank(str)){
                    return;
                }
                logger.info("token解密后字符串为: {}",str);
                TokenID tokenID = JSON.parseObject(str, TokenID.class);
                if(null==tokenID||null==tokenID.getUid()){
                    return;
                }
                obj.setUid(tokenID.getUid());
            }
        }catch (Exception e){
            logger.error("从userToken中解密出userId异常:",e);
        }
    }

    /**
     * 设置http头公共参数
     * @param obj
     * @param uuidStr
     */
    private void setHeaderParams(BaseParam obj, String uuidStr) {
        String sysType = request.getHeader("sysType");
        String sysVersion = request.getHeader("systemVersion");
        String appVersion = request.getHeader("appVersion");
        String packagename= request.getHeader("packageName");
        String tt= request.getHeader("tt");
        String appId= request.getHeader("appId");
        String areaCode = request.getHeader("areaCode");
        String token = request.getHeader(CommonConsts.USER_TOKEN);
        logger.info("用户token: "+token);
        obj.setSysType(sysType);
        obj.setSystemVersion(sysVersion);
        obj.setAppVersion(appVersion);
        obj.setPackageName(packagename);
        obj.setTt(tt);
        obj.setAppId(appId);
        obj.setAreaCode(areaCode);
        obj.setToken(null==obj.getToken()&&StringUtils.isNotEmpty(token)?token:obj.getToken());
    }
    
    /*** 
     * 获取 request 中 json 字符串的内容 
     *  
     * @param request 
     * @return : <code>byte[]</code> 
     * @throws IOException 
     */  
    public String getRequestJsonString(HttpServletRequest request)  
            throws IOException {  
        String submitMehtod = request.getMethod();  
        // GET  
        if (submitMehtod.equals("GET")) {  
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");  
        // POST  
        } else {  
            return getRequestPostStr(request);  
        }  
    }  
  
    /**       
     * 描述:获取 post 请求的 byte[] 数组 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,  
                    contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
  
    /**       
     * 描述:获取 post 请求内容 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
    }  
  
}
