package com.zhong.encrypt.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author juzi
 * @date 2023/6/13 上午 10:17
 * @description aes 加解密实现
 */
public class AesEncryptor implements Encryptor {

    private final AES aes;

    public AesEncryptor(AES aes) {
        this.aes = aes;
    }

    /**
     * 加密
     */
    @Override
    public String encrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return StrUtil.EMPTY;
        }
        return aes.encryptBase64(content);
    }

    /**
     * 解密
     */
    @Override
    public String decrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return StrUtil.EMPTY;
        }
        return aes.decryptStr(content);
    }



}
