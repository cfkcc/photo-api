package com.photo.api.controller.photo;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.photo.api.annotation.LoginSign;
import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.param.CommParam;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.util.Page;
import com.photo.api.controller.BaseController;
import com.photo.api.service.account.AccountApiService;
import com.photo.api.service.pay.PayApiService;
import com.photo.api.service.photo.PhotosApiService;
import com.photo.api.service.vesion.VersionApiService;

@RestController
@RequestMapping("photo")
public class PhotoController extends BaseController{
	
	@Resource(name="photosApiService")
	private PhotosApiService photosApiService;
	@Resource(name="accountApiService")
	private AccountApiService accountApiService;
	@Resource(name="payApiService")
	private PayApiService payApiService;
	@Resource(name="versionApiService")
	private VersionApiService versionApiService;
	
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="photoGroup", method=RequestMethod.POST)
	public Object photoGroup(@RequestBody CommParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		if (photoParam.getType()!=null && photoParam.getType()==1) {
			page.getParams().put("isHot", 1);
			if (StringUtils.isNotEmpty(photoParam.getUserId())) {
				page.getParams().put("userId", photoParam.getUserId());
			}
		}
		page.getParams().put("status", 1);
		page.getParams().put("flag", 0);
		page.getParams().put("uid", photoParam.getUid());
		page.getParams().put("abroad", photoParam.getAbroad());
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(photosApiService.findPhotoGroupsByPage(page));
	}
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="photos", method=RequestMethod.POST)
	public Object photos(@RequestBody CommParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		page.getParams().put("userId", photoParam.getUserId());
		page.getParams().put("status", 1);
		page.getParams().put("flag", 0);
		page.getParams().put("uid", photoParam.getUid());
		page.getParams().put("groupId", photoParam.getGroupId());
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(photosApiService.findPhotosByPage(page));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="subscribe", method=RequestMethod.POST)
	public Object subscribe(@RequestBody CommParam photoParam){
 
		boolean isFans = StringUtils.isNotEmpty(photoParam.getStatus())?(photoParam.getStatus().equals("1")?Boolean.TRUE:Boolean.FALSE):Boolean.FALSE;
		accountApiService.saveOrUpdateUserFans(photoParam.getUserId(),photoParam.getUid(),isFans);
		return new ResponseMessage<Object>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="like", method=RequestMethod.POST)
	public Object like(@RequestBody CommParam photoParam){
		accountApiService.saveOrUpdateUserLike(photoParam.getUserId(),photoParam.getUid(),photoParam.getStatus().equals("1")?Boolean.TRUE:Boolean.FALSE);
		return new ResponseMessage<Object>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="choices", method=RequestMethod.POST)
	public Object choices(@RequestBody CommParam photoParam){
		String photoId = photoParam.getPhotoId();
		return new ResponseMessage<Map<String, Object>>(photosApiService.findChoices(photoId));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="buyRecords", method=RequestMethod.POST)
	public Object buyRecords(@RequestBody CommParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		page.getParams().put("status", 1);
		page.getParams().put("groupId", photoParam.getGroupId());
		page.getParams().put("uid", photoParam.getUid());
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(photosApiService.findPhotoBuyRecordsByPage(page));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="buy", method=RequestMethod.POST)
	public Object buy(@RequestBody CommParam photoParam){
		if (photoParam.getChoice()!=null) {
			if (photoParam.getChoice()==0) {
				photosApiService.saveOrUpdateRecord(photoParam.getUid(), photoParam.getId());
			}else{
				photosApiService.saveOrUpdateRecord(photoParam.getUid(), photoParam.getId(), Boolean.FALSE);
			}
		}
		return new ResponseMessage<Object>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@LoginSign(isNeedLogin=false)
	@RequestMapping(value="version", method=RequestMethod.POST)
	public Object version(@RequestBody CommParam photoParam){
		return new ResponseMessage<Map<String, Object>>(versionApiService.findNewVersion("1", photoParam.getSysType(), photoParam.getChannelId()));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="sendCode", method=RequestMethod.POST)
	public Object sendCode(@RequestBody CommParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.getNewCode(photoParam.getUid(), photoParam.getEmail()));
	}
	@LoginSign(isNeedLogin=true)
	@RequestMapping(value="bindEmail", method=RequestMethod.POST)
	public Object bindEmail(@RequestBody CommParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.updateUserEmail(photoParam.getUid(), photoParam.getEmail(), photoParam.getCode()));
	}
}
