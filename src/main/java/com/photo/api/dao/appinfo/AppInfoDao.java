package com.photo.api.dao.appinfo;

import com.photo.api.dao.PageDao;
import com.photo.api.model.appinfo.AppInfo;

public interface AppInfoDao extends PageDao<AppInfo> {
	
	public AppInfo findById(String appId);

}
