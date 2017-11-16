package com.photo.api.dao.photo.impl;

import org.springframework.stereotype.Repository;

import com.photo.api.dao.PageDaoAbstract;
import com.photo.api.dao.photo.PhotoBuyRecordDao;
import com.photo.api.model.photo.PhotoBuyRecord;

@Repository("photoBuyRecordDao")
public class PhotoBuyRecordDaoImpl extends PageDaoAbstract<PhotoBuyRecord> implements PhotoBuyRecordDao {

}
