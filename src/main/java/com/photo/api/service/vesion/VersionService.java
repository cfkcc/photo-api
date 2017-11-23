package com.photo.api.service.vesion;

import com.photo.api.model.version.Version;

public interface VersionService {
	
	public Version findNewVersion(String appType, String systemType, String channel);

}
