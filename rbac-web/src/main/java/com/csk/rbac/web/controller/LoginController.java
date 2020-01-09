package com.csk.rbac.web.controller;

import com.csk.rbac.common.RbacConstant;
import com.csk.rbac.security.code.ValidateCodeGenerator;
import com.csk.rbac.security.code.img.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/31 9:52
 **/
@Controller
public class LoginController {

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();

    @GetMapping("/image/code")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.createCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request), RbacConstant.SESSION_KEY_IMAGE_CODE, imageCode);
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            //log.info("引发跳转的请求是：{}", redirectUrl);
            System.out.println("引发跳转的请求是：" + redirectUrl);
        }
        return "login";
    }

    @GetMapping("index2")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("user",authentication.getPrincipal());
        return "index2";
    }

    @GetMapping("/")
    public void success(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/index2");
    }
}
