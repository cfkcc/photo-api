package com.photo.api.dao.user;

import com.photo.api.dao.PageDao;
import com.photo.api.model.user.UserEmailCode;

public interface UserEmailCodeDao extends PageDao<UserEmailCode>{
	
	public UserEmailCode findByParam(String userId, String email);
	
	public UserEmailCode findByCode(String code);
	
}
