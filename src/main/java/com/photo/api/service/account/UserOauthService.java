package com.photo.api.service.account;

import com.photo.api.model.user.UserOauth;

public interface UserOauthService {
	/**
	 * 根据用户的第三方openId 和 类型获取用户的授权信息
	 * @param oId
	 * @param type
	 * @return
	 */
	public UserOauth findUserOauthByOpenId(String oid,int type);
	/**
	 * 添加用户的授权凭证
	 * @param openId
	 * @param userId
	 * @param accessToken
	 * @param clientType
	 */
    public void addUserOauth(String openId,String userId,String accessToken,int clientType);
    /**
     * 添加用户的授权凭证
     * @param uo
     */
    public void addUserOauth(UserOauth uo);
    /**
     * 更新用户的授权凭证
     * @param uo
     */
    public void updateUserOauth(UserOauth uo);
}
