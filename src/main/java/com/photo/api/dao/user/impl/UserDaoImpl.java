package com.photo.api.dao.user.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserDao;
import com.photo.api.model.user.User;

@Repository("userDao")
public class UserDaoImpl  extends PageDaoAbstract<User> implements UserDao{

	public User findById(String userId){
		User user = findOne("findById",userId);
		return user;
		
	}

	
	public Integer findCount() {
		return (Integer) find("findCount",null);
	}

	
	public Map<String, Object> findWithMap(Map<String, Object> map) {
		return (Map<String, Object>) find("findWithMap",map);
	}

}
