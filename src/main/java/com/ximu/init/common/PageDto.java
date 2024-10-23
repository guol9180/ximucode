package com.ximu.init.common;

import com.ximu.init.constant.CommonConstant;
import lombok.Data;

/**
 * @author: 析木
 * @description: 分页请求属性
 */
@Data
public class PageDto {

    /**
     * 分页偏移量
     */
    private Integer currentPage = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
