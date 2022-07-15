package com.zhong.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhong.es.model.EsBaseDTO;
import com.zhong.es.service.ESReadService;
import com.zhong.es.service.ESWriteService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;

/**
 * @date 2022/7/14 20:15
 */
@Slf4j
@Component
public class EsDataManager implements ESWriteService, ESReadService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean create(String index, String alias, XContentBuilder builder) {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder()
                .put("number_of_shards", 3)
                .put("number_of_replicas", 2));
        if (alias != null) {
            request.alias(new Alias(alias));
        }
        if (builder != null) {
            request.mapping(builder);
        }
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
            log.info("create acknowledged:{},index:{}", acknowledged, index);
            log.info("create shardsAcknowledged:{},index:{}", shardsAcknowledged, index);
            if (acknowledged) {
                return Boolean.TRUE;
            }
        } catch (Throwable t) {
            log.error("exception:", t);
        }
        return Boolean.FALSE;
    }

    @Override
    public <T extends EsBaseDTO> boolean save(T t, String index) {
        IndexRequest indexRequest = new IndexRequest()
                .index(index)
                .id(Optional.ofNullable(t.getId()).orElse(null))
                .source(JSON.toJSONString(t), XContentType.JSON)
                .opType(DocWriteRequest.OpType.INDEX)//操作类型
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)//即使刷新
                ;
        IndexResponse response;
        try {
            response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                log.error("save error ,version conflict,index:{}", indexRequest.index());
            }
            return Boolean.FALSE;
        } catch (Exception throwable) {
            log.error("save error, index:{}", indexRequest.index(), throwable);
            return Boolean.FALSE;
        }
        return response.status() == RestStatus.OK || response.status() == RestStatus.CREATED;
    }

    @Override
    public SearchResponse search(String index, SearchSourceBuilder builder) {
        if (builder == null) {
            return null;
        }
        SearchRequest searchRequest = new SearchRequest()
                .indices(index)
                .source(builder);
        try {
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Throwable e) {
            log.error("search error , index:{}", Arrays.toString(searchRequest.indices()));
        }
        return null;
    }
}
