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

    
    public UserOauth findUserOauthByOpenId(String openId, int clientType) {
        return userOauthDao.findUserOauthByOpenId(openId,clientType);
    }

    
    public void addUserOauth(String openId, String userId, String accessToken, int clientType) {
        UserOauth userOauth = new UserOauth();
        userOauth.setBingId(CommonUtil.getUUID());
        userOauth.setOpenId(openId);
        userOauth.setUserId(userId);
        userOauth.setAccessToken(accessToken);
        userOauth.setClientType(clientType);
        userOauth.setCreateTime(new Date());
        userOauthDao.add(userOauth);
    }
    


}
