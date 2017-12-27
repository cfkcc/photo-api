package com.photo.api.dao.user;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.User;

public interface UserDao extends PageDao<User>{
	/**
	 * 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 */
	public User findById(String userId);
	
	/**
	 * 根据用户昵称获取用户信息
	 * @param nickName
	 * @return
	 */
	public User findByNickName(String nickName);
	/**
	 * 根据用的email获取用户信息
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email);
	/**
	 * 判断是否有此昵称
	 * @param param
	 * @return
	 */
	public boolean haveNickName(String userId, String nickName);
	
}
