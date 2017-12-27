package com.photo.api.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserLoginRecordDao;
import com.photo.api.model.user.UserLoginRecord;

@Repository("userLoginRecordDao")
public class UserLoginRecordDaoImpl extends PageDaoAbstract<UserLoginRecord> implements UserLoginRecordDao {

	@Override
	public void deleteByIds(String[] ids) {
		delete(ids);
	}

	@Override
	public UserLoginRecord findLatestRerordByUserId(String userId) {
		return findOne("findLatestRerordByUserId", userId);
	}

	@Override
	public UserLoginRecord findById(String loginRecordId) {
		return findOne("findById", loginRecordId);
	}


}
