package com.photo.api.model.pay;

import java.io.Serializable;
import java.util.Date;

public class PayCurrency implements Serializable{

	private static final long serialVersionUID = 1L;
	private String currencyId;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private String createUser;
	private String updateUser;
	private String name;
	private Float exchangeRatio;
	private String remark;
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getExchangeRatio() {
		return exchangeRatio;
	}
	public void setExchangeRatio(Float exchangeRatio) {
		this.exchangeRatio = exchangeRatio;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
