package com.zhong.springboot.snakeyaml;

import lombok.Data;

/**
 * @date 2022/6/22 19:55
 */
@Data
public class GmpOssConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String uploadPath;

}
