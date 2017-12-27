package com.photo.api.dao.user;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.UserLoginRecord;

public interface UserLoginRecordDao extends PageDao<UserLoginRecord>{

	/**
	 * 根据id删除用户登录记录
	 * @param ids
	 */
	public void deleteByIds(String[] ids);
	/**
	 * 根据用户id获取用户的最新的登录记录
	 * @param userId
	 * @return
	 */
	public UserLoginRecord findLatestRerordByUserId(String userId);
	/**
	 * 根据主键获取用户登录记录
	 * @param id
	 * @return
	 */
	public UserLoginRecord findById(String id);
}
