package com.jrmarcco.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询请求。
 *
 * @author jrmarcco
 * @version 1.0  2018/11/19
 */
@Data
public class PageQueryReq<T> implements Serializable {
    private static final long serialVersionUID = -5107433058914466982L;

    private Integer pageIndex;
    private Integer pageSize;

    private T queryCondition;

    public PageQueryReq() {
        this.pageIndex = 1;
        this.pageSize = 20;
    }
}
