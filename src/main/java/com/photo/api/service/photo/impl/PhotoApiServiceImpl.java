package com.photo.api.service.photo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.photo.api.common.util.Arith;
import com.photo.api.common.util.Page;
import com.photo.api.model.photo.Photo;
import com.photo.api.model.photo.PhotoBuyRecord;
import com.photo.api.model.photo.PhotoGroup;
import com.photo.api.model.user.User;
import com.photo.api.service.account.UserFansService;
import com.photo.api.service.account.UserLikeService;
import com.photo.api.service.account.UserService;
import com.photo.api.service.photo.PhotoBuyRecordService;
import com.photo.api.service.photo.PhotoGroupService;
import com.photo.api.service.photo.PhotoService;
import com.photo.api.service.photo.PhotosApiService;

@Service("photosApiService")
public class PhotoApiServiceImpl implements PhotosApiService {
	
	@Resource(name="photoBuyRecordService")
	private PhotoBuyRecordService photoBuyRecordService;
	@Resource(name="photoGroupService")
	private PhotoGroupService photoGroupService;
	@Resource(name="photoService")
	private PhotoService photoService;
	@Resource(name="userLikeService")
	private UserLikeService userLikeService;
	@Resource(name="userFansService")
	private UserFansService userFansService;
	@Resource(name="userService")
	private UserService userService;

//	
//	public Boolean isBuy(String userId, String photoId) {
//		return photoBuyRecordService.isBuy(userId, photoId);
//	}

	
	public Boolean getPurchasingPower(String userId, String[] photoIds) {
		//默认金币不够提示用户去充值
		boolean isCan = Boolean.FALSE;
		User user = userService.findUserById(userId);
		if (user != null) {
			double userTotalCoins = user.getCoins().doubleValue();
			if (photoIds!=null&&photoIds.length>0) {
				String photoId = photoIds[0];
				Photo p = photoService.findById(photoId);
				double price = Arith.mul(p.getCoins().doubleValue(), 1-p.getDiscount(), 2);
				double userCoins = Arith.sub(userTotalCoins, price);
				if(userCoins>0){
					//金币充足,扣除用户的金币
					user.setCoins(BigDecimal.valueOf(userCoins));
					userService.updateUser(user);
					isCan = Boolean.TRUE;
				}
			}
		}
		return isCan;
	}

	
	public Boolean getPurchasingPower(String userId, String photoGroupId) {
		//默认金币不够提示用户去充值	
		boolean isCan = Boolean.FALSE;
		User user = userService.findUserById(userId);
		if (user != null) {
			double userTotalCoins = user.getCoins().doubleValue();
			if (StringUtils.isNotBlank(photoGroupId)) {
				PhotoGroup pg = photoGroupService.findById(photoGroupId);
				double price = Arith.mul(pg.getCoins().doubleValue(), 1-pg.getDiscount(), 2);
				double userCoins = Arith.sub(userTotalCoins, price);
				if(userCoins>0){
					//金币充足,扣除用户的金币
					user.setCoins(BigDecimal.valueOf(userCoins));
					userService.updateUser(user);
					isCan = Boolean.TRUE;
				}
			}
		}
		return isCan;
	}

