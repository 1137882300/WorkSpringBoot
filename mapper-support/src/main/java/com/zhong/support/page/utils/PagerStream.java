package com.zhong.support.page.utils;


import com.zhong.support.page.stream.PageEmptyStreamBuilder;
import com.zhong.support.page.stream.PageStartStreamBuilder;

/**
 *
 */
public class PagerStream {


    public static final int PAGE_SIZE = 20;

    /**
     * 流式分页-不需要调用 PagerStream.startPage
     */
    public static PageStartStreamBuilder page(Integer page) {
        return page(page, PAGE_SIZE);
    }


    /**
     * 流式分页-不需要调用 PagerStream.startPage
     */
    public static PageStartStreamBuilder page(Integer page, int pageSize) {
        int pageDefault = 1;
        if (page != null && page > 0) {
            pageDefault = page;
        }
        return new PageStartStreamBuilder(pageDefault, pageSize);
    }


    public static PageEmptyStreamBuilder<?> empty() {
        return new PageEmptyStreamBuilder<>();
    }


}
