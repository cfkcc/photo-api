package com.photo.api.dao.user;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.UserLike;

public interface UserLikeDao extends PageDao<UserLike>{
	
	/**
	 * 获取用户的点赞记录
	 * @param userId
	 * @param likerId
	 * @return
	 */
	public UserLike findByUserIdAndLikerId(String userId, String likerId);
	/**
	 * 根据被点赞者的ID获取其被点赞的数量
	 * @param likerId
	 * @return
	 */
	public long findLikeCountByLikerId(String likerId);
}
