package com.photo.api.service.account;

import com.photo.api.model.user.UserEmailCode;

public interface UserEmailCodeService {
	
	public void addUserEmailCode(UserEmailCode uec);
	
	public void updatUserEmailCode(UserEmailCode uec);
	
	public void delete(String[] userId);
	
	public UserEmailCode findByParam(String userId, String email);
	
	public UserEmailCode findByCode(String code);
	
	public void commitEmailCode(String email, int status, String code);

}
