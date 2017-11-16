package com.photo.api.service.account.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	 
}
