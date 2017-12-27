package com.photo.api.common.enums;

public enum UserType {
    SYSTEM_USER(1), SYSTEM_TOURIST(2);
    private int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
