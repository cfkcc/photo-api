package com.photo.api.service.vesion.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.model.version.Version;
import com.photo.api.service.vesion.VersionApiService;
import com.photo.api.service.vesion.VersionService;

@Service("versionApiService")
public class VersionApiServiceImpl implements VersionApiService{
	
	@Resource(name="versionService")
	private VersionService versionService;

	@Override
	public Map<String, Object> findNewVersion(String appType, String systemType, String channel) {
		Version v = versionService.findNewVersion(appType, systemType, channel);
		Map<String, Object> result = new HashMap<String, Object>();
		if (v != null) {
			result.put("versionId", v.getVersionId());
			result.put("versionCode", v.getVersionCode());
			result.put("downloadUrl", v.getDownloadUrl());
			result.put("tips", v.getTips());
			result.put("isMust", v.getIsMust());
			result.put("md5", v.getMd5());
			result.put("packagename", v.getPackageName());
			result.put("systemType", v.getSystemType());
			result.put("channel", v.getChannel());
		}
		return result;
	}

}
