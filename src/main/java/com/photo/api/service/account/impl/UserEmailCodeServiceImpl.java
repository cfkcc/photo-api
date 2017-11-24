package com.photo.api.service.account.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.user.UserEmailCodeDao;
import com.photo.api.model.user.UserEmailCode;
import com.photo.api.service.account.UserEmailCodeService;

@Service("userEmailCodeService")
public class UserEmailCodeServiceImpl implements UserEmailCodeService {
	
	@Resource(name="userEmailCodeDao")
	private UserEmailCodeDao userEmailCodeDao;

	@Override
	public void addUserEmailCode(UserEmailCode uec) {
		userEmailCodeDao.add(uec);		
	}

	@Override
	public void updatUserEmailCode(UserEmailCode uec) {
		userEmailCodeDao.update(uec);		
	}

	@Override
	public void delete(String[] userId) {
		userEmailCodeDao.delete(userId);		
	}

	@Override
	public UserEmailCode findByParam(String userId, String email) {
		return userEmailCodeDao.findByParam(userId, email);
	}

	@Override
	public UserEmailCode findByCode(String code) {
		return userEmailCodeDao.findByCode(code);
	}

}
