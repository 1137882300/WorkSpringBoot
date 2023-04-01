package com.zhong.parsing.snakeyaml;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Properties;

/**
 * @date 2022/6/23 9:50
 */
public class Test3 {

    public static void main(String[] args) throws NacosException, JsonProcessingException {

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "nacos-headless.bbmall-middleware.svc.cluster.bbmall:8848");
        properties.put(PropertyKeyConst.NAMESPACE, "gmp-dev");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig("common-oss.yml", null, 3000);
        System.out.println(config);

//        Yaml yaml = new Yaml();
//        Map map = yaml.loadAs(config, Map.class);
//        Map tero = (Map) map.get("tero");
//        Map file = (Map) tero.get("file");
//        Map aliyun = (Map) file.get("aliyun");
//        Map oss = (Map) aliyun.get("oss");
//
//        GmpOssConfig gmpOssConfig = new GmpOssConfig();
//        gmpOssConfig.setEndpoint((String) oss.get("endpoint"));
//        gmpOssConfig.setAccessKeyId((String) oss.get("access-key-id"));
//        gmpOssConfig.setAccessKeySecret((String) oss.get("access-key-secret"));
//        gmpOssConfig.setBucketName((String) oss.get("bucket-name"));
//        gmpOssConfig.setUploadPath((String) oss.get("uploadPath"));
//
//        System.out.println(gmpOssConfig);

//        OssConfig ossConfig = yaml.loadAs(config, OssConfig.class);
//        System.out.println(ossConfig);

    }


}
