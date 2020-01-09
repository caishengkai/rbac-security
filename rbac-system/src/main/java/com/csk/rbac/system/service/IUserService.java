package com.csk.rbac.system.service;

import com.csk.rbac.common.service.IBaseService;
import com.csk.rbac.system.model.User;

public interface IUserService extends IBaseService<User> {
    User selectByName(String username);
}
