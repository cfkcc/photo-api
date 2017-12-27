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

	
	public void addUserEmailCode(UserEmailCode uec) {
		userEmailCodeDao.add(uec);		
	}

	
	public void updatUserEmailCode(UserEmailCode uec) {
		userEmailCodeDao.update(uec);		
	}

	
	public void delete(String[] userId) {
		userEmailCodeDao.delete(userId);		
	}

	
	public UserEmailCode findByParam(String userId, String email) {
		return userEmailCodeDao.findByParam(userId, email);
	}

	
	public UserEmailCode findByCode(String code) {
		return userEmailCodeDao.findByCode(code);
	}


	@Override
	public void commitEmailCode(String email, int status, String code) {
		
	}

}
