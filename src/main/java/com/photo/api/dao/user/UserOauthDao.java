package com.photo.api.dao.user;

import java.util.Map;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.UserOauth;

public interface UserOauthDao extends PageDao<UserOauth>{
	
	public UserOauth findUserOauthByOpenId(String oid,int type);
	
}
