package com.photo.api.common.enums;

public enum DataBaseStatusType {
    ERROR(-2), // 异常/错误/失败
    DELETED(-1), // 已经删除
    NO_USING(0), // 未使用/非最新
    USING(1), // 使用中/最新
    HANDING(2), // 处理中
    HANDING_STOP(3), // 处理暂停
    HANDING_FORGO(4);// 处理放弃
    private int type;

    DataBaseStatusType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return type;
    }

    public static DataBaseStatusType getStatus(int operate) {
        DataBaseStatusType[] types = DataBaseStatusType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getStatus() == operate) {
                return types[i];
            }
        }
        return DataBaseStatusType.USING;
    }
    
}
