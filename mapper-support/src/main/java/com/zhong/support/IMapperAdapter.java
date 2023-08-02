package com.zhong.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * mapper 基类
 */
public interface IMapperAdapter<T> extends BaseMapper<T> {

    /**
     * 查询所有
     */
    List<T> selectAll();

    /**
     * 批量插入
     */
    void insertList(List<T> list);

    /**
     * 根据id判断记录是否存在，存在返回1，否则返回null
     */
    Integer existById(Serializable id);

    /**
     * 软删除  使用 update table set deleted=0
     */
    int softDeleteById(Serializable id);

    /**
     * 软根据id判断记录是否存在，存在返回1，否则返回null 增加 where deleted=0
     */
    Integer softExistById(Serializable id);

    /**
     * 软查询 增加 where deleted=0
     */
    T softSelectById(Serializable id);

    /**
     * 软查询（根据ID 批量查询） 增加 where deleted=0
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> softSelectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 软查询所有 增加 where deleted=0
     */
    List<T> softSelectAll();
}
