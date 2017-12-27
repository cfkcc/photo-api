package com.photo.api.common.enums;

/**
 * blue pay 的回调状态码
 */
public enum BluePayStatusCodeEnum {
    /**
     * 成功返回码，均为以2开头的三位数字
     */
    CODE_200(200,"支付成功"),
    CODE_201(201,"正在支付中"),
    CODE_202(202,"支付失败"),
    CODE_203(203,"支付取消"),

    /**
     * 请求参数错误，均为以4开头的三位数字
     */
    CODE_400(400,"请求参数错误"),
    CODE_401(401,"未授权"),
    CODE_402(402,"双卡错误"),
    CODE_403(403,"SIM卡异常"),
    CODE_404(404,"请求数据不存在或请求异常"),
    CODE_405(405,"SDK使用错误"),
    CODE_406(406,"Cash card调用失败"),
    CODE_407(407,"短信发送错误"),
    CODE_408(408,"游戏厂商未储值或钱袋余额不足"),
    CODE_409(409,"产品状态受限"),

    /**
     * BLUEPAY服务错误，均以5开头的三位数字
     */
    CODE_500(500,"BLUEPAY内部错误"),
    CODE_501(501,"SDK与BLUEPAY服务通信异常"),
    CODE_502(502,"BLUEPAY在运营商侧服务异常"),
    CODE_503(503,"网络限制"),
    CODE_504(504,"用户当日累计消费超过日限制"),
    CODE_505(505,"用户当月累计消费超过月限制"),
    CODE_506(506,"用户黑名单"),
    CODE_507(507,"用户消费间隔时间少于60s"),
    CODE_508(508,"待确认密码已经被使用"),
    CODE_509(509,"待确认密码不存在或非法"),
    CODE_510(510,"待确认密码已经过期"),
    CODE_511(511,"当前用户频繁试错，请稍候再试"),
    CODE_512(512,"银行卡账号已经绑定"),
    CODE_513(513,"钱袋状态不正确"),
    CODE_514(514,"钱袋为后付费钱袋，不需要储值"),
    CODE_532(532,"该用户没有绑定银行卡"),
    CODE_533(533,"该用户没找到银行卡的交易记录"),
    CODE_534(534,"通过imsi获取用户手机号码失败"),
    CODE_540(540,"银行卡号错误"),

    /**
     * 运营商服务错误，均以6开头的三位数字
     */
    CODE_600(600,"运营商服务错误"),
    CODE_601(601,"用户余额不足"),
    CODE_602(602,"用户状态异常"),
    CODE_603(603,"用户取消操作"),
    CODE_605(605,"用户重复使用相同transaction id付费"),
    CODE_606(606,"用户重复点击广告"),
    CODE_607(607,"用户接口超时"),
    CODE_608(608,"超出广告主限制"),
    CODE_609(609,"用户关联的银行卡付款失败"),
    CODE_611(611,"用户消费金额小于系统要求金额"),
    CODE_621(621,"被充值用户号码异常"),
    CODE_623(623,"充值卡已过期"),
    CODE_624(624,"充值失败"),
    CODE_631(631,"账号信息或者动态码填写错误"),
    CODE_634(634,"单笔额度不能超过XXX"),

    /**
     * 无返回码
     */
    CODE_999(999,"未知错误");

    private int code;
    private String msg;
    private BluePayStatusCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code){
        for(BluePayStatusCodeEnum bluePayStatusCode : BluePayStatusCodeEnum.values()){
            if(bluePayStatusCode.getCode() == code){
                return bluePayStatusCode.getMsg();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
