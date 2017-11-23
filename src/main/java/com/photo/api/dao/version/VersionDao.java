package com.photo.api.dao.version;

import com.photo.api.dao.PageDao;
import com.photo.api.model.version.Version;

public interface VersionDao extends PageDao<Version>{

	Version findNewVersion(String appType, String systemType, String channel);
}
