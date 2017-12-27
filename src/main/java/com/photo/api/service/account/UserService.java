package com.photo.api.service.account;

import java.util.List;
import java.util.Map;

import com.photo.api.model.user.User;

public interface UserService {
	/**
	 * 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 */
    User findUserById(String userId);
    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);
    /**
     * 添加新用户
     * @param user
     */
    void addUser(User user);
    /**
     * 
     * @param nickName
     * @param headImage
     * @param sign
     * @return
     */
//    User addUser(String nickName, String headImage, String sign);
    void saveOrUpdateUserInfo(String userId, String nickName, String headImage, String sign);
    Map<String, Object> findUserInfoById(String userId);
    User findByNickName(String nickName);
    User findUserByEmail(String email);
    boolean haveNickName(String userId, String nickName);
//    void updatePassword(Map<String, Object> param);
//    List<User> findAccountListBySearchKey(String searchKey, int pageIndex, int pageSize);
}
