package com.photo.api.service.account;

import java.util.Map;

import com.photo.api.model.user.User;

public interface UserService {
	
    User findUserById(String userId);

    void updateUser(User user);
    
    void addUser(User user);
    
    User addUser(String nickname,String headImg,int userType);
    
    Map<String, Object> findUserInfoById(String userId);
}
