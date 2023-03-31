package com.zhong.springboot;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    static final String URL = "jdbc:mysql://127.0.0.1/zane?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    @Test
    void contextLoads() {
    }

    @Test
    void FastAutoGenerator() {
        FastAutoGenerator.create(URL, "root", "root")
                .globalConfig(x -> {
                    x.author("xiao-pang");
                    x.outputDir("F:\\test");
                })

                .strategyConfig(x -> {
                    x.addInclude("base");
                })

                .execute();
    }

}
