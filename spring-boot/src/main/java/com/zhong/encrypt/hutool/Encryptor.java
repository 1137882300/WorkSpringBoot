package com.zhong.encrypt.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zhong.utils.SpringUtil;
import org.springframework.core.env.Environment;

/**
 * @author: juzi
 * @date: 2023/6/13
 * @desc: 加解密接口
 */
public interface Encryptor {


    Encryptor WEB = new AesEncryptor(SecureUtil.aes(StrUtil.bytes(SpringUtil.getBean(Environment.class).getProperty("web.aes.key"))));

    Encryptor DB = new AesEncryptor(SecureUtil.aes(StrUtil.bytes(SpringUtil.getBean(Environment.class).getProperty("db.aes.key"))));

    /**
     * 加密
     */
    String encrypt(String content);

    /**
     * 解密
     */
    String decrypt(String content);

}
