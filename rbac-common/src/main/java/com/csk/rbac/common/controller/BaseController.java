package com.csk.rbac.common.controller;

import com.csk.rbac.common.QueryRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/13 15:30
 **/
public class BaseController {

    /**
     * 前端列表查询通用方法
     * @param request 包含列表查询起始行和每页行数的分装类
     * @param supplier 具体业务模块列表的查询方法，Supplier是一个接口，内部有一个get()方法，只有调用get()方法才会真正去查询列表
     * @return
     */
    protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> supplier) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>)supplier.get());
        PageHelper.clearPage();
        return getDataTable(pageInfo);
    }

    private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getList());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
}
