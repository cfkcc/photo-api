package com.photo.api.common.enums;

/**
 * 状态位枚举
 */
public enum EnumStatusType {

    STATUS_NOT(0,"禁用"),
    STATUS_OK(1,"启用"),
    STATUS_DEL(2,"删除");

    private int type;
    private String desc;

    EnumStatusType(int type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
