package com.photo.api.controller.photo;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photo.api.common.error.ErrorCode;
import com.photo.api.common.result.ResponseMessage;
import com.photo.api.common.util.Page;
import com.photo.api.controller.BaseController;
import com.photo.api.controller.param.PhotoParam;
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
	
	@RequestMapping("photoGroup")
	public Object photoGroup(PhotoParam photoParam){
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
	@RequestMapping("photos")
	public Object photos(PhotoParam photoParam){
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
	@RequestMapping("subscribe")
	public Object subscribe(PhotoParam photoParam){
		accountApiService.saveOrUpdateUserFans(photoParam.getUserId(),photoParam.getUid(),photoParam.getStatus().equals("1")?Boolean.TRUE:Boolean.FALSE);
		return new ResponseMessage<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@RequestMapping("like")
	public Object like(PhotoParam photoParam){
		accountApiService.saveOrUpdateUserLike(photoParam.getUserId(),photoParam.getUid(),photoParam.getStatus().equals("1")?Boolean.TRUE:Boolean.FALSE);
		return new ResponseMessage<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@RequestMapping("choices")
	public Object choices(PhotoParam photoParam){
		String photoId = photoParam.getPhotoId();
		return new ResponseMessage<Map<String, Object>>(photosApiService.findChoices(photoId));
	}
	@RequestMapping("balance")
	public Object balance(PhotoParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.getBalance(photoParam.getUid()));
	}
	@RequestMapping("updatePersonalInfo")
	public Object updatePersonalInfo(PhotoParam photoParam){
		accountApiService.saveOrUpdateUserInfo(photoParam.getUid(), photoParam.getNickName(), photoParam.getHeadImg(), photoParam.getSign());
		return new ResponseMessage<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@RequestMapping("personalInfo")
	public Object personalInfo(PhotoParam photoParam){
		if (StringUtils.isNotEmpty(photoParam.getUserId())) {
			return new ResponseMessage<Map<String, Object>>(accountApiService.getUserInfo(photoParam.getUserId()));
		}else{
			return new ResponseMessage<Map<String, Object>>(accountApiService.getUserInfo(photoParam.getUid()));
		}
	}
	@RequestMapping("records")
	public Object records(PhotoParam photoParam){
		Page page = new Page();
		page.setPageNo(photoParam.getPageIndex());
		page.setPageSize(photoParam.getPageSize());
		page.getParams().put("status", 1);
		page.getParams().put("groupId", photoParam.getGroupId());
		page.getParams().put("uid", photoParam.getUid());
		page.setOrderBy(" create_time desc");
		return new ResponseMessage<Map<String, Object>>(photosApiService.findPhotoBuyRecordsByPage(page));
	}
	
	@RequestMapping("buy")
	public Object buy(PhotoParam photoParam){
		if (photoParam.getChoice()!=null) {
			if (photoParam.getChoice()==0) {
				photosApiService.saveOrUpdateRecord(photoParam.getUid(), photoParam.getId());
			}else{
				photosApiService.saveOrUpdateRecord(photoParam.getUid(), photoParam.getId(), Boolean.FALSE);
			}
		}
		return new ResponseMessage<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
	}
	@RequestMapping("fans")
	public Object fans(PhotoParam photoParam){
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
	@RequestMapping("myFowllowed")
	public Object myFowllowed(PhotoParam photoParam){
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
	@RequestMapping("productList")
	public Object productList(PhotoParam photoParam){
		String channelId = photoParam.getChannelId();
		return new ResponseMessage<Map<String, Object>>(payApiService.findProductsByChannelId(channelId));
	}
	@RequestMapping("version")
	public Object version(PhotoParam photoParam){
		return new ResponseMessage<Map<String, Object>>(versionApiService.findNewVersion("1", photoParam.getSysType(), photoParam.getChannel()));
	}
	@RequestMapping("sendCode")
	public Object sendCode(PhotoParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.getNewCode(photoParam.getUid(), photoParam.getEmail()));
	}
	@RequestMapping("bindEmail")
	public Object bindEmail(PhotoParam photoParam){
		return new ResponseMessage<Map<String, Object>>(accountApiService.updateUserEmail(photoParam.getUid(), photoParam.getEmail(), photoParam.getCode()));
	}
}
