package com.photo.api.service.photo;

import java.util.Map;

import com.photo.api.common.util.Page;

public interface PhotosApiService {
	/**
	 * 保存购买记录状态 默认为单张购买
	 * @param userId 购买的用户ID
	 * @param photoId 被购买的图片的ID
	 * @param isBuy 是否已购买
	 */
	public void saveOrUpdateRecord(String userId, String photoId, Boolean isBuy);
	/**
	 * 保存购买记录状态
	 * @param userId 购买的用户ID
	 * @param photoId 被购买的图片的ID
	 * @param isBuy 是否已购买
	 * @param isSingle 是否单张购买(对于已购买无效)
	 */
	public void saveOrUpdateRecord(String userId, String photoId, Boolean isBuy, Boolean isSingle);
	/**
	 * 判断是否购买
	 * @param userId
	 * @param photoId
	 * @return
	 */
	public Boolean isBuy(String userId, String photoId);
	/**
	 * 根据用户的金币判断是否可以购买
	 * @param userId
	 * @return
	 */
	public Boolean getPurchasingPower(String userId, String[] photoIds);
	/**
	 * 根据用户的金币判断是否可以购买套图
	 * @param userId
	 * @return
	 */
	public Boolean getPurchasingPower(String userId, String photoGroupId);
	/**
	 * 分页查询图片信息
	 * @param page
	 * @return
	 */
	public Map<String, Object> findPhotosByPage(Page page);
	/**
	 * 分页查询套图信息
	 * @param page
	 * @return
	 */
	public Map<String, Object> findPhotoGroupsByPage(Page page);
	/**
	 * 分页查询图片购买记录
	 * @param page
	 * @return
	 */
	public Map<String, Object> findPhotoBuyRecordsByPage(Page page);

}
