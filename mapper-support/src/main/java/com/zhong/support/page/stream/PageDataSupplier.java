package com.zhong.support.page.stream;

import java.util.function.Supplier;

/**
 * 分页数据懒加载器
 */
public interface PageDataSupplier<T>  extends Supplier<T> {
}
