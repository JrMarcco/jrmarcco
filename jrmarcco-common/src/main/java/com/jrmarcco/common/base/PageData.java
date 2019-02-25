package com.jrmarcco.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hongjc
 * @version 1.0  2019/1/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 2396584634756814954L;

    private List<T> list;
    private long total;
}
