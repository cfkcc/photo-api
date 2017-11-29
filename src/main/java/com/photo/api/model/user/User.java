package com.photo.api.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = -7080249877024144230L;

    private String userId;

    private String nickname;

    private String headImg;

    private Integer userType;

    private Integer status;

    private Date createTime;
    private Date updateTime;
    
    private BigDecimal coins;

    private String sign;
    
    private String email;
    private String updateUser;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public BigDecimal getCoins() {
		return coins;
	}

	public void setCoins(BigDecimal coins) {
		this.coins = coins;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickname=" + nickname + ", headImg=" + headImg + ", userType=" + userType
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", coins="
				+ coins + ", sign=" + sign + ", email=" + email + ", updateUser=" + updateUser + "]";
	}

}
