package com.csk.rbac.security.code;

/**
 * 验证码生成器接口
 */
public interface ValidateCodeGenerator {

    ValidateCode createCode();
}
