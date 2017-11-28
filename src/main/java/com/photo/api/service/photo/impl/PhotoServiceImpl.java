package com.photo.api.service.photo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.dao.photo.PhotoDao;
import com.photo.api.model.photo.Photo;
import com.photo.api.model.photo.PhotoBuyRecord;
import com.photo.api.service.photo.PhotoService;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	
	@Resource(name="photoDao")
	private PhotoDao photoDao;

	
	public Photo findById(String photoId) {
		return photoDao.findById(photoId);
	}

	
	public Page findByPage(Page page) {
		return photoDao.findByPage(page);
	}

	
	public Integer findCoinsByPhotoIds(String[] photoId) {
		return photoDao.findCoins(photoId, PhotoBuyRecord.Choice.Single.getChoices());
	}

	
	public Integer findCoinsByGroupId(String groupId) {
		return photoDao.findCoins(new String[]{groupId}, PhotoBuyRecord.Choice.Group.getChoices());
	}

	
	public List<String> findPhotoIdsByGroupId(String photoGroupId) {
		return photoDao.findPhotoIdsByGroupId(photoGroupId);
	}

	
	public List<Photo> findPhotoByGroupId(String photoGroupId) {
		return photoDao.findPhotosByGroupId(photoGroupId);
	}

}
