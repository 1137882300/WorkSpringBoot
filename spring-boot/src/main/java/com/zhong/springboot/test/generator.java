package com.zhong.springboot.test;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.HashMap;

/**
 * Created by cc on 2022/6/12
 */
public class generator {

    static final String URL = "jdbc:mysql://127.0.0.1/zane?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/spring-boot/src/main/java/";
        String xmlPath = System.getProperty("user.dir") + "/spring-boot/src/main/resources/mapper";

        FastAutoGenerator.create(URL, "root", "root")
                //全局配置
                .globalConfig(x -> x.author("xiao-pang").outputDir(path).disableOpenDir().fileOverride())
                //策略配置：哪些表
                .strategyConfig(x -> {
                    x.addInclude("basic")
                            .entityBuilder().idType(IdType.ASSIGN_UUID).versionColumnName("version")
                            .enableColumnConstant().enableChainModel().enableTableFieldAnnotation()
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))

                    ;
                })

                //包配置
                .packageConfig(x -> x.parent("com.zhong.springboot").entity("po").controller("controller").service("service")
                        .serviceImpl("service.impl").mapper("mapper").xml("mapper.xml")
                        .pathInfo(new HashMap<OutputFile, String>() {{
                            put(OutputFile.mapperXml, xmlPath);
                        }}))

                //模板引擎
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

}
