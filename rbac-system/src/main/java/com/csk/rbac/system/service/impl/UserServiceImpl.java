package com.csk.rbac.system.service.impl;

import com.csk.rbac.common.service.impl.BaseServiceImpl;
import com.csk.rbac.system.model.User;
import com.csk.rbac.system.service.IUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/30 14:11
 **/
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Override
    public User selectByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", username);
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }
}
