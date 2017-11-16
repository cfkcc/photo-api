package com.photo.api.service.account.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.photo.api.service.account.UserAuditService;

@Service("userAuditService")
public class UserAuditServiceImpl implements UserAuditService {
	Logger log = LoggerFactory.getLogger(UserAuditServiceImpl.class);

}
