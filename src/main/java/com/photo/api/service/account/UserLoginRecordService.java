package com.photo.api.service.account;

import com.photo.api.model.user.UserLoginRecord;

public interface UserLoginRecordService {
	/**
	 * 添加用户登录记录
	 * @param record
	 */
	public void addUserLoginRecord(UserLoginRecord record);
	/**
	 * 修改用户登录记录
	 * @param record
	 */
	public void updateUserLoginRecord(UserLoginRecord record);
	/**
	 * 根据id删除用户登录记录
	 * @param ids
	 */
	public void deleteByIds(String[] ids);
	/**
	 * 根据ID获取用户登录记录
	 * @param id
	 * @return
	 */
	public UserLoginRecord findById(String id);
	/**
	 * 根据用户id获取用户的最新的登录记录
	 * @param userId
	 * @return
	 */
	public UserLoginRecord findLatestRerordByUserId(String userId);

}
