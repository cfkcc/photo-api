package com.photo.api.model.user;

import java.io.Serializable;
import java.util.Date;

public class UserOauth implements Serializable {
    private static final long serialVersionUID = -7155247680281656965L;

    private String oauthId;
    private String oid;
    private String userId;
    private String accessToken;
    private Integer type;
    private Integer status;
    private Date createTime;
    private String openIcon;
    private String openName;
	public UserOauth() {
		super();
	}
	
	public UserOauth(String oid, String userId, String accessToken, Integer type, String openIcon, String openName) {
		super();
		this.oid = oid;
		this.userId = userId;
		this.accessToken = accessToken;
		this.type = type;
		this.openIcon = openIcon;
		this.openName = openName;
	}



	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	@Override
	public String toString() {
		return "UserOauth [oauthId=" + oauthId + ", oid=" + oid + ", userId=" + userId + ", accessToken=" + accessToken
				+ ", type=" + type + ", status=" + status + ", createTime="
				+ createTime + ", openIcon=" + openIcon + ", openName=" + openName + "]";
	}

}
