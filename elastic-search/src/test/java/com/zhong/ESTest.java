package com.zhong;

import com.zhong.es.config.ElasticSearchConfig;
import com.zhong.es.service.impl.EsDataManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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


}
