package com.csk.rbac.common;

/**
 * @description:全局常量类
 * @author: caishengkai
 * @time: 2019/12/30 11:09
 **/
public class RbacConstant {

    public static final String STATUS_VALID = "1";

    public static final String STATUS_LOCK = "0";

    // 图形验证码 session key
    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    // 返回报文头 json格式，编码 utf-8
    public static final String JSON_UTF8 = "application/json;charset=utf-8";

    //登录方式
    public enum LoginType {
        NORMAL(1, "网页"),
        SMS(2, "短信"),
        WECHAT(1, "微信");

        private int code;
        private String value;
        LoginType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

}
