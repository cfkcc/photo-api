package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserOauthDao;
import com.photo.api.model.user.UserOauth;

@Repository("userOauthDao")
public class UserOauthDaoImpl extends PageDaoAbstract<UserOauth> implements UserOauthDao {

	@Override
	public UserOauth findUserOauthByOpenId(String openId, int clientType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("openId", openId);
		param.put("clientType", clientType);
		return findOne("findUserOauthByOpenId", param);
	}

}
