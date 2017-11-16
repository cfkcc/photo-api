package com.photo.api.common.token;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class TokenID implements Serializable{

    private static final long serialVersionUID = -5600016525461507988L;

    @JSONField(ordinal = 0)
    private String uid;
    @JSONField(ordinal = 1)
    private String ttp;

    protected  TokenID(){}

    public TokenID(String uid){
        this.uid = uid;
        this.ttp = Long.toHexString(System.currentTimeMillis());
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTtp() {
        return ttp;
    }

    public void setTtp(String ttp) {
        this.ttp = ttp;
    }
}
