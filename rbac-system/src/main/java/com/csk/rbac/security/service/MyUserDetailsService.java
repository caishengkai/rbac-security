package com.csk.rbac.security.service;

import com.csk.rbac.common.RbacConstant;
import com.csk.rbac.common.utils.DateUtil;
import com.csk.rbac.security.model.MyUserDetails;
import com.csk.rbac.system.model.User;
import com.csk.rbac.system.service.IPermissionService;
import com.csk.rbac.system.service.IUserService;
import com.csk.rbac.system.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/30 11:41
 **/
@Configuration
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.selectByName(username);
        if (user != null) {
            String permissions = permissionService.findUserPermissions(user.getUserId());
            //未被锁定
            boolean notLocked = true;
            if (StringUtils.equals(user.getStatus(), RbacConstant.STATUS_LOCK)) {
                notLocked = false;
            }
            MyUserDetails userDetails = new MyUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            userDetails.setTheme(user.getTheme());
            userDetails.setAvatar(user.getAvatar());
            userDetails.setEmail(user.getEmail());
            userDetails.setMobile(user.getMobile());
            userDetails.setSsex(user.getSsex());
            userDetails.setUserId(user.getUserId());
            userDetails.setPassword(user.getPassword());
            userDetails.setLoginTime(DateUtil.dateToStr(new Date()));
            return userDetails;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
