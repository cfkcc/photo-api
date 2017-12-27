package com.photo.api.common.param;

public class PageParam extends BaseParam{
	
	private static final long serialVersionUID = 1L;
	private Integer pageIndex = 1;
	private Integer pageSize = 10;
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
