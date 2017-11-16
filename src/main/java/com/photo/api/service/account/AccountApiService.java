package com.photo.api.service.account;

import com.photo.api.common.exception.ServiceException;
import com.photo.api.model.user.ThirdPartyUser;
import com.photo.api.model.user.User;

public interface AccountApiService {
	
    public boolean checkThirdToken(ThirdPartyUser thirdPartyUser, boolean isOpen) throws ServiceException;
    
    public User addThirdUserLogin(ThirdPartyUser thirdPartyUser);

}
