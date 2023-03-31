package com.zhong;

import com.alibaba.fastjson.JSON;
import com.zhong.entity.EsEntity;
import com.zhong.es.config.ElasticSearchConfig;
import com.zhong.es.service.impl.EsDataManager;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2022/7/15 15:56
 */
@Slf4j
@SpringBootTest(classes = {EsDataManager.class, ElasticSearchConfig.class})
public class ESTest {

    @Resource
    private EsDataManager esDataManager;

    @Test
    public void createIndex() {
        String index = "test_index";
        String alias = "test_index_alias";
        boolean b = esDataManager.create(index, null, null);
        log.info(String.format("创建是否成功:%s , index=%s , alias=%s", b, index, alias));

    }

    @Test
    public void saveIndex() {
        String index = "test_index";
        EsEntity esEntity = new EsEntity();
        esEntity.setBrandId("11");
        esEntity.setFeatures("name");
        esEntity.setAge(15);
        boolean save = esDataManager.save(esEntity, index);
        log.info(String.format("创建是否成功:%s , index=%s", save, index));
    }

    @Test
    public void search() {
        String index = "test_index";
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        bqb.filter(QueryBuilders.termQuery("age", 15));
        ssb.query(bqb);
        log.info("search ssb=" + ssb);
        SearchResponse response = esDataManager.search(index, ssb);
        List<EsEntity> entityList = Arrays.stream(response.getHits().getHits()).map(x -> JSON.parseObject(x.getSourceAsString(), EsEntity.class)).collect(Collectors.toList());
        log.info("search result=" + JSON.toJSONString(entityList));
    }

}
