package com.zhong.es.service.impl;

import com.zhong.es.model.EsBaseDTO;
import com.zhong.es.service.ESWriteService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @date 2022/7/14 20:15
 */
@Slf4j
@Component
public class EsDataManager implements ESWriteService {

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
        return false;
    }
}
