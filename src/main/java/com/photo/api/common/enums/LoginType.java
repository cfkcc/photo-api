package com.photo.api.common.enums;

public enum LoginType {
    SELF(0), // 自有账户登陆
    QQ(1), WEIXIN(2), WEIBO(3), FACEBOOK(4), TWITTER(5), GOOGLE(6), LINE(7), INSTAGRAM(8), OTHER(-1);

    private int type;

    LoginType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    /**
     * 类型检查，返回枚举类
     * @param type
     * @return
     */
    public static LoginType getLoginType(int type) {
        LoginType[] types = LoginType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getType() == type) {
                return types[i];
            }
        }
        return LoginType.OTHER;
    }
    
}
