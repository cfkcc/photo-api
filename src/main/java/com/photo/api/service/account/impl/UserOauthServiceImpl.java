package com.photo.api.service.account.impl;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.photo.api.common.util.CommonUtil;
import com.photo.api.dao.user.UserOauthDao;
import com.photo.api.model.user.UserOauth;
import com.photo.api.service.account.UserOauthService;

@Service("userOauthService")
public class UserOauthServiceImpl implements UserOauthService {
	Logger log = LoggerFactory.getLogger(UserOauthServiceImpl.class);


    @Resource(name="userOauthDao")
    private UserOauthDao userOauthDao;

    
    public UserOauth findUserOauthByOpenId(String oid, int clientType) {
        return userOauthDao.findUserOauthByOpenId(oid,clientType);
    }

    
    public void addUserOauth(String openId, String userId, String accessToken, int clientType) {
        UserOauth userOauth = new UserOauth();
        userOauth.setOauthId(CommonUtil.getUUID());
        userOauth.setOid(openId);
        userOauth.setUserId(userId);
        userOauth.setAccessToken(accessToken);
        userOauth.setType(clientType);
        userOauth.setCreateTime(new Date());
        addUserOauth(userOauth);
    }


	@Override
	public void addUserOauth(UserOauth uo) {
		userOauthDao.add(uo);
	}


	@Override
	public void updateUserOauth(UserOauth uo) {
		userOauthDao.update(uo);		
	}
}
