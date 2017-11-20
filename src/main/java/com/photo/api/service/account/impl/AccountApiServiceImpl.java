package com.photo.api.service.account.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.photo.api.common.constant.CommonConsts;
import com.photo.api.common.enums.LoginTypeEnum;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.ServiceException;
import com.photo.api.common.util.Arith;
import com.photo.api.common.util.CryptalUtil;
import com.photo.api.common.util.HttpClientUtil;
import com.photo.api.model.user.ThirdPartyUser;
import com.photo.api.model.user.User;
import com.photo.api.model.user.UserOauth;
import com.photo.api.service.account.AccountApiService;
import com.photo.api.service.account.UserOauthService;
import com.photo.api.service.account.UserService;

@Service("accountApiService")
public class AccountApiServiceImpl implements AccountApiService {
	Logger log = LoggerFactory.getLogger(AccountApiServiceImpl.class);

	@Resource(name="userOauthService")
	private UserOauthService userOauthService;
	@Resource(name="userService")
	private UserService userService;

    @Override
    public boolean checkThirdToken(ThirdPartyUser thirdPartyUser, boolean isOpen) throws ServiceException {
    	if (!isOpen) {
			return Boolean.TRUE;
		}
        Integer clientType = thirdPartyUser.getClientType();
        boolean isPass = false;
        if(clientType != null){
            try{
                if(LoginTypeEnum.QQ.getLoginType() == clientType){
                    isPass = checkQQToken(thirdPartyUser);
                }else if(LoginTypeEnum.WECHAT.getLoginType() == clientType){
                    isPass = checkWechatToken(thirdPartyUser.getAccessToken(),thirdPartyUser.getOpenId());
                }
            }catch (Exception e){
                log.error("ThirdPartyLoginApiServiceImpl checkThirdToken is error :"+e.getMessage(),e);
                throw new ServiceException(ErrorCode.CODE_SERVICE_ERRER,e);
            }
        }
        return isPass;
    }

    private boolean checkQQToken(ThirdPartyUser thirdPartyUser) throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(thirdPartyUser.getThirdAppId()).append("&")
                .append("format=json&")
                .append("openid=").append(thirdPartyUser.getOpenId()).append("&")
                .append("openkey=").append(thirdPartyUser.getAccessToken()).append("&")
                .append("pf=").append(thirdPartyUser.getPf()).append("&")
                .append("userip=").append(thirdPartyUser.getUserIp());
        String combineParam = sb.toString();
        String encodeParam = URLEncoder.encode(combineParam,"UTF-8");
        encodeParam = "GET&%2Fv3%2Fuser%2Fget_info&"+encodeParam;
        String key = thirdPartyUser.getThirdAppKey() + "&";
        byte[] data = CryptalUtil.HmacSHA1Encrypt(encodeParam,key);
        String sig = Base64.encodeBase64String(data);
        String requestUrl = CommonConsts.QQ_URL + "?" + combineParam + "&sig=" + URLEncoder.encode(sig,"UTF-8");
        String result = HttpClientUtil.doGet(requestUrl);
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject != null && jsonObject.getInteger("ret") == 0){
                return true;
            }
        }
        return false;
    }

    private boolean checkWechatToken(String accessToken,String openId){
        String url = String.format(CommonConsts.WECHAT_URL,accessToken,openId);
        String result = HttpClientUtil.doGet(url);
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject.containsKey("errcode") && jsonObject.getIntValue("errcode") == 0){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public User addThirdUserLogin(ThirdPartyUser thirdPartyUser) {
        String openId = thirdPartyUser.getOpenId();
        Integer clientType = thirdPartyUser.getClientType();
        UserOauth userOauth = userOauthService.findUserOauthByOpenId(openId, clientType);
        String userId = null;
        if(userOauth == null){
            User user = userService.addUser(thirdPartyUser.getNickname(), thirdPartyUser.getHeadImg(), 1);
            userId = user.getUserId();
            userOauthService.addUserOauth(openId,userId,thirdPartyUser.getAccessToken(),clientType);
        }else{
            userId = userOauth.getUserId();
        }
        User user = userService.findUserById(userId);
        return user;
    }

	@Override
	public Map<String, Object> getBalance(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.findUserById(userId);
		double coins = 0;
		if (user != null) {
			coins = Arith.round(user.getCoins().doubleValue(), 2);
		}
		result.put("coins", coins);
		return result;
	}

}
