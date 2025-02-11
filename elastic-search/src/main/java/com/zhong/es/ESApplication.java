package com.zhong.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class ESApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class, args);
        log.info("ESApplication 启动成功");
    }

}
