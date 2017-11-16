package com.photo.api.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.user.UserAuditDao;
import com.photo.api.model.user.UserAudit;

@Repository("userAuditDao")
public class UserAuditDaoImpl extends PageDaoAbstract<UserAudit> implements UserAuditDao {

}
