package com.csk.rbac.web.controller;

import com.csk.rbac.common.QueryRequest;
import com.csk.rbac.common.controller.BaseController;
import com.csk.rbac.system.model.Role;
import com.csk.rbac.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/13 14:26
 **/
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> roleList(QueryRequest request, Role role) {
        return super.selectByPageNumSize(request, () -> roleService.findAllRole(role));
    }
}
