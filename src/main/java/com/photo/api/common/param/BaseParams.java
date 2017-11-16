package com.photo.api.common.param;/**
 * Created by Dell on 2017/10/16.
 */

import java.io.Serializable;

/**
 * @author
 * @create 2017-10-16 15:35
 **/
public class BaseParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String channel;//渠道
    private String sysType;//系统类型,i表示IOS，a表示android
    private String sysVersion;// 系统版本
    private String appVersion;// 应用版本
    private String sysLanguage;// 系统语言
    private String clientAreaCode;
    private String method;// 执行方法
    private Integer origin;//来源
    private String clientId;//手机设备唯一标识
    private String packageName;	//应用app包名
    private String tt;// 请求的时间戳
    private String clientIp;
    private String token;
    private String userId;

    @Override 
    public String toString() {
        return "BaseParams{" + "channel='" + channel + '\'' + ", sysType='" + sysType + '\'' + ", sysVersion='" + sysVersion
                + '\'' + ", appVersion='" + appVersion + '\'' + ", sysLanguage='" + sysLanguage + '\'' + ", clientAreaCode='"
                + clientAreaCode + '\'' + ", method='" + method + '\'' + ", origin=" + origin + ", clientId='" + clientId + '\''
                + ", packageName='" + packageName + '\'' + ", tt='" + tt + '\''
                + ", clientIp='" + clientIp + '\'' + ", token='" + token + '\'' + ", userId='" + userId + '\'' + '}';
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSysLanguage() {
        return sysLanguage;
    }

    public void setSysLanguage(String sysLanguage) {
        this.sysLanguage = sysLanguage;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClientAreaCode() {
		return clientAreaCode;
	}

	public void setClientAreaCode(String clientAreaCode) {
		this.clientAreaCode = clientAreaCode;
	}

	public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
