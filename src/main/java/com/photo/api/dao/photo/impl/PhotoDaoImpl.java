package com.photo.api.dao.photo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.photo.PhotoDao;
import com.photo.api.model.photo.Photo;
import com.photo.api.model.photo.PhotoBuyRecord;

@Repository("photoDao")
public class PhotoDaoImpl extends PageDaoAbstract<Photo> implements PhotoDao {

	@Override
	public Photo findById(String photoId) {
		return findOne("findById", photoId);
	}

	@Override
	public List<Photo> findPhotosByGroupId(String groupId) {
		return (List<Photo>) findList("findPhotosByGroupId", groupId);
	}
	
	@Override
	public List<String> findPhotoIdsByGroupId(String groupId) {
		List<Photo> objectList = this.findPhotosByGroupId(groupId);
		List<String> result = new ArrayList<String>();
		for (Photo photo : objectList) {
			result.add(photo.getPhotoId());
		}
		return result;
	}

	@Override
	public Integer findCoins(String[] ids, Integer choice) {
		Integer coins = 0;
		if (ArrayUtils.isNotEmpty(ids)) {
			if (choice!=null && choice==PhotoBuyRecord.Choice.Group.getChoices()) {
				String groupId = ids[0];
				coins = (Integer)find("findCoinsByGroupId", groupId);
			}else{
				String [] photoIds = Arrays.copyOfRange(ids, 0, ids.length);
				coins = (Integer)find("findCoinsByPhotoIds", photoIds);
			}
		}
		return coins;
	}

}
