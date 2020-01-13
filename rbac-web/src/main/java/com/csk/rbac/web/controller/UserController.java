package com.csk.rbac.web.controller;

import com.csk.rbac.common.QueryRequest;
import com.csk.rbac.common.RbacTree;
import com.csk.rbac.common.ServerResponse;
import com.csk.rbac.common.controller.BaseController;
import com.csk.rbac.system.model.Permission;
import com.csk.rbac.system.model.User;
import com.csk.rbac.system.service.IPermissionService;
import com.csk.rbac.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/10 14:28
 **/

@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IUserService userService;

    @PostMapping("getUserMenu")
    @ResponseBody
    public ServerResponse<RbacTree> getUserMenu(String userName) {
        RbacTree<Permission> tree = permissionService.getUserMenu(userName);
        return ServerResponse.createBySuccess(tree);
    }

    @RequestMapping("index")
    public String userIndex() {
        return "system/user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, User user) {
        return super.selectByPageNumSize(request, () -> userService.findAllUser(user));
    }
}
