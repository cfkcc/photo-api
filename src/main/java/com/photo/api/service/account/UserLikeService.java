package com.photo.api.service.account;

public interface UserLikeService {
	/**
	 * 判断是否点过赞，默认未点赞
	 * @param userId
	 * @param likerId
	 * @return
	 */
	public Boolean isLike(String userId, String likerId);
	/**
	 * 保存点赞记录状态
	 * @param userId
	 * @param likerId
	 * @param isLike
	 */
	public void saveOrUpdateUserLike(String userId, String likerId, Boolean isLike);
	/**
	 * 获取被点在的用户的被点赞数量
	 * @param likerId
	 * @return
	 */
	public long findLikeCountByLikerId(String likerId);

}