	public Map<String, Object> findPhotosByPage(Page page) {
		String uid = page.getParams().get("uid")!=null?page.getParams().get("uid").toString():"";
		page = photoService.findByPage(page);
		long totalCount = page.getRowCount();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalCount", totalCount);
		result.put("totalPage", page.getPageCount());
		List<Map<String, Object>> photoList = new ArrayList<Map<String, Object>>();
		Iterator<Photo> it = (Iterator<Photo>) page.getRecords().iterator();
		if(it != null){
			double totalCoins = 0;
			while(it.hasNext()){
				Photo p = it.next();
				Map<String, Object> photoMap = new HashMap<String, Object>();
				photoMap.put("discount", Arith.round(1-p.getDiscount(),2));
				photoMap.put("photoId", p.getPhotoId());
				photoMap.put("imgUrl", p.getImgUrl());
				photoMap.put("origPrice", p.getCoins());
				photoMap.put("publishTime", p.getCreateTime());
				double price = Arith.mul(p.getCoins().doubleValue(), 1-p.getDiscount(), 2);
				photoMap.put("price", price);
				
				User user = userService.findUserById(p.getUserId());
				boolean isFollowed = Boolean.FALSE;
				boolean isLike = Boolean.FALSE;
				if (user != null) {
					photoMap.put("nickName", user.getNickname());
					photoMap.put("userId", user.getUserId());
					photoMap.put("headImg", user.getHeadImg());
					photoMap.put("nickName", user.getNickname());
					isFollowed = userFansService.isFans(user.getUserId(), uid);
					isLike = userLikeService.isLike(user.getUserId(), uid);
				}
				boolean isBuy = photoBuyRecordService.isBuy(uid, p.getPhotoId());
				boolean isFree = p.getIsFree()==Photo.Free.No.getStatus();
				if (!isBuy&&isFree) {
					totalCoins = Arith.add(totalCoins, p.getCoins().doubleValue());
				}
				photoMap.put("isFree", isFree);
				photoMap.put("isFollowed", isFollowed);
				photoMap.put("isLike", isLike);
				photoMap.put("isBuy", isBuy);
				//此处添加分享的跳转次数
				photoMap.put("shareCount", 18238);
				
				photoList.add(photoMap);
			}
			result.put("totalCoins", totalCoins);
			if (!photoList.isEmpty()) {
				result.put("list", photoList);
			}
		}
		return result;
	}

	
	public Map<String, Object> findPhotoGroupsByPage(Page page) {
		String uid = page.getParams().get("uid")!=null?page.getParams().get("uid").toString():"";
		page = photoGroupService.findByPage(page);
		long totalCount = page.getRowCount();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalCount", totalCount);
		result.put("totalPage", page.getPageCount());
		
		List<Map<String, Object>> photoGroupList = new ArrayList<Map<String, Object>>();
		Iterator<PhotoGroup> it = (Iterator<PhotoGroup>) page.getRecords().iterator();
		if(it != null){
			while(it.hasNext()){
				Map<String, Object> photoGroupMap = new HashMap<String, Object>();
				List<Map<String, Object>> photoList = new ArrayList<Map<String, Object>>();
				PhotoGroup pg = it.next();
				photoGroupMap.put("groupId", pg.getGroupId());
				photoGroupMap.put("discount", Arith.round(1-pg.getDiscount(),2));
				photoGroupMap.put("origPrice", pg.getCoins());
				photoGroupMap.put("publishTime", this.publishTimeFormat(pg.getCreateTime()));
				double price = Arith.mul(pg.getCoins().doubleValue(), 1-pg.getDiscount(), 2);
				photoGroupMap.put("price", price);
				
				
				User user = userService.findUserById(pg.getUserId());
				boolean isFollowed = Boolean.FALSE;
				boolean isLike = Boolean.FALSE;
				if (user != null) {
					photoGroupMap.put("nickName", user.getNickname());
					photoGroupMap.put("userId", user.getUserId());
					photoGroupMap.put("headImg", user.getHeadImg());
					photoGroupMap.put("nickName", user.getNickname());
					isFollowed = userFansService.isFans(user.getUserId(), uid);
					isLike = userLikeService.isLike(user.getUserId(), uid);
				}
				photoGroupMap.put("isFollowed", isFollowed);
				photoGroupMap.put("isLike", isLike);
				
				
				Page photoPage = new Page();
				photoPage.setPageNo(1);
				photoPage.setPageSize(3);
				photoPage.setOrderBy(" sort asc");
				photoPage.getParams().put("groupId", pg.getGroupId());
				photoPage.getParams().put("status", 1);
				photoPage.getParams().put("flag", 0);
				photoPage.getParams().put("uid", uid);
				Map<String, Object> photoListmap = this.findPhotosByPage(photoPage);
				if (!photoListmap.isEmpty()) {
					double totalCoins = Double.valueOf(photoListmap.get("totalCoins").toString());
					photoGroupMap.put("photoList", photoListmap.get("list"));
					photoGroupMap.put("totalCount", photoListmap.get("totalCount"));
					photoGroupMap.put("totalPage", photoListmap.get("totalPage"));
					photoGroupMap.put("totalCoins", totalCoins);
				}
				//此处添加分享的跳转次数
				photoGroupMap.put("shareCount", 18238);
				photoGroupList.add(photoGroupMap);
			}
			result.put("list", photoGroupList);
		}
		return result;
	}

	
	public boolean saveOrUpdateRecord(String userId, String photoId) {
		return saveOrUpdateRecord(userId, photoId, Boolean.TRUE);
	}

	
	public boolean saveOrUpdateRecord(String userId, String photoId, Boolean isSingle) {
		boolean flag = Boolean.FALSE;
		if (isSingle) {
			if (getPurchasingPower(userId, new String[]{photoId})) {
				//添加购买记录
				photoBuyRecordService.saveOrUpdateRecord(userId, photoId, isSingle);
				flag = Boolean.TRUE;
			}
		}else{
			if (getPurchasingPower(userId, photoId)) {
				//添加购买记录
				List<String> photoIdList = photoService.findPhotoIdsByGroupId(photoId);
				if (!photoIdList.isEmpty()) {
					String [] photoIds = photoIdList.toArray(new String[photoIdList.size()]);
					photoBuyRecordService.saveOrUpdateRecord(userId, photoIds, isSingle);
					flag = Boolean.TRUE;
				}
			}
		}
		return flag;
	}
	

	
	public Map<String, Object> findPhotoBuyRecordsByPage(Page page) {
		page = photoBuyRecordService.findByPage(page);
		long totalCount = page.getRowCount();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("totalCount", totalCount);
		result.put("totalPage", page.getPageCount());
		
		List<Map<String, Object>> photoGroupList = new ArrayList<Map<String, Object>>();
		Iterator<PhotoBuyRecord> it = (Iterator<PhotoBuyRecord>) page.getRecords().iterator();
		if (it != null) {
			while(it.hasNext()){
				PhotoBuyRecord pbr = it.next();
				String photoId = pbr.getPhotoId();
				Photo photo = photoService.findById(photoId);
				Map<String, Object> userMap = userService.findUserInfoById(photo.getUserId());
				userMap.remove("conins");
				userMap.remove("sign");
				userMap.remove("email");
				Map<String, Object> recordMap = new HashMap<String, Object>();
				recordMap.putAll(userMap);
				recordMap.put("buyDay", publishTimeFormat(pbr.getCreateTime()));
				recordMap.put("choice", pbr.getChoice());
				photoGroupList.add(recordMap);
			}
			result.put("list", photoGroupList);
		}
		return result;
	}
	
