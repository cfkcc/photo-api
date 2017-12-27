package com.photo.api.service.account.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.photo.api.common.constant.CommonConsts;
import com.photo.api.common.enums.DataBaseStatusType;
import com.photo.api.common.enums.LoginType;
import com.photo.api.common.enums.UserType;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.exception.AppErrorException;
import com.photo.api.common.token.AESUtil;
import com.photo.api.common.token.TokenID;
import com.photo.api.common.util.Arith;
import com.photo.api.common.util.AuthConfig;
import com.photo.api.common.util.CommonUtil;
import com.photo.api.common.util.DateUtil;
import com.photo.api.common.util.EmailUtils;
import com.photo.api.common.util.Page;
import com.photo.api.model.user.User;
import com.photo.api.model.user.UserEmailCode;
import com.photo.api.model.user.UserFans;
import com.photo.api.model.user.UserLoginRecord;
import com.photo.api.model.user.UserOauth;
import com.photo.api.service.account.AccountApiService;
import com.photo.api.service.account.UserEmailCodeService;
import com.photo.api.service.account.UserFansService;
import com.photo.api.service.account.UserLikeService;
import com.photo.api.service.account.UserLoginRecordService;
import com.photo.api.service.account.UserOauthService;
import com.photo.api.service.account.UserService;
import com.photo.api.service.account.impl.auth.AuthFBService;
import com.photo.api.service.account.impl.auth.AuthGGService;
import com.photo.api.service.account.impl.auth.AuthQQService;
import com.photo.api.service.account.impl.auth.AuthTwService;
import com.photo.api.service.account.impl.auth.AuthWeixinService;
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
	@Resource(name="authFB")
	private AuthFBService authFB;
	@Resource(name="authGG")
	private AuthGGService authGG;
	@Resource(name="authQQ")
	private AuthQQService authQQ;
	@Resource(name="authTW")
	private AuthTwService authTW;
	@Resource(name="authWeixin")
	private AuthWeixinService authWeixin;
	@Resource(name="userLoginRecordService")
	private UserLoginRecordService userLoginRecordService;

    
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
				user.setNickName(userName);
				user.setCreateTime(new Date());
				user.setUserType(1);
				userService.addUser(user);
			}else{
				User user = userService.findUserById(userId);
				if (user!=null) {
					userName = user.getNickName();
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
	
	public User getUserByNickName(String nickName) {
		try {
			return userService.findByNickName(nickName);
		} catch (Exception e) {
			throw new AppErrorException(e, "根据昵称查询用户出错");
		}
	}

	private User addUser(User user) {
//		try {
			// 初始化默认的用户资料
			initDefaultMsg(user);
			userService.addUser(user);
//		} catch (AppErrorException ex) {
//			throw ex;
//		} catch (Exception e) {
//			throw new AppErrorException(e, "添加用户异常！");
//		}
		return user;
	}

	private void initDefaultMsg(User user) {
		String uid = null;
		BigDecimal coins = BigDecimal.ZERO;
		if(StringUtils.isBlank(user.getUserId())){
			uid = CommonUtil.getUUID();
			user.setUserId(uid);
		}
		if (user.getCoins()==null) {
			user.setCoins(coins);
		}
		//设置游客昵称

		if (StringUtils.isBlank(user.getNickName())) { //设置注册账号昵称
			user.setNickName(this.getDefaultTouristNickName());
		}
		if (StringUtils.isBlank(user.getHeadImg())) {
			user.setHeadImg(User.DEFAULT_ICON);
		}
		user.setBir(new Date());
		user.setSex(0);
		user.setStatus(DataBaseStatusType.USING.getStatus());
//		user.setCoins(coins);
		//用户类型
		if(user.getUserType() != null && StringUtils.isBlank(user.getUserType().toString())){
			user.setUserType(UserType.SYSTEM_USER.getType());
		}
	}


	@Override
	public Map<String, Object> commitLogin(LoginType loginType, User loginUser) {
		String uid = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {// email登录
			if (loginType == LoginType.SELF) {
				User user = userService.findUserByEmail(loginUser.getEmail());
				// 检查登录是否成功
				if (null == user || !user.getPassword().equals(loginUser.getPassword())) {
					throw new AppErrorException(ErrorCode.CODE_EMAIL_ERROR.getCode(),
							ErrorCode.CODE_EMAIL_ERROR.getMessage());
				}
				uid = user.getUserId();// 得到uid
				loginUser = user;
			} else {// 第三方登录
				// 鉴权是否成功
				UserOauth loginBinding = loginUser.getUserOauth();
				// 查看是否绑定
				UserOauth binding = findBindingByOid(loginBinding.getOid(), loginType.getType());
				if (null == binding) {
//					binding = loginBinding;
					User user = new User();
					// 添加第三方用户
					user.setUserId(CommonUtil.getUUID());
					user.setHeadImg(loginBinding.getOpenIcon());
					user.setNickName(loginBinding.getOpenName());
					addUser(user);
					loginUser = user;
					
					uid = user.getUserId();// 得到uid
					binding = loginBinding;
					binding.setUserId(uid);
					binding.setOauthId(CommonUtil.getUUID());
					binding.setStatus(DataBaseStatusType.USING.getStatus());
					saveBinding(binding);
				} else {
					binding.setAccessToken(loginBinding.getAccessToken());
					userOauthService.updateUserOauth(binding);
					User user = userService.findUserById(loginBinding.getUserId());
					loginUser = user;
				}
			}
			UserLoginRecord record = loginUser.getUserLoginRecord();
			record.setUserId(loginUser.getUserId());
			TokenID tokenId = new TokenID(loginUser.getUserId());
			String tokenIdJson = JSON.toJSONString(tokenId);
			String token = AESUtil.encryptData(tokenIdJson);
			record.setToken(token);
			record.setCreateUser(loginUser.getUserId());
			record.setUserId(loginUser.getUserId());
			record.setCreateTime(new Date(Long.parseLong(tokenId.getTtp(), 16)));
			userLoginRecordService.addUserLoginRecord(record);
			Map<String, Object> userMap = userService.findUserInfoById(loginUser.getUserId());
			result.putAll(userMap);
			result.put(CommonConsts.USER_TOKEN, token);
			return result;
		} catch (AppErrorException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new AppErrorException(ex, "用户登录出错!");

		}

	}


	private Map<String, Object> setUserData(User loginUser) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (loginUser != null) {
			userMap.put("uid", loginUser.getUserId());
			userMap.put("nickName", loginUser.getNickName());
			userMap.put("iconUrl", loginUser.getHeadImg());
			userMap.put("bir", loginUser.getBir());
			userMap.put("sex", loginUser.getSex());
//			userMap.put("coins",Arith.round(loginUser.getCoins().longValue(), 2));
		}else{
			initDefaultMsg(loginUser);
			userMap.putAll(setUserData(loginUser));
		}
		return userMap;
	}

	@Override
	public void updateUser(User user, boolean nameDoubleCheck) {

		try {
			if (nameDoubleCheck) {
				// 校验昵称是否重复
				User nicknameAccount = userService.findByNickName(user.getNickName());
				if (nicknameAccount != null && !nicknameAccount.getUserId().equals(user.getUserId())) {
					throw new AppErrorException(ErrorCode.CODE_NICK_USED_ERROR.getCode(),
							ErrorCode.CODE_NICK_USED_ERROR.getMessage());
				}
			}
			User u = findById(user.getUserId());
			userService.updateUser(u);
		} catch (AppErrorException e) {
			throw e;
		} catch (Exception e) {
			throw new AppErrorException(e, "修改用户信息异常！");
		}
	}

	/*@Override
	public void updatePassword(String uid, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", uid);
		params.put("password", password);
		try {
			userService.updatePassword(params);
		} catch (Exception e) {
			throw new AppErrorException(e, "修改用户密码异常！");
		}
	}*/

	@Override
	public User findById(String uid) {
		return userService.findUserById(uid);
	}

	@Override
	public User findByEmail(String email) {
		try {
			User user = userService.findUserByEmail(email);
			return user;
		} catch (Exception e) {
			throw new AppErrorException(e, "通过email查询用户信息异常！");
		}
	}

	@Override
	public boolean haveNickName(String uid, String nickName) {
		try {
			return userService.haveNickName(uid, nickName);
		} catch (Exception e) {
			throw new AppErrorException(e, "检查昵称是否重复异常！");
		}
	}

	/*@Override
	public Auth auThrid(String code, LoginType type) {
		if (StringUtils.isNotBlank(code))
			return null;
		if (type == LoginType.QQ) {
			return authQQ.auth(code);
		} else if (type == LoginType.WEIXIN) {
			return authWeixin.auth(code);
		} else if (type == LoginType.GOOGOL) {
			return authGG.auth(code);
		}
		return null;
	}*/

	@Override
	public boolean auThrid(String oid, String accessToken, LoginType type
			,String sysType,String packagename, String appId, String openName, String openIcon) {
		log.info("---------------校验参数---------------------");
		log.info("---------------oid---------------------" + oid);
		log.info("---------------openName---------------------" + openName);
		log.info("---------------openIcon---------------------" + openIcon);
		log.info("---------------accessToken---------------------" +accessToken);
		log.info("---------------type---------------------"+type);
		log.info("---------------sysType---------------------"+sysType);
		log.info("---------------packagename---------------------"+packagename);
		log.info("---------------appId---------------------"+appId);
		if (StringUtils.isBlank(oid) || StringUtils.isBlank(accessToken))
			return false;
		
		 if (type == LoginType.QQ) { 
			 return authQQ.auth(oid, accessToken, sysType, packagename); 
		} else if (type == LoginType.WEIXIN) { 
			return authWeixin.auth(oid, accessToken, sysType, packagename); 
		}/* else if (type == LoginType.WEIBO) { 
			return authWeibo.auth(oid, accessToken, refreshToken, sysType, packagename); 
		} */else if (type == LoginType.FACEBOOK) {
			/*if(StringUtils.isBlank(packagename) ){
					packagename=AuthConfig.getString("auth.fb.callback.android.packagename.game");
			}*/
			log.info("------------校验中----------" +  packagename);
			return authFB.auth(oid, accessToken, sysType,packagename);
		}else if (type == LoginType.GOOGLE) {
			return authGG.auth(oid, accessToken, sysType,packagename);
		} else if (type == LoginType.TWITTER) {
			return authTW.auth(oid, accessToken, sysType,packagename);
		}
		return false;
	}

	@Override
	public void saveBinding(UserOauth uo) {
		try {
			userOauthService.addUserOauth(uo);
		} catch (Exception e) {
			throw new AppErrorException(e, "添加第三方账号信息失败");
		}
	}

	@Override
	public UserOauth findBindingByOid(String oid, int type) {
		try {
			return userOauthService.findUserOauthByOpenId(oid, type);
		} catch (Exception e) {
			throw new AppErrorException(e, "查询第三方账号信息异常！");
		}
	}

	@Override
	public String findUserToken(String uid) {
		UserLoginRecord ulr = userLoginRecordService.findLatestRerordByUserId(uid);
		String token = "";
		if (ulr!=null) {
			Date expireTime = ulr.getCreateTime();
			Date nowDate = new Date();
			if (nowDate.getTime()-expireTime.getTime()>7200) {
				token = ulr.getToken();
			}else{
				
			}
		}
		return token;
	}

	/**
	 * 生成一个token
	 * 
	 * @param uid
	 * @return
	 */
/*	public String createUserToken(String uid) {
		TokenID tokenId = new TokenID(uid);
		String token = JSON.toJSONString(tokenId);
		return AESUtil.encryptData(token);
	}*/

	/*public static void main(String[] args) {
		AccountApiServiceImpl aa = new AccountApiServiceImpl();
		String token = aa.createUserToken("12345");
		System.out.println("token = "+token);
		JSONObject json = JSONObject.fromObject(AESUtil.decryptData(token));
		System.out.println("json = "+json);
		System.out.println("uid = "+json.getString("uid"));
		String ttp = json.getString("ttp");
		System.out.println(" ttp = "+Long.parseLong(ttp, 16));
	}*/

	@Override
	public User commitTouristLogin(User user) {
		//由客户端编识生成用户标识
		user.setUserType(UserType.SYSTEM_TOURIST.getType());
		try {
			initDefaultMsg(user);
		} catch (Exception e) {
			throw new AppErrorException(e, "获取游客账号信息失败！");
		}
		return user;
	}

	/**
	 * 设置游客默认昵称
	 * @return
     */
	private String getDefaultTouristNickName(){
		String incrId = CommonUtil.getRandomString(6, 0);
		return new StringBuffer("ID").append(String.format("%s",incrId)).toString();
	}


	@Override
	public Map<String, Object> touristLogin() {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.commitTouristLogin(new User());
		if (user!=null) {
			result.put("uid", user.getUserId());
			result.put("nickName", user.getNickName());
			result.put("headImg", user.getHeadImg());
			result.put("bir", DateUtil.datesShort2String(user.getBir()));
			result.put("sex", user.getSex());
			result.put("coins", Arith.round(user.getCoins().doubleValue(), 2));
		}
		return null;
	}

}
