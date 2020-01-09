package com.csk.rbac.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:RBAC总配置类
 * @author: caishengkai
 * @time: 2019/12/31 10:12
 **/
@ConfigurationProperties(prefix = "rbac")
public class RbacProperties {

    //登录url
    private String loginUrl;
    // 免认证静态资源路径
    private String anonResourcesUrl;
    //验证码配置类
    private CodeProperties code = new CodeProperties();

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getAnonResourcesUrl() {
        return anonResourcesUrl;
    }

    public void setAnonResourcesUrl(String anonResourcesUrl) {
        this.anonResourcesUrl = anonResourcesUrl;
    }

    public CodeProperties getCode() {
        return code;
    }

    public void setCode(CodeProperties code) {
        this.code = code;
    }
}
