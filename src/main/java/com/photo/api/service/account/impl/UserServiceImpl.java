package com.photo.api.service.account.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

    
    public User findUserById(String userId) {
        return userDao.findById(userId);
    }

    public void updateUser(User user) {
    	userDao.update(user);
    }

	
	public void addUser(User user) {
		userDao.add(user);
	}
	
    public void saveOrUpdateUserInfo(String userId, String nickName, String headImage, String sign) {
    	User user = null;
    	if (StringUtils.isNotEmpty(userId)) {
			user = this.findUserById(userId);
			if (StringUtils.isNotEmpty(nickName)) {
				user.setNickName(nickName);
			}
			if (StringUtils.isNotEmpty(headImage)) {
				user.setHeadImg(headImage);;
			}
			if (StringUtils.isNotEmpty(sign)) {
				user.setSign(sign);;
			}
			this.updateUser(user);
		}else{
	        user = new User();
	        user.setUserId(CommonUtil.getUUID());
	        if (StringUtils.isNotEmpty(nickName)) {
				user.setNickName(nickName);
			}
			if (StringUtils.isNotEmpty(headImage)) {
				user.setHeadImg(headImage);;
			}
			if (StringUtils.isNotEmpty(sign)) {
				user.setSign(sign);;
			}
	        user.setUserType(0);
	        user.setCreateTime(new Date());
	        this.addUser(user);
		}
    }
	
	public Map<String, Object> findUserInfoById(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.findUserById(userId);
		if (user != null) {
			result.put("userId", user.getUserId());
			result.put("headImg", user.getHeadImg());
			result.put("nickName", user.getNickName());
			result.put("sign", user.getSign());
			result.put("email", user.getEmail());
			result.put("conins", Arith.round(user.getCoins().doubleValue(), 2));
		}
		return result;
	}

	@Override
	public User findByNickName(String nickName) {
		return userDao.findByNickName(nickName);
	}

	@Override
	public boolean haveNickName(String userId, String nickName) {
		return userDao.haveNickName(userId, nickName);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

}
