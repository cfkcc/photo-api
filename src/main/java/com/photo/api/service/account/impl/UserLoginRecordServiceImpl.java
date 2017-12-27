package com.photo.api.service.account.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.user.UserLoginRecordDao;
import com.photo.api.model.user.UserLoginRecord;
import com.photo.api.service.account.UserLoginRecordService;

@Service("userLoginRecordService")
public class UserLoginRecordServiceImpl implements UserLoginRecordService {
	@Resource(name="userLoginRecordDao")
	private UserLoginRecordDao userLoginRecordDao;

	@Override
	public void addUserLoginRecord(UserLoginRecord record) {
		userLoginRecordDao.add(record);
	}

	@Override
	public void updateUserLoginRecord(UserLoginRecord record) {
		userLoginRecordDao.update(record);
	}

	@Override
	public void deleteByIds(String[] ids) {
		userLoginRecordDao.delete(ids);		
	}

	@Override
	public UserLoginRecord findById(String id) {
		return userLoginRecordDao.findById(id);
	}

	@Override
	public UserLoginRecord findLatestRerordByUserId(String userId) {
		return userLoginRecordDao.findLatestRerordByUserId(userId);
	}

}
