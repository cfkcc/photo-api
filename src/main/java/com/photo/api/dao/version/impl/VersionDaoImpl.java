package com.photo.api.dao.version.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.version.VersionDao;
import com.photo.api.model.version.Version;

@Repository("versionDao")
public class VersionDaoImpl extends PageDaoAbstract<Version> implements VersionDao{

	@Override
	public Version findNewVersion(String appType, String systemType, String channel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appType", appType);
		param.put("systemType", systemType);
		param.put("channel", channel);
		return findOne("findNewVersion",param);
	}

}
