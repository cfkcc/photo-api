package com.photo.api.service.photo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.photo.api.common.util.Page;
import com.photo.api.dao.photo.PhotoBuyRecordDao;
import com.photo.api.model.photo.PhotoBuyRecord;
import com.photo.api.service.photo.PhotoBuyRecordService;

@Service("photoBuyRecordService")
public class PhotoBuyRecordServiceImpl implements PhotoBuyRecordService {
	
	@Resource(name="photoBuyRecordDao")
	private PhotoBuyRecordDao photoBuyRecordDao;

	@Override
	public Boolean isBuy(String userId, String photoId) {
		Boolean isBuy = Boolean.FALSE;
		PhotoBuyRecord pbr = this.findByUserIdAndPhotoId(userId, photoId);
		if (pbr != null) {
			isBuy = pbr.getStatus()==PhotoBuyRecord.Status.Yes.getStatus()?Boolean.TRUE:Boolean.FALSE;
		}
		return isBuy;
	}

	@Override
	public Page findByPage(Page page) {
		return photoBuyRecordDao.findByPage(page);
	}
	
	private void addRecord(PhotoBuyRecord record){
		photoBuyRecordDao.add(record);
	}
	
	private void updateRecord(PhotoBuyRecord record){
		photoBuyRecordDao.update(record);
	}
	private void addBatchRecord(List<PhotoBuyRecord> recordList){
		photoBuyRecordDao.addBatch(recordList);
	}
	
	private void updateBatchRecord(List<PhotoBuyRecord> recordList){
		photoBuyRecordDao.updateBatch(recordList);
	}
	
	private PhotoBuyRecord findByUserIdAndPhotoId(String userId, String photoId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("photoId", photoId);
		return photoBuyRecordDao.findOne("findByUserIdAndPhotoId", param);
	}

	@Override
	public void saveOrUpdateRecord(String userId, String photoId, Boolean isSingle) {
		PhotoBuyRecord pbr = this.findByUserIdAndPhotoId(userId, photoId);
		Integer status = this.isBuy(userId, photoId)?PhotoBuyRecord.Status.Yes.getStatus():PhotoBuyRecord.Status.No.getStatus();
		if (pbr != null) {
			pbr.setStatus(status);
			this.updateRecord(pbr);
		}else{
			Integer choice = isSingle?PhotoBuyRecord.Choice.Single.getChoices():PhotoBuyRecord.Choice.Group.getChoices();
			pbr = new PhotoBuyRecord();
			pbr.setStatus(status);
			pbr.setPhotoId(photoId);
			pbr.setUserId(userId);
			pbr.setCreateTime(new Date());
			pbr.setChoice(choice);
			this.addRecord(pbr);
		}
		
	}

	@Override
	public void saveOrUpdateRecord(String userId, String[] photoIds, Boolean isSingle) {
		int length = photoIds.length;
		if (length==1) {
			saveOrUpdateRecord(userId, photoIds[0], isSingle);
		}else{
			List<PhotoBuyRecord> updateList = new ArrayList<PhotoBuyRecord>();
			List<PhotoBuyRecord> addList = new ArrayList<PhotoBuyRecord>();
			for (int i = 0; i < photoIds.length; i++) {
				String photoId  = photoIds[i];
				PhotoBuyRecord pbr = this.findByUserIdAndPhotoId(userId, photoId);
				Integer status = this.isBuy(userId, photoId)?PhotoBuyRecord.Status.Yes.getStatus():PhotoBuyRecord.Status.No.getStatus();
				if (pbr != null) {
					pbr.setStatus(status);
					updateList.add(pbr);
				}else{
					Integer choice = isSingle?PhotoBuyRecord.Choice.Single.getChoices():PhotoBuyRecord.Choice.Group.getChoices();
					pbr = new PhotoBuyRecord();
					pbr.setPhotoId(photoId);
					pbr.setStatus(status);
					pbr.setUserId(userId);
					pbr.setCreateTime(new Date());
					pbr.setChoice(choice);
					addList.add(pbr);
				}
			}
			if (!addList.isEmpty()) {
				this.addBatchRecord(addList);
			}
			if (!updateList.isEmpty()) {
				this.updateBatchRecord(updateList);
			}
		}
	}

}
