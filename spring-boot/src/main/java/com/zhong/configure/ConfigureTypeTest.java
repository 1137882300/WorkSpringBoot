package com.zhong.configure;

import lombok.Data;

import java.util.HashMap;

/**
 * @author: juzi
 * @date: 2023/10/9
 * @desc:
 */
@Data
//@ConfigurationProperties(prefix = "yxk.micro")
public class ConfigureTypeTest {

    private HashMap<String, String> platform = new HashMap<>();

    public void map() {
        //properties 配置：
        //yxk.micro.platform[oms]=a818c929d29f6
        //platform 属性值, key：oms, value: a818c929d29f6
    }


}
