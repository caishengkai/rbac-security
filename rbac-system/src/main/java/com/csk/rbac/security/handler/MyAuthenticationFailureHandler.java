package com.csk.rbac.security.handler;

import com.csk.rbac.common.RbacConstant;
import com.csk.rbac.common.ServerResponse;
import com.csk.rbac.security.exception.ValidateCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:自定义登录成功逻辑
 * @author: caishengkai
 * @time: 2019/12/30 11:19
 **/
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message;
        if (exception instanceof UsernameNotFoundException) {
            message = "用户不存在！";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误！";
        } else if (exception instanceof LockedException) {
            message = "用户已被锁定！";
        } else if (exception instanceof DisabledException) {
            message = "用户不可用！";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户已过期！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "用户密码已过期！";
        } else if (exception instanceof ValidateCodeException) {
            message = exception.getMessage();
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        response.setContentType(RbacConstant.JSON_UTF8);
        response.getWriter().write(mapper.writeValueAsString(ServerResponse.createByErrorMessage(message)));
    }
}
