package com.photo.api.dao.user;

import java.util.Map;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.User;

public interface UserDao extends PageDao<User>{
	public User findById(String userId);
	
	public Integer findCount();
	
	public Map<String, Object> findWithMap(Map<String, Object> map);
	
}
