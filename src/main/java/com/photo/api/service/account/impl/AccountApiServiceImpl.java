package com.photo.api.service.account.impl;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
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
import com.photo.api.common.util.CommonUtil;
import com.photo.api.common.util.CryptalUtil;
import com.photo.api.common.util.EmailUtils;
import com.photo.api.common.util.HttpClientUtil;
import com.photo.api.common.util.Page;
import com.photo.api.model.user.ThirdPartyUser;
import com.photo.api.model.user.User;
import com.photo.api.model.user.UserEmailCode;
import com.photo.api.model.user.UserFans;
import com.photo.api.model.user.UserOauth;
import com.photo.api.service.account.AccountApiService;
import com.photo.api.service.account.UserEmailCodeService;
import com.photo.api.service.account.UserFansService;
import com.photo.api.service.account.UserLikeService;
import com.photo.api.service.account.UserOauthService;
import com.photo.api.service.account.UserService;
import com.photo.api.service.photo.PhotoGroupService;

@Service("accountApiService")
public class AccountApiServiceImpl implements AccountApiService {
	Logger log = LoggerFactory.getLogger(AccountApiServiceImpl.class);

	@Resource(name="userOauthService")
	private UserOauthService userOauthService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="userFansService")
	private UserFansService userFansService;
	@Resource(name="userLikeService")
	private UserLikeService userLikeService;
	@Resource(name="photoGroupService")
	private PhotoGroupService photoGroupService;
	@Resource(name="userEmailCodeService")
	private UserEmailCodeService userEmailCodeService;

    
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
    
    
    public User addThirdUserLogin(ThirdPartyUser thirdPartyUser) {
        String openId = thirdPartyUser.getOpenId();
        Integer clientType = thirdPartyUser.getClientType();
        UserOauth userOauth = userOauthService.findUserOauthByOpenId(openId, clientType);
        String userId = null;
        if(userOauth == null){
            User user = userService.addUser(thirdPartyUser.getNickname(), thirdPartyUser.getHeadImg(), "");
            userId = user.getUserId();
            userOauthService.addUserOauth(openId,userId,thirdPartyUser.getAccessToken(),clientType);
        }else{
            userId = userOauth.getUserId();
        }
        User user = userService.findUserById(userId);
        return user;
    }

	
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

	
	public Map<String, Object> findFans(Page page) {
		page = userFansService.findFansByPage(page);
		Map<String, Object> result = new HashMap<String, Object>();
		long totalCount = page.getRowCount();
		result.put("totalCount", totalCount);
		result.put("totalPage", page.getPageCount());
		List<Map<String, Object>> photoList = new ArrayList<Map<String, Object>>();
		Iterator<UserFans> it = (Iterator<UserFans>)page.getRecords().iterator();
		if(it != null){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while(it.hasNext()){
				UserFans uf = it.next();
				Map<String, Object> map = userService.findUserInfoById(uf.getFansId());
				map.remove("conins");
				map.remove("sign");
				map.remove("email");
				list.add(map);
			}
			result.put("list", list);
		}
		return result;
	}

	
	public Map<String, Object> findFowllowed(Page page) {
		page = userFansService.findFansByPage(page);
		Map<String, Object> result = new HashMap<String, Object>();
		long totalCount = page.getRowCount();
		result.put("totalCount", totalCount);
		result.put("totalPage", page.getPageCount());
		List<Map<String, Object>> photoList = new ArrayList<Map<String, Object>>();
		Iterator<UserFans> it = (Iterator<UserFans>)page.getRecords().iterator();
		if(it != null){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while(it.hasNext()){
				UserFans uf = it.next();
				Map<String, Object> map = userService.findUserInfoById(uf.getUserId());
				map.remove("conins");
				map.remove("sign");
				map.remove("email");
				list.add(map);
			}
			result.put("list", list);
		}
		return result;
	}

	
	public Map<String, Object> getUserInfo(String userId) {
		Map<String, Object> result = userService.findUserInfoById(userId);
		long followingCount = userFansService.findCountByUserId(userId);
		result.put("followingCount", followingCount);
		long fansCount = userFansService.findFansCountByUserId(userId);
		result.put("fansCount", fansCount);
		long postCount = photoGroupService.findCountByUserId(userId);
		result.put("postCount", postCount);
		return result;
	}

	
	public Map<String, Object> updateUserEmail(String userId, String email, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		//1、首先通过email 地址获取code的相关信息，验证code是否过去后者已经被使用
		UserEmailCode uec = userEmailCodeService.findByCode(code);
		if (uec != null) {
			if(!uec.getEmail().equals(email)){
				result.put("tips", "The email ["+email+"] is wrong!");
				return result;
			}
			if(uec.getStatus()==UserEmailCode.Status.Used.getStatus()){
				result.put("tips", "The code ["+code+"] has been used!");
				return result;
			}
			int flag = uec.getExpireTime().compareTo(new Date());
			if(flag==-1){
				uec.setStatus(UserEmailCode.Status.Unvalid.getStatus());
				userEmailCodeService.updatUserEmailCode(uec);
				result.put("tips", "The code ["+code+"] is expired!");
				return result;
			}
			uec.setStatus(UserEmailCode.Status.Used.getStatus());
			userEmailCodeService.updatUserEmailCode(uec);
			//2、再次判断该email所绑定的用户的ID 所属的用户是否存在，存在即可更新用户信息
			User user = userService.findUserById(userId);
			if (user != null) {
				user.setEmail(email);
				userService.updateUser(user);
				result.put("tips", "Bind your email success!");
			}
		}else{
			result.put("tips", "The code ["+code+"] is wrong!");
		}
		return result;
	}

	
	public Map<String, Object> getNewCode(String userId, String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		String code  = CommonUtil.getRandomString(6, 2);
		//1、首先通过email 地址获取code的相关信息，验证code是否过去后者已经被使用
		UserEmailCode uec = new UserEmailCode();
		uec.setCodeId(CommonUtil.getUUID());
		uec.setUserId(userId);
		uec.setCode(code);
		uec.setType(0);
		Date expireTime = new Date();
		uec.setCreateTime(expireTime);
		expireTime.setMinutes(expireTime.getMinutes()+1);
		uec.setExpireTime(expireTime);
		uec.setEmail(email);
		uec.setStatus(UserEmailCode.Status.Unused.getStatus());
		String userName = "ID"+CommonUtil.getRandomString(6, 0);
		
		try {
			if (StringUtils.isEmpty(userId)) {
				User user = new User();
				userId = CommonUtil.getUUID();
				user.setUserId(userId);
				user.setCoins(BigDecimal.ZERO);
//				user.setEmail(email);
				user.setStatus(1);
				user.setNickname(userName);
				user.setCreateTime(new Date());
				user.setUserType(1);
				userService.addUser(user);
			}else{
				User user = userService.findUserById(userId);
				if (user!=null) {
					userName = user.getNickname();
				}
			}
			userEmailCodeService.addUserEmailCode(uec);
			EmailUtils.sendMail(email, userName, code);
			result.put("userId", userId);
		} catch (Exception e) {
			result.put("tips", "Send email failed!");
		}
		return result;
	}

	@Override
	public void saveOrUpdateUserFans(String userId, String fansId, Boolean isFans) {
		userFansService.saveOrUpdateUserFans(userId, fansId, isFans);		
	}

	@Override
	public void saveOrUpdateUserLike(String userId, String likerId, Boolean isLike) {
		userLikeService.saveOrUpdateUserLike(userId, likerId, isLike);		
	}

	@Override
	public void saveOrUpdateUserInfo(String userId, String nickName, String headImage, String sign) {
		userService.saveOrUpdateUserInfo(userId, nickName, headImage, sign);
	}

}
