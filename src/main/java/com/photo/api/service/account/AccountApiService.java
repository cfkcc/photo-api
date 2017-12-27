package com.photo.api.service.account;

import java.util.Map;

import com.photo.api.common.enums.LoginType;
import com.photo.api.common.util.Page;
import com.photo.api.model.user.User;
import com.photo.api.model.user.UserOauth;

public interface AccountApiService {
	/**
	 * 根据用户Id获取用户的金币数
	 * @param userId
	 * @return
	 */
    public Map<String, Object> getBalance(String userId);
    /**
     * 分页获取用的粉丝
     * @param page
     * @return
     */
    public Map<String, Object> findFans(Page page);
    /**
     * 分页获取用户关注的人
     * @param page
     * @return
     */
    public Map<String, Object> findFowllowed(Page page);
    /**
     * 根据用户的ID获取用户的信息
     * @param userId
     * @return
     */
    public Map<String, Object> getUserInfo(String userId);
    /**
     * 用户邮箱绑定
     * @param userId 用户Id
     * @param email 用户的email
     * @param code 用户的绑定code
     */
    public Map<String, Object> updateUserEmail(String userId, String email, String code);
    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    public Map<String, Object> getNewCode(String userId, String email);
	/**
	 * 保存关注记录状态
	 * @param userId 被关注者ID
	 * @param fansId 关注者
	 * @param isFans
	 */
	public void saveOrUpdateUserFans(String userId, String fansId, Boolean isFans);
	/**
	 * 保存点赞记录状态
	 * @param userId
	 * @param likerId
	 * @param isLike
	 */
	public void saveOrUpdateUserLike(String userId, String likerId, Boolean isLike);
	/**
	 * 修改用户信息
	 * @param userId
	 * @param nickName
	 * @param headImage
	 * @param sign
	 */
	public void saveOrUpdateUserInfo(String userId, String nickName, String headImage, String sign);
	
	
	/**
     * 根据昵称匹配用户
     * @param nickName
     * @return
     */
    public User getUserByNickName(String nickName);

    /**
     * 账号登录
     * @param loginType
     * @param loginUser
     * @return
     */
    public Map<String, Object> commitLogin(LoginType loginType, User user);

    /**
     * 游客登录信息
     * @param user
     */
    public User commitTouristLogin(User user);
    /**
     * 游客登录信息
     * @param user
     */
    public Map<String, Object> touristLogin();

    /**
     * 修改用户基本信息
     * @param user
     * @param nameDoubleCheck 是否检查昵称重复
     */
    public void updateUser(User user, boolean nameDoubleCheck);

    /**
     * 根据用户id获取用户信息
     * @param uid
     * @return
     */
    public User findById(String uid);

    /**
     * 根据email获取用户信息
     * @param email
     * @return
     */
    public User findByEmail(String email);

    /**
     * 检查是否存在同名用户
     * @param uid
     * @param nickName
     * @return
     */
    public boolean haveNickName(String uid, String nickName);

    /**
     * 第三方鉴权
     * @param oid
     * @param accessToken
     * @param refreshToken
     * @param type
     * @return
     */
    public boolean auThrid(String oid, String accessToken, LoginType type, String sysType, String packagename, String appId, String openName, String openIcon);

    /**
     * 保存第三方账号绑定信息
     * @param binding
     */
    public void saveBinding(UserOauth binding);

    /**
     * 修改第三方账号绑定信息
     * @param binding
     */
//    public void updateBinding(UserOauth binding);

    /**
     * 获取第三方账号绑定信息
     * @param oid
     * @param type
     */
    public UserOauth findBindingByOid(String oid, int type);

    /**
     * 查找用户token
     * @param uid
     * @return
     */
    public String findUserToken(String uid);

    /**
     * 根据用户ID，来源生成token
     * @param uid
     * @param origin
     * @return
     */
//    public String createUserToken(String uid);
}
