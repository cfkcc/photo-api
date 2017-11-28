package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserLikeDao;
import com.photo.api.model.user.UserLike;

@Repository("userLikeDao")
public class UserLikeDaoImpl extends PageDaoAbstract<UserLike> implements UserLikeDao {

	
	public UserLike findByUserIdAndLikerId(String userId, String likerId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("likerId", likerId);
		return findOne("findByUserIdAndLikerId", param);
	}

	
	public long findLikeCountByLikerId(String likerId) {
		long rowCount = ((Integer)find("findLikeCountByLikerId", likerId)).longValue();
		return rowCount;
	}

}
