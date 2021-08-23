package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wan
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021年08月06日 17:41:58
 */
@Data
@NoArgsConstructor
public class PageInfo {

    //页数
    private int pageNum;
    //每页个数
    private int pageSize;
    private int preLimetNum;
    private int endLimitNum;

    /**
     * 分页
     * @param pageInfo
     */
    public static void pagination(PageInfo pageInfo) {
        int pageNum = pageInfo.getPageNum();
        int pageSize = pageInfo.getPageSize();
        pageInfo.setPreLimetNum(pageNum == 0 ? 1 : (pageSize == 0 ? (pageNum-1)*10 : (pageNum-1)*pageSize));
        pageInfo.setEndLimitNum(pageSize == 0 ? 10 : pageSize);
    }
}
