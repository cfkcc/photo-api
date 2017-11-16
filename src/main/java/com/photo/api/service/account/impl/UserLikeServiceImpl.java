package com.photo.api.service.account.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.user.UserLikeDao;
import com.photo.api.model.user.UserLike;
import com.photo.api.service.account.UserLikeService;

@Service("userLikeService")
public class UserLikeServiceImpl implements UserLikeService {
	
	@Resource(name="userLikeDao")
	private UserLikeDao userLikeDao;

	@Override
	public Boolean isLike(String userId, String likerId) {
		Boolean isLike = Boolean.FALSE;
		UserLike uk = this.findByUserIdAndLikerId(userId, likerId);
		if (uk != null) {
			isLike = uk.getStatus()==UserLike.Status.Like.getStatus()?Boolean.TRUE:Boolean.FALSE;
		}
		return isLike;
	}

	private void addUserLike(UserLike uk) {
		userLikeDao.add(uk);
	}
	
	private void updateUserLike(UserLike uk){
		userLikeDao.update(uk);
	}
	
	private UserLike findByUserIdAndLikerId(String userId, String likerId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("likerId", likerId);
		return userLikeDao.findOne("findByUserIdAndLikerId", param);
	}

	@Override
	public void saveOrUpdateUserLike(String userId, String likerId, Boolean isLike) {
		UserLike uk = this.findByUserIdAndLikerId(userId, likerId);
		Integer status = isLike?UserLike.Status.Like.getStatus():UserLike.Status.Unlike.getStatus();
		if (uk != null) {
			uk.setStatus(status);
			uk.setUpdateTime(new Date());
			this.updateUserLike(uk);
		}else{
			uk = new UserLike();
			uk.setUpdateTime(new Date());
			uk.setCreateTime(new Date());
			uk.setStatus(status);
			uk.setLikerId(likerId);
			uk.setUserId(userId);
			this.addUserLike(uk);
		}
	}

	@Override
	public long findLikeCountByLikerId(String likerId) {
		return userLikeDao.findLikeCountByLikerId(likerId);
	}

}
