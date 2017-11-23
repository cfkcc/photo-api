package com.photo.api.service.account.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.photo.api.common.util.Arith;
import com.photo.api.common.util.CommonUtil;
import com.photo.api.dao.user.UserDao;
import com.photo.api.model.user.User;
import com.photo.api.service.account.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userDao")
	private UserDao userDao;

    @Override
    public User findUserById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public void updateUser(User user) {
    	userDao.update(user);
    }

	@Override
	public void addUser(User user) {
		userDao.add(user);
	}
	
	 @Override
    public User addUser(String nickname, String headImg, int userType) {
        User user = new User();
        user.setUserId(CommonUtil.getUUID());
        user.setNickname(nickname);
        user.setHeadImg(headImg);
        user.setUserType(userType);
        user.setCreateTime(new Date());
        userDao.add(user);
        return user;
    }

	@Override
	public Map<String, Object> findUserInfoById(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.findUserById(userId);
		if (user != null) {
			result.put("userId", user.getUserId());
			result.put("headImg", user.getHeadImg());
			result.put("nickName", user.getNickname());
			result.put("sign", user.getSign());
			result.put("conins", Arith.round(user.getCoins().doubleValue(), 2));
		}
		return result;
	}
	 
}
