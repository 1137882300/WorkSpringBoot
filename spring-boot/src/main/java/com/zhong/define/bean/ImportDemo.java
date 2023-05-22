package com.zhong.define.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: Import 导入的类优先于本类
 */

@Import(ConfigDemo.class)
@Configuration
public class ImportDemo {


}
