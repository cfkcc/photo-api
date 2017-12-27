package com.photo.api.common.enums;

public enum SettingSwitch {
    OPEN(1), // 开启
    CLOSE(2);// 关闭
    private int type;

    SettingSwitch(int type) {
        this.type = type;
    }

    public int getSwitch() {
        return type;
    }

    public static SettingSwitch getSwitch(int operate) {
        SettingSwitch[] types = SettingSwitch.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getSwitch() == operate) {
                return types[i];
            }
        }
        return SettingSwitch.OPEN;
    }
    
}
