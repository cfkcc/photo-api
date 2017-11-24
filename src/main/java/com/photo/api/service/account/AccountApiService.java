package com.photo.api.service.account;

import java.util.Map;

import com.photo.api.common.exception.ServiceException;
import com.photo.api.common.util.Page;
import com.photo.api.model.user.ThirdPartyUser;
import com.photo.api.model.user.User;

public interface AccountApiService {
	
    public boolean checkThirdToken(ThirdPartyUser thirdPartyUser, boolean isOpen) throws ServiceException;
    
    public User addThirdUserLogin(ThirdPartyUser thirdPartyUser);
    
    public Map<String, Object> getBalance(String userId);
    
    public Map<String, Object> findFans(Page page);
    
    public Map<String, Object> findFowllowed(Page page);
    
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
}
