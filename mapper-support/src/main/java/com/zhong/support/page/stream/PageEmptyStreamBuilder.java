package com.zhong.support.page.stream;

import java.util.Collections;
import java.util.List;

/**
 * 空流式分页类
 */
public class PageEmptyStreamBuilder<T> extends PageEndStreamBuilder<T> {

    private List<T> list;
    private int total;
    private int pageSize;


    public PageEmptyStreamBuilder() {
        super(Collections.emptyList(),null);

    }

    @Override
    protected int getPageSize() {
        return 0;
    }

    @Override
    protected int getTotal() {
        return 0;
    }


}
