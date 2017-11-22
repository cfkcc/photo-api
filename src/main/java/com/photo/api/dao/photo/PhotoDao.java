package com.photo.api.dao.photo;

import java.util.List;

import com.photo.api.dao.PageDao;
import com.photo.api.model.photo.Photo;

public interface PhotoDao extends PageDao<Photo> {
	
	public Photo findById(String photoId);
	
	public List<Photo> findPhotosByGroupId(String groupId);
	public List<String> findPhotoIdsByGroupId(String groupId);
	/**
	 * 根据购买类型获取相应的金币值
	 * @param ids
	 * @param choice 0：购买图片 1：购买套图
	 * @return
	 */
	public Integer findCoins(String [] ids, Integer choice);

}
