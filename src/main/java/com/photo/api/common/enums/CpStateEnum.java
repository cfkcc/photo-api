package com.photo.api.common.enums;

public enum CpStateEnum {

    CP_SUCCESS("2000", "成功"), CP_PARAM_ERROR("2001", "code参数错误"), CP_INFO_ERROR("2002", "cpInfo参数错误"), CP_SERVERID_ERROR("2003",
            "服务器id错误"), CP_ORDERNO_EXISTS("2004", "订单号已存在"), CP_ROLE_NOTEXISTS("2005", "角色不存在"), CP_PRODUCT_ERROR("2006", "商品错误");

    private String code;

    private String msg;

    private CpStateEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getCpMsg(String code) {
        for (CpStateEnum cpStateEnum : CpStateEnum.values()) {
            if (cpStateEnum.getCode().equals(code)) {
                return cpStateEnum.getMsg();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
