package com.csk.rbac.system.service;

import com.csk.rbac.common.service.IBaseService;
import com.csk.rbac.system.model.User;

import java.util.List;

public interface IUserService extends IBaseService<User> {
    User selectByName(String username);

    List<User> findAllUser(User user);
}
