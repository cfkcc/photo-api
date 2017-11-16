package com.photo.api.model.user;

import java.io.Serializable;

public class ThirdPartyUser implements Serializable {
    private static final long serialVersionUID = 2650468991134914501L;

    private String openId;

    private String nickname;

    private String headImg;

    private String accessToken;

    private String pf;

    private Integer clientType;

    private String userIp;

    private String thirdAppId;

    private String thirdAppKey;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getThirdAppId() {
        return thirdAppId;
    }

    public void setThirdAppId(String thirdAppId) {
        this.thirdAppId = thirdAppId;
    }

    public String getThirdAppKey() {
        return thirdAppKey;
    }

    public void setThirdAppKey(String thirdAppKey) {
        this.thirdAppKey = thirdAppKey;
    }
}
