package com.zhong.es.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @date 2022/7/14 21:04
 */
public interface ESReadService {

    SearchResponse search(String index, SearchSourceBuilder builder);

}
