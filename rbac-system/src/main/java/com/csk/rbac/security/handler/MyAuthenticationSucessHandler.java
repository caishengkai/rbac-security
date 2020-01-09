package com.csk.rbac.security.handler;

import com.csk.rbac.common.RbacConstant;
import com.csk.rbac.common.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:自定义登录失败逻辑
 * @author: caishengkai
 * @time: 2019/12/30 11:19
 **/
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 登录成功后，跳转到之前被拦截的url
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(RbacConstant.JSON_UTF8);
        response.getWriter().write(mapper.writeValueAsString(ServerResponse.createBySuccess()));
    }
}
