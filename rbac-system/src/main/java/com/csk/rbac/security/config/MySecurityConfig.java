package com.csk.rbac.security.config;

import com.csk.rbac.common.properties.RbacProperties;
import com.csk.rbac.security.code.img.ImageCodeFilter;
import com.csk.rbac.security.handler.MyAuthenticationFailureHandler;
import com.csk.rbac.security.handler.MyAuthenticationSucessHandler;
import com.csk.rbac.security.service.MyUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import tk.mybatis.mapper.common.Mapper;

import javax.sql.DataSource;

/**
 * @description: SpringSecurity base 认证配置类
 * @author: caishengkai
 * @time: 2019/12/30 10:53
 **/
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private ImageCodeFilter imgCodeFilter;
    @Autowired
    private RbacProperties rbacProperties;

    // spring security自带的密码加密工具类
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 处理 rememberMe 自动登录认证
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(rbacProperties.getAnonResourcesUrl(),",");

        http.formLogin() //使用表单登录
                .loginPage(rbacProperties.getLoginUrl()) //设置登录页
                .loginProcessingUrl(rbacProperties.getCode().getLoginProcessingUrl()) //对应登录页的提交请求 action="/login"
                .successHandler(authenticationSucessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .rememberMe() //记住我
                .tokenRepository(persistentTokenRepository()) //配置token持久化仓库
                .tokenValiditySeconds(3600) //remember过期时间 单位为秒
                .userDetailsService(userDetailsService) //处理自动登录的逻辑
                .and()
                .authorizeRequests() //授权配置
                .antMatchers(anonResourcesUrl).permitAll() // 免认证静态资源路径
                .antMatchers(
                        rbacProperties.getLoginUrl(),
                        rbacProperties.getCode().getCreateUrl()
                ).permitAll() //登录页放行
                .anyRequest() //所有请求
                .authenticated() //都要认证
                .and().csrf().disable() //关闭CSRF攻击防御
                .addFilterBefore(imgCodeFilter, UsernamePasswordAuthenticationFilter.class); //图像验证码校验过滤器，放在账号密码校验过滤器之前
    }

}