	private String publishTimeFormat(Date date){
		Date now = new Date();
		StringBuffer sb = new StringBuffer();
		long l=now.getTime()-date.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
//		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		
		Boolean flag = Boolean.FALSE;
		if(day>0&&!flag){
			sb.append(day+"天前");
			flag = Boolean.TRUE;
		}
		if(hour>0&&!flag){
			sb.append(hour+"小时前");
			flag = Boolean.TRUE;
		}
		if(min>0&&!flag){
			sb.append(min+"分前");
			flag = Boolean.TRUE;
		}
		if(!flag){
			sb.append("刚刚");
			flag = Boolean.TRUE;
		}
		return sb.toString();
	}

	
	public Map<String, Object> findChoices(String photoId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Photo p = photoService.findById(photoId);
		if (p != null) {
			Map<String, Object> singleMap = new HashMap<String, Object>();
			double singlePrice = Arith.mul(p.getCoins().doubleValue(), 1-p.getDiscount(), 2);
			if (p.getDiscount()!=0) {
				singleMap.put("origPrice", Arith.round(p.getCoins().doubleValue(), 2));
			}
			singleMap.put("price", singlePrice);
			singleMap.put("choice", PhotoBuyRecord.Choice.Single.getChoices());
			singleMap.put("photoId", photoId);
			result.put("single", singleMap);
			PhotoGroup pg = photoGroupService.findById(p.getGroupId());
			if (pg != null) {
				Map<String, Object> groupMap = new HashMap<String, Object>();
				double groupPrice = Arith.mul(pg.getCoins().doubleValue(), 1-pg.getDiscount(), 2);
				if (pg.getDiscount()!=0) {
					groupMap.put("origPrice", Arith.round(pg.getCoins().doubleValue(), 2));
				}
				groupMap.put("choice", PhotoBuyRecord.Choice.Group.getChoices());
				groupMap.put("price", groupPrice);
				groupMap.put("groupId", pg.getGroupId());
				result.put("group", groupMap);
			}
		}
		return result;
	}

}
