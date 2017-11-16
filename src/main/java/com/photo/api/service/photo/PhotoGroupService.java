package com.photo.api.service.photo;

import com.photo.api.common.util.Page;
import com.photo.api.model.photo.PhotoGroup;

public interface PhotoGroupService {
	
	public PhotoGroup findById(String groupId);
	
	public Page findByPage(Page page);

}
