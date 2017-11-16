package com.photo.api.service.account;

import com.photo.api.common.util.Page;

public interface UserFansService {
	/**
	 * 判断是否被关注，默认未关注
	 * @param userId 被关注者
	 * @param fansId 关注者
	 * @return
	 */
	public Boolean isFans(String userId, String fansId);
	/**
	 * 保存关注记录状态
	 * @param userId 被关注者ID
	 * @param fansId 关注者
	 * @param isFans
	 */
	public void saveOrUpdateUserFans(String userId, String fansId, Boolean isFans);
	/**
	 * 获取我关注的用户的数量
	 * @param userId
	 * @return
	 */
	public long findCountByUserId(String fansId);
	/**
	 * 获取被关注的用户的被关注数量
	 * @param fansId
	 * @return
	 */
	public long findFansCountByFansId(String userId);
	/**
	 * 粉丝分页查询
	 * @param page
	 * @return
	 */
	public Page findFansByPage(String userId, Integer pageIndex, Integer pageSize, String sorting);
	/**
	 * 我关注的用户分页查询
	 * @param page
	 * @return
	 */
	public Page findUsersByPage(String fansId, Integer pageIndex, Integer pageSize, String sorting);

}
