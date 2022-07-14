package com.zhong.es.service;

import com.zhong.es.model.EsBaseDTO;
import org.elasticsearch.common.xcontent.XContentBuilder;

/**
 * @date 2022/7/14 21:04
 */
public interface ESWriteService {


    boolean create(String index, String alias, XContentBuilder builder);

    <T extends EsBaseDTO> boolean save(T t, String index);

}
