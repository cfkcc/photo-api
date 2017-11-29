package com.photo.api.controller.accont;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.photo.api.common.constant.CommonConsts;
import com.photo.api.common.enums.LoginTypeEnum;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.ServiceException;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.token.AESUtil;
import com.photo.api.common.token.TokenID;
import com.photo.api.controller.BaseController;
import com.photo.api.model.user.ThirdPartyUser;
import com.photo.api.model.user.User;
import com.photo.api.service.account.AccountApiService;

@RestController
@RequestMapping("account")
public class AccountController extends BaseController{
	
	@Resource(name="accountApiService")
	private AccountApiService accountService;
	
	@RequestMapping("login")
	public Object login(){

//		String uid = getRequestUserId();
		String ip = getClientIp();
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("uid", uid);
		map.put("ip", ip);
		return new ResponseMessage<Map<String, Object>>(map);
	}
	
    /**
     * 第三方登录
     * @param thirdPartyUser
     * @return
     * @throws ServiceException
     */
    @RequestMapping("thirdLogin")
    public Object thirdLogin(ThirdPartyUser thirdPartyUser) throws ServiceException {
        if(StringUtils.isBlank(thirdPartyUser.getOpenId()) || StringUtils.isBlank(thirdPartyUser.getAccessToken())
                || thirdPartyUser.getClientType() == null || StringUtils.isBlank(thirdPartyUser.getNickname())
                || StringUtils.isBlank(thirdPartyUser.getHeadImg())){
            return new ResponseMessage<Object>(ErrorCode.CODE_PARAM_ERROR.getCode(),ErrorCode.CODE_PARAM_ERROR.getMessage());
        }
        if(thirdPartyUser.getClientType().intValue() == LoginTypeEnum.QQ.getLoginType()){
            thirdPartyUser.setUserIp(this.getClientIp());
            thirdPartyUser.setThirdAppId(CommonConsts.QQ_APP_ID);
            thirdPartyUser.setThirdAppKey(CommonConsts.QQ_APP_KEY);
        }
        boolean isPass = accountService.checkThirdToken(thirdPartyUser, Boolean.TRUE);
        if(isPass){
        	User user = accountService.addThirdUserLogin(thirdPartyUser);
            String userId = user.getUserId();
            TokenID tokenID = new TokenID(userId);
            String token = JSON.toJSONString(tokenID);
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("user",user);
            result.put(CommonConsts.USER_TOKEN, AESUtil.encryptData(token));
            return new ResponseMessage<Map<String, Object>>(result);
        }else{
            return new ResponseMessage<Object>(ErrorCode.CODE_AUTH_ERROR.getCode(),ErrorCode.CODE_AUTH_ERROR.getMessage());
        }
    }


    /**
     * 获取登录的配置信息
     * @return
     */
    @RequestMapping("getLoginConfig")
    public Object getConfig(){
        Map<String,Object> configMap= new HashMap<String, Object>();
        Map<String,Object> wechatMap = new HashMap<String, Object>();
        wechatMap.put("appId",CommonConsts.WEIXIN_APP_ID);
        wechatMap.put("appKey",CommonConsts.WEIXIN_APP_SECRET);

        Map<String,Object> qqMap = new HashMap<String, Object>();
        qqMap.put("appId",CommonConsts.QQ_APP_ID);
        qqMap.put("appKey",CommonConsts.QQ_APP_KEY);

        configMap.put("wechat",wechatMap);
        configMap.put("qq",qqMap);

        return new ResponseMessage<Map<String, Object>>(configMap);
    }
	

}
