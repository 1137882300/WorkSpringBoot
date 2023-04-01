//package com.zhong.parsing.snakeyaml;
//
//import org.yaml.snakeyaml.Yaml;
//
//import java.io.InputStream;
//import java.util.Map;
//
///**
// * Created by cc on 2022/6/19
// */
//public class Test2 {
//
//    public static void main(String[] args) {
//        InputStream inputStream = Test2.class.getClassLoader().getResourceAsStream("application.yml");
//        Map load = new Yaml().load(inputStream);
////        System.out.println(load);//meta={oss={url=zjkhasdbnqwuo, password=asdasdasdas, username=pouiijnoand}}}
//        Map meta = (Map) load.get("meta");
//        Map oss = (Map) meta.get("oss");
//        String password = (String) oss.get("password");
//        System.out.println(password);
//
//
//    }
//
//}
