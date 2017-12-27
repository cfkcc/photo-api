package com.photo.api.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
	public static final String DEFAULT_ICON = "http://image.game.uc.cn/2015/3/25/10351407.jpg";
    private static final long serialVersionUID = -7080249877024144230L;
    private String userId;
    private String nickName;
    private String headImg;
    private Integer userType;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private BigDecimal coins;
    private Date bir;
    private String sign;
    private Integer sex;
    private String email;
    private String updateUser;
    private String password;
    transient private UserOauth userOauth;
    transient private UserLoginRecord userLoginRecord;
    private String token;
    private String appId;
    private String packageName;
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBir() {
		return bir;
	}

	public void setBir(Date bir) {
		this.bir = bir;
	}
	public UserOauth getUserOauth() {
		return userOauth;
	}

	public void setUserOauth(UserOauth userOauth) {
		this.userOauth = userOauth;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserLoginRecord getUserLoginRecord() {
		return userLoginRecord;
	}

	public void setUserLoginRecord(UserLoginRecord userLoginRecord) {
		this.userLoginRecord = userLoginRecord;
	}
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", headImg=" + headImg + ", userType=" + userType
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", coins="
				+ coins + ", bir=" + bir + ", sign=" + sign + ", sex=" + sex + ", email=" + email + ", updateUser="
				+ updateUser + ", password=" + password + "]";
	}
	
}
