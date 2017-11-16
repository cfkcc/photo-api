package com.photo.api.service.photo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

	@Override
	public Boolean isBuy(String userId, String photoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getPurchasingPower(String userId, String[] photoIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getPurchasingPower(String userId, String photoGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
				photoMap.put("isFollowed", isFollowed);
				photoMap.put("isLike", isLike);
				photoMap.put("sale", Arith.round(1-p.getSale(),2));
				photoMap.put("isFree", isFree);
				photoMap.put("photoId", p.getPhotoId());
				photoMap.put("imgUrl", p.getImgUrl());
				photoMap.put("isBuy", isBuy);
				photoMap.put("coins", p.getCoins());
				photoMap.put("publishTime", p.getCreateTime());
				double price = Arith.mul(p.getCoins().doubleValue(), 1-p.getSale(), 2);
				photoMap.put("price", price);
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

	@Override
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
				photoGroupMap.put("groupId", pg.getGroupId());
				photoGroupMap.put("sale", Arith.round(1-pg.getSale(),2));
				photoGroupMap.put("coins", pg.getCoins());
				photoGroupMap.put("publishTime", this.publishTimeFormat(pg.getCreateTime()));
				
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
					double price = Arith.mul(pg.getCoins().doubleValue(), 1-pg.getSale(), 2);
					photoGroupMap.put("price", price);
				}
				//此处添加分享的跳转次数
				photoGroupMap.put("shareCount", 18238);
				photoGroupList.add(photoGroupMap);
			}
			result.put("list", photoGroupList);
		}
		return result;
	}

	@Override
	public void saveOrUpdateRecord(String userId, String photoId, Boolean isBuy) {
		photoBuyRecordService.saveOrUpdateRecord(userId, photoId, isBuy);		
	}

	@Override
	public void saveOrUpdateRecord(String userId, String photoId, Boolean isBuy, Boolean isSingle) {
		photoBuyRecordService.saveOrUpdateRecord(userId, photoId, isBuy, isSingle);		
	}
	

	@Override
	public Map<String, Object> findPhotoBuyRecordsByPage(Page page) {
		String uid = page.getParams().get("uid")!=null?page.getParams().get("uid").toString():"";
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
				
			}
		}
		return null;
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

}
