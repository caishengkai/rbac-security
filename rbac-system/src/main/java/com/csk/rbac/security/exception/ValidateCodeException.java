package com.csk.rbac.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description:验证码验证异常类
 * @author: caishengkai
 * @time: 2019/12/30 19:58
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
