package com.photo.api.common.enums;

import org.apache.commons.lang.StringUtils;

public enum SysType {
    ANDROID("a"), // 安卓
    IOS("i")// 苹果
    ;

    private String type;

    SysType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SysType getSysType(String type) {
        if (StringUtils.isNotBlank(type) && type.toLowerCase().startsWith("iphone")) {
            return SysType.IOS;
        }
        SysType[] types = SysType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getType().equals(type)) {
                return types[i];
            }
        }
        return SysType.ANDROID;
    }

}
