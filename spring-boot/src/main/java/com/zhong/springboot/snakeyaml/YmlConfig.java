package com.zhong.springboot.snakeyaml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by cc on 2022/6/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class YmlConfig {


    private String url;
    private String password;
    private String username;

}
