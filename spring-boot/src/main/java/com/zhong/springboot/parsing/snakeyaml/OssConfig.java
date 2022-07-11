package com.zhong.springboot.parsing.snakeyaml;

import lombok.Data;

/**
 * @date 2022/6/23 10:20
 */
@Data
public class OssConfig {


    private Tero tero;
}
@Data
class Tero {

    private File file;
}
@Data
class File {

    private Aliyun aliyun;
    private String suffix;
}
@Data
class Aliyun {

    private Oss oss;
}
@Data
class Oss {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String uploadPath;

}