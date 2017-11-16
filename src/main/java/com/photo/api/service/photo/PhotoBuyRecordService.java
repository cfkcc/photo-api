package com.photo.api.service.photo;

import com.photo.api.common.util.Page;

public interface PhotoBuyRecordService {
	
	/**
	 * 判断是否已购买，默认未购买
	 * @param userId
	 * @param photoId
	 * @return
	 */
	public Boolean isBuy(String userId, String photoId);
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
	 * 分页查询购买记录
	 */
	public Page findByPage(Page page);
}
