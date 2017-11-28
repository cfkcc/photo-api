package com.photo.api.dao;

import java.util.Collection;

import com.photo.api.common.constant.CommonConsts;
import com.photo.api.common.util.Page;

public abstract class PageDaoAbstract<T> extends BaseDaoAbstract<T> implements PageDao<T> {

	
	public Page findByPage(Page page) {
		if (!page.isFindCountOnly()) {
			page.getParams().put("offset", page.getStartPage());
			page.getParams().put("limit", page.getPageSize());
			page.getParams().put("ordering", page.getOrderBy());
			Collection<?> records = findList(CommonConsts.FINDBYPAGE, page.getParams());
			long rowCount = ((Integer)find(CommonConsts.FINDCOUNTBYPAGE, page.getParams())).longValue();
			page.setQueryResult(rowCount, records);
			return page;
		}
		long rowCount = ((Integer)find(CommonConsts.FINDCOUNTBYPAGE, page.getParams())).longValue();
		page.setRowCount(rowCount);
		return page;
	}
	
}
