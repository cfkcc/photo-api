package com.photo.api.dao.user;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.UserFans;

public interface UserFansDao extends PageDao<UserFans>{
	/**
	 * 获取用户的关注记录
	 * @param userId
	 * @param fansId
	 * @return
	 */
	public UserFans findByUserIdAndFansId(String userId, String fansId);
	/**
	 * 获取我关注的用户的数量
	 * @param userId
	 * @return
	 */
	public long findCountByUserId(String userId);
	/**
	 * 获取被关注的用户的被关注数量
	 * @param fansId
	 * @return
	 */
	public long findFansCountByFansId(String fansId);

}
