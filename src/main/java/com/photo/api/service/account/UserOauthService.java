package com.photo.api.service.account;

import com.photo.api.model.user.UserOauth;

public interface UserOauthService {
	
	UserOauth findUserOauthByOpenId(String openId,int clientType);

    void addUserOauth(String openId,String userId,String accessToken,int clientType);
}
