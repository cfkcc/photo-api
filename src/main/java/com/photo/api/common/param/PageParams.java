package com.photo.api.common.param;

public class PageParams extends BaseParams {
	private static final long serialVersionUID = 1L;
	private Long pageIndex;
    private Long pageSize;

    public Long getPageIndex() {
        if(pageIndex==null){
            return 1L;
        }
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        if(pageSize==null){
            return 10L;
        }
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
