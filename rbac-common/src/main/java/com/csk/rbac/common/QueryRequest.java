package com.csk.rbac.common;

import java.io.Serializable;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/13 15:35
 **/
public class QueryRequest implements Serializable {

    private int pageSize;
    private int pageNum;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
