package com.photo.api.service.account.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.dao.user.UserFansDao;
import com.photo.api.model.user.UserFans;
import com.photo.api.service.account.UserFansService;

@Service("userFansService")
public class UserFansServiceImpl implements UserFansService {
	
	@Resource(name="userFansDao")
	private UserFansDao userFansDao;

	@Override
	public Boolean isFans(String userId, String fansId) {
		Boolean isFans = Boolean.FALSE;
		UserFans uf = this.findByUserIdAndFansId(userId, fansId);
		if (uf != null) {
			isFans = uf.getStatus()==UserFans.Status.Yes.getStatus()?Boolean.TRUE:Boolean.FALSE;
		}
		return isFans;
	}

	@Override
	public void saveOrUpdateUserFans(String userId, String fansId, Boolean isFans) {
		UserFans uf = this.findByUserIdAndFansId(userId, fansId);
		Integer status = isFans?UserFans.Status.Yes.getStatus():UserFans.Status.No.getStatus();
		if (uf != null) {
			uf.setStatus(status);
			uf.setUpdateTime(new Date());
			this.updateFans(uf);
		}else{
			uf = new UserFans();
			uf.setCreateTime(new Date());
			uf.setUpdateTime(new Date());
			uf.setFansId(fansId);
			uf.setStatus(status);
			uf.setUserId(userId);
			this.addFans(uf);
		}
	}

	@Override
	public long findCountByUserId(String fansId) {
		return userFansDao.findCountByUserId(fansId);
	}

	@Override
	public long findFansCountByFansId(String userId) {
		return userFansDao.findFansCountByFansId(userId);
	}

	@Override
	public Page findFansByPage(String userId, Integer pageIndex, Integer pageSize, String sorting) {
		Page page = new Page(pageIndex);
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		if (StringUtils.isNotBlank(sorting)) {
			page.setOrderBy(sorting);
		}
		page.getParams().put("userId", userId);
		return userFansDao.findByPage(page);
	}

	@Override
	public Page findUsersByPage(String fansId, Integer pageIndex, Integer pageSize, String sorting) {
		Page page = new Page(pageIndex);
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		if (StringUtils.isNotBlank(sorting)) {
			page.setOrderBy(sorting);
		}
		page.getParams().put("fansId", fansId);
		return userFansDao.findByPage(page);
	}
	
	private UserFans findByUserIdAndFansId(String userId, String fansId){
		return userFansDao.findByUserIdAndFansId(userId, fansId);
	}
	
	private void addFans(UserFans uf){
		userFansDao.add(uf);
	}
	private void updateFans(UserFans uf){
		userFansDao.update(uf);
	}

}
