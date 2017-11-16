package com.photo.api.service.photo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.dao.photo.PhotoGroupDao;
import com.photo.api.model.photo.PhotoGroup;
import com.photo.api.service.photo.PhotoGroupService;

@Service("photoGroupService")
public class PhotoGroupServiceImpl implements PhotoGroupService {
	
	@Resource(name="photoGroupDao")
	private PhotoGroupDao photoGroupDao;

	@Override
	public PhotoGroup findById(String groupId) {
		return photoGroupDao.findById(groupId);
	}

	@Override
	public Page findByPage(Page page) {
		return photoGroupDao.findByPage(page);
	}

}
