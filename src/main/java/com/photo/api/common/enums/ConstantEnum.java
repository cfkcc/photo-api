package com.photo.api.common.enums;

public final class ConstantEnum {

    public static enum Flag{
        DEL((byte)-1,"已删除"),VALID((byte) 1,"有效");
        private byte flag;
        private String name;
        private Flag(byte flag,String name){
            this.flag = flag;
            this.name = name;
        }

        public byte getFlag() {
            return flag;
        }

        public String getName() {
            return name;
        }
    }


    public static enum Status{
        DISABLE((byte)0,"禁用"),ENABLE((byte) 1,"启用");
        private byte status;
        private String name;
        private Status(byte status,String name){
            this.status = status;
            this.name = name;
        }

        public byte getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }
    }
}
