package com.photo.api.service.appinfo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.appinfo.AppInfoDao;
import com.photo.api.model.appinfo.AppInfo;
import com.photo.api.service.appinfo.AppInfoService;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
	
	@Resource(name="appInfoDao")
	private AppInfoDao appInfoDao;

	@Override
	public AppInfo findById(String appId) {
		return appInfoDao.findById(appId);
	}

	@Override
	public void addAppInfo(AppInfo appInfo) {
		appInfoDao.add(appInfo);		
	}

	@Override
	public void updateAppInfo(AppInfo appInfo) {
		appInfoDao.update(appInfo);		
	}

}
