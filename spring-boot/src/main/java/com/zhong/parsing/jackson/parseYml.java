//package com.zhong.parsing.jackson;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.zhong.parsing.snakeyaml.YmlConfig;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Map;
//
///**
// * Created by cc on 2022/6/19
// */
//public class parseYml {
//
//    public static void main(String[] args) throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        InputStream resourceAsStream =
//                parseYml.class.getClassLoader().getResourceAsStream("parse.yml");
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//        assert resourceAsStream != null;
//        Map map = mapper.readValue(resourceAsStream, Map.class);
//        Map<String, Object> oo = (Map) ((Map) map.get("meta")).get("oss");
//
//        YmlConfig ymlConfig = new YmlConfig();
//        ymlConfig.setUrl((String) oo.get("url"));
//        ymlConfig.setPassword((String) oo.get("password"));
//        ymlConfig.setUsername((String) oo.get("username"));
//
//        System.out.println(ymlConfig);
//
//    }
//
//}
