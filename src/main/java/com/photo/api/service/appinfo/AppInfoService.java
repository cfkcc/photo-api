package com.photo.api.service.appinfo;

import com.photo.api.model.appinfo.AppInfo;

public interface AppInfoService {
	/**
	 * 根据app唯一标识码获取app信息
	 * @param appId
	 * @return
	 */
	public AppInfo findById(String appId);
	/**
	 * 添加app信息
	 * @param appInfo
	 */
	public void addAppInfo(AppInfo appInfo);
	/**
	 * 修改app信息
	 * @param appInfo
	 */
	public void updateAppInfo(AppInfo appInfo);

}
