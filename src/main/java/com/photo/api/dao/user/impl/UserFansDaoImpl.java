package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserFansDao;
import com.photo.api.model.user.UserFans;

@Repository("userFansDao")
public class UserFansDaoImpl extends PageDaoAbstract<UserFans> implements UserFansDao {

	@Override
	public UserFans findByUserIdAndFansId(String userId, String fansId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("fansId", fansId);
		return findOne("findByUserIdAndFansId", param);
	}

	@Override
	public long findCountByUserId(String userId) {
		long rowCount = ((Integer)find("findCountByUserId", userId)).longValue();
		return rowCount;
	}

	@Override
	public long findFansCountByFansId(String fansId) {
		long rowCount = ((Integer)find("findFansCountByFansId", fansId)).longValue();
		return rowCount;
	}


}
