package com.photo.api.service.vesion;

import java.util.Map;

import com.photo.api.model.version.Version;

public interface VersionApiService {
	
	public Map<String, Object> findNewVersion(String appType, String systemType, String channel);

}
