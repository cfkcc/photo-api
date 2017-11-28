package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserFansDao;
import com.photo.api.model.user.UserFans;

@Repository("userFansDao")
public class UserFansDaoImpl extends PageDaoAbstract<UserFans> implements UserFansDao {

	
	public UserFans findByUserIdAndFansId(String userId, String fansId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("fansId", fansId);
		return findOne("findByUserIdAndFansId", param);
	}

	
	public long findCountByUserId(String userId) {
		long rowCount = ((Integer)find("findCountByUserId", userId)).longValue();
		return rowCount;
	}

	
	public long findFansCountByUserId(String fansId) {
		long rowCount = ((Integer)find("findFansCountByUserId", fansId)).longValue();
		return rowCount;
	}


}
