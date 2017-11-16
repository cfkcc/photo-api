package com.photo.api.service.photo;

import com.photo.api.common.util.Page;
import com.photo.api.model.photo.Photo;

public interface PhotoService {
	/**
	 * 根据图片的ID获取图片信息
	 * @param photoId
	 * @return
	 */
	public Photo findById(String photoId);
	/**
	 * 分页查询图片信息
	 * @param page
	 * @return
	 */
	public Page findByPage(Page page);
	/**
	 * 根据图片ID获取购买图片的金币数量
	 * @param photoIds
	 * @return
	 */
	public Integer findCoinsByPhotoIds(String[] photoId);
	/**
	 * 根据套图ID获取购买套图的金币数量
	 * @param groupId
	 * @return
	 */
	public Integer findCoinsByGroupId(String groupId);

}
