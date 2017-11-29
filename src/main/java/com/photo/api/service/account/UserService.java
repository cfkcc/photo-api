package com.photo.api.service.account;

import java.util.Map;

import com.photo.api.model.user.User;

public interface UserService {
	
    User findUserById(String userId);

    void updateUser(User user);
    
    void addUser(User user);
    User addUser(String nickName, String headImage, String sign);
    
    void saveOrUpdateUserInfo(String userId, String nickName, String headImage, String sign);
    
    Map<String, Object> findUserInfoById(String userId);
}
