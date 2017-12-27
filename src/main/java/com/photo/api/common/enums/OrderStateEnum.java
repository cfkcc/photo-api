package com.photo.api.common.enums;

public enum OrderStateEnum {

    WAIT_PAY(0,"待支付"),
    PAYMENT_IN(1,"支付中"),
    PAY_SUCCESS(2,"支付成功"),
    PAY_FAILED(3,"支付失败"),
    PAY_CANCEL(4,"支付取消");

    int stateCode;
    String stateMsg;

    private OrderStateEnum(int stateCode,String stateMsg){
        this.stateCode = stateCode;
        this.stateMsg = stateMsg;
    }

    public static String getStateMsg(int stateCode){
        for(OrderStateEnum orderStateEnum :OrderStateEnum.values()){
            if(orderStateEnum.getStateCode() == stateCode){
                return orderStateEnum.getStateMsg();
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
