package com.zhong.encrypt.handwriting;

import com.zhong.utils.SpringUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: juzi
 * @date: 2023/6/13
 * @desc: 加密配置类
 */
@ConfigurationProperties(prefix = "crypto")
@Component
@Data
public class CryptoConfig {
    private AESEntity aes;
    private MD5Entity md5;

    @Data
    public static class AESEntity {
        private String secretKey;
        private String iv;
    }

    @Data
    public static class MD5Entity {
        private String salt;
    }


    public static CryptoConfig getInstance() {
        return SpringUtil.getBean(CryptoConfig.class);
    }
}