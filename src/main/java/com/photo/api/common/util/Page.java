package com.photo.api.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Page {
	/**
	 * 每页显示记录数，默认为10条数据
	 */
	private int pageSize = 10;
	/**
	 * 当前页 默认为第一页
	 */
	private int pageNo = 1;
	/**
	 * 排序规则
	 */
	private String orderBy;
	/**
	 * 总页数
	 */
	private long pageCount;
	/**
	 * 分页数据
	 */
	private Collection<?> records;
	/**
	 * 总记录数
	 */
	private long rowCount;
	/**
	 * 从第几条记录开始
	 */
	private int startPage;
	/**
	 * 查询参数
	 */
	private Map<String, Object> params=new HashMap<String, Object>();
	/**
	 * 是否只查询总记录数
	 */
	private boolean isFindCountOnly=Boolean.FALSE;
	
	public Page() {
		setStartPage((this.pageNo-1)*this.pageSize);
	}
	
	public Page(int pageNo) {
		this.pageNo = pageNo;
		setStartPage((this.pageNo-1)*this.pageSize);
	}
	public void setQueryResult(long rowCount, Collection<?> records){
		setRowCount(rowCount);
		setRecords(records);
	}
	public int getOff(){
		return (this.pageNo-1)*this.pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public long getPageCount() {
		return this.pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public Collection<?> getRecords() {
		return records;
	}
	public void setRecords(Collection<?> records) {
		this.records = records;
	}
	public long getRowCount() {
		return rowCount;
	}
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		setPageCount(this.rowCount%this.pageSize==0?(this.rowCount/this.pageSize):(this.rowCount/this.pageSize+1));
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public boolean isFindCountOnly() {
		return isFindCountOnly;
	}
	public void setFindCountOnly(boolean isFindCountOnly) {
		this.isFindCountOnly = isFindCountOnly;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
