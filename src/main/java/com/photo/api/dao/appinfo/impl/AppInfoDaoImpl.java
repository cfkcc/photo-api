package com.photo.api.dao.appinfo.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.appinfo.AppInfoDao;
import com.photo.api.model.appinfo.AppInfo;

@Repository("appInfoDao")
public class AppInfoDaoImpl extends PageDaoAbstract<AppInfo> implements AppInfoDao {

	@Override
	public AppInfo findById(String appId) {
		return findOne("findById", appId);
	}

}
