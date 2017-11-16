package com.photo.api.dao.photo.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.photo.PhotoGroupDao;
import com.photo.api.model.photo.PhotoGroup;

@Repository("photoGroupDao")
public class PhotoGroupDaoImpl extends PageDaoAbstract<PhotoGroup> implements PhotoGroupDao {

	@Override
	public PhotoGroup findById(String groupId) {
		return findOne("findById", groupId);
	}

}
