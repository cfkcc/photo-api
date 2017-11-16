package com.photo.api.dao.photo;

import com.photo.api.dao.PageDao;
import com.photo.api.model.photo.PhotoGroup;

public interface PhotoGroupDao extends PageDao<PhotoGroup> {
	
	public PhotoGroup findById(String groupId);
	
}
