package com.photo.api.dao;

import com.photo.api.common.util.Page;

public interface PageDao<T> extends BaseDao<T> {

	public Page findByPage(Page page);
	
}
