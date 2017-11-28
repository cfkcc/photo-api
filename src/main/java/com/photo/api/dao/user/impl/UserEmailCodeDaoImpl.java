package com.photo.api.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserEmailCodeDao;
import com.photo.api.model.user.UserEmailCode;

@Repository("userEmailCodeDao")
public class UserEmailCodeDaoImpl  extends PageDaoAbstract<UserEmailCode> implements UserEmailCodeDao{

	
	public UserEmailCode findByParam(String userId, String email) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userId)) {
			param.put("userId", userId);
		}
		if (StringUtils.isNotBlank(email)) {
			param.put("email", email);
		}
		return findOne("findByParam", param);
	}

	
	public UserEmailCode findByCode(String code) {
		return findOne("findByCode", code);
	}
}
