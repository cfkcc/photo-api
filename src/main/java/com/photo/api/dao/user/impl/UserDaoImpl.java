package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserDao;
import com.photo.api.model.user.User;

@Repository("userDao")
public class UserDaoImpl  extends PageDaoAbstract<User> implements UserDao{

	@Override
	public User findById(String userId){
		User user = findOne("findById",userId);
		return user;
	}
	
	@Override
	public User findByNickName(String nickName) {
		return findOne("findByNickName", nickName);
	}


	@Override
	public User findUserByEmail(String email) {
		return findOne("findUserByEmail", email);
	}

	@Override
	public boolean haveNickName(String userId, String nickName) {
		boolean isExist = Boolean.FALSE;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("nickName", nickName);
		User user = this.findOne("findWithUserIdAndName", param);
		if (user!=null) {
			isExist = Boolean.TRUE;
		}
		return isExist;
	}

}
