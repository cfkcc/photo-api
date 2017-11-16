package com.photo.api.common.enums;

/**
 * 登录类型
 */
public enum LoginTypeEnum {
    QQ(1), 				//QQ登录
    WECHAT(2), 			//微信登录
	FACEBOOK(3), 		//FACEBOOK登录
	GOOGLE(4); 			//GOOGLE登录

	private int loginType;
    private LoginTypeEnum(int loginType){
        this.loginType = loginType;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
