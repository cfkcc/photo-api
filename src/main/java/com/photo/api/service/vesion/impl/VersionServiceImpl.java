package com.photo.api.service.vesion.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.dao.version.VersionDao;
import com.photo.api.model.version.Version;
import com.photo.api.service.vesion.VersionService;

@Service("versionService")
public class VersionServiceImpl implements VersionService{
	
	@Resource(name="versionDao")
	private VersionDao versionDao;

	@Override
	public Version findNewVersion(String appType, String systemType, String channel) {
		return versionDao.findNewVersion(appType, systemType, channel);
	}

}
