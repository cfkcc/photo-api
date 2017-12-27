package com.photo.api.controller.accont;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.photo.api.annotation.LoginSign;
import com.photo.api.common.enums.LoginType;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.param.CommParam;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.util.CommonUtil;
import com.photo.api.common.util.Page;
import com.photo.api.controller.BaseController;
import com.photo.api.model.user.User;
import com.photo.api.model.user.UserLoginRecord;
import com.photo.api.model.user.UserOauth;
import com.photo.api.service.account.AccountApiService;

@RestController
@RequestMapping("user")
public class AccountController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Resource(name="accountApiService")
	private AccountApiService accountApiService;
	
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="thirdLogin", method=RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody CommParam params){
		// 登录逻辑处理
		User user = new User();
		LoginType loginType = LoginType.getLoginType(params.getType());
		if (loginType == LoginType.SELF) {
			user.setEmail(params.getEmail());
			user.setPassword(decryptData(params.getPassword()));
		} else {// 第三方登录
			logger.info("------------------第三方登录校验开始--------------------");
			// 鉴权是否成功
			//检测包名
			logger.info("-----loginType------"+loginType);
			logger.info("1."+params.getOid());
			logger.info("2."+params.getAccessToken());
			logger.info("3."+params.getOpenIcon());
			logger.info("3."+params.getOpenName());
			logger.info("4."+loginType);
			logger.info("4."+params.getAppId());
			logger.info("6."+params.getSysType());
			logger.info("7."+params.getPackageName());
//			boolean authThrid = accountApiService.auThrid(params.getOid(), params.getAccessToken(), loginType,params.getSysType(), params.getPackageName(),params.getAppId(),params.getOpenName(), params.getOpenIcon());
			
//			if (!authThrid) {
//				return new ResponseMessage<Object>(ErrorCode.CODE_AUTH_ERROR.getCode(), ErrorCode.CODE_AUTH_ERROR.getMessage(), null);
//			}
			//第三方登录需要获取客户端传入的Authorization Code 
			UserOauth uo = new UserOauth(params.getOid(), params.getUid(), params.getAccessToken(), loginType.getType(),params.getOpenIcon(),params.getOpenName());
			user.setUserOauth(uo);
		}
		UserLoginRecord record = new UserLoginRecord();
		record.setLoginRecordId(CommonUtil.getUUID());
		record.setLoginType(loginType.getType());
		user.setUserLoginRecord(record);
		return new ResponseMessage<Map<String, Object>>(accountApiService.commitLogin(loginType, user));
	}
	@LoginSign(isNeedLogin=false)
	@RequestMapping("touristLogin")
	public Object touristLogin(){
		return new ResponseMessage<Map<String, Object>>(accountApiService.touristLogin());
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="fans", method=RequestMethod.POST)
	public Object fans(@RequestBody CommParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		String userId = photoParam.getUserId();
		if (StringUtils.isEmpty(userId)) {
			userId = photoParam.getUid();
		}
		page.getParams().put("userId", userId);
		page.getParams().put("status", 1);
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(accountApiService.findFans(page));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="myFowllowed", method=RequestMethod.POST)
	public Object myFowllowed(@RequestBody CommParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		String userId = photoParam.getUserId();
		if (StringUtils.isEmpty(userId)) {
			userId = photoParam.getUid();
		}
		page.getParams().put("fansId", userId);
		page.getParams().put("status", 1);
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(accountApiService.findFowllowed(page));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="updatePersonalInfo", method=RequestMethod.POST)
	public Object updatePersonalInfo(@RequestBody CommParam photoParam){
		accountApiService.saveOrUpdateUserInfo(photoParam.getUid(), photoParam.getNickName(), photoParam.getHeadImg(), photoParam.getSign());
		return new ResponseMessage<Object>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="personalInfo", method=RequestMethod.POST)
	public Object personalInfo(@RequestBody CommParam photoParam){
		if (StringUtils.isNotEmpty(photoParam.getUserId())) {
			return new ResponseMessage<Map<String, Object>>(accountApiService.getUserInfo(photoParam.getUserId()));
		}else{
			return new ResponseMessage<Map<String, Object>>(accountApiService.getUserInfo(photoParam.getUid()));
		}
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="balance", method=RequestMethod.POST)
	public Object balance(@RequestBody CommParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.getBalance(photoParam.getUid()));
	}
	
}