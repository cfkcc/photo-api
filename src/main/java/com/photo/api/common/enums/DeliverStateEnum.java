package com.photo.api.common.enums;

public enum DeliverStateEnum {

    DELIVER_HOLD(0, "待发货"), DELIVER_IN(1, "发货中"), DELIVER_SUCCESS(2, "发货成功"), DELIVER_FAILED(-1, "发货失败");

    private int stateCode;

    private String stateMsg;

    private DeliverStateEnum(int stateCode, String stateMsg) {
        this.stateCode = stateCode;
        this.stateMsg = stateMsg;
    }

    public static String getStateMsg(int stateCode) {
        for (DeliverStateEnum deliverStateEnum : DeliverStateEnum.values()) {
            if (deliverStateEnum.getStateCode() == stateCode) {
                return deliverStateEnum.getStateMsg();
            }
        }
        return null;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }
    
}
