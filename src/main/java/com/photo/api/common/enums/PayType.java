package com.photo.api.common.enums;

public enum PayType {
    GOOGLE(1), WEIXIN(2), ZHIFUBAO(3), APPLE(4);

    private int type;

    PayType(int type) {
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
    public static PayType getPayType(int type) {
        PayType[] types = PayType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getType() == type) {
                return types[i];
            }
        }
        return PayType.GOOGLE;
    }
    
}
