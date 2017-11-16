package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserOauth implements Serializable {
    private static final long serialVersionUID = -7155247680281656965L;

    private String bingId;

    private String openId;

    private String userId;

    private String accessToken;

    private Integer clientType;

    private Date createTime;
    public String getBingId() {
        return bingId;
    }

    public void setBingId(String bingId) {
        this.bingId = bingId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserOauth [bingId=" + bingId + ", openId=" + openId + ", userId=" + userId + ", accessToken="
				+ accessToken + ", clientType=" + clientType + "]";
	}

}
