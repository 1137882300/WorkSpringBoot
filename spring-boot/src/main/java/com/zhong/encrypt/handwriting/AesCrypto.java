package com.zhong.encrypt.handwriting;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * @author: juzi
 * @date: 2023/6/13
 * @desc:
 */
public class AesCrypto {

    /**
     * @author juzi
     * @date 2023/6/13 上午 10:29
     * @description AES/CBC/PKCS5Padding
     */
    public static String decrypt(String content, String slatKey, String vectorKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(slatKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(Hex.decodeHex(content));
        return new String(encrypted);
    }

    /**
     * @author juzi
     * @date 2023/6/13 上午 10:29
     * @description AES/CBC/PKCS5Padding
     */
    public static String encrypt(String content, String slatKey, String vectorKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(slatKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return Hex.encodeHexString(encrypted);
    }


    /**
     * @author juzi
     * @date 2023/6/13 上午 10:30
     * @description AES/CBC/PKCS7Padding
     */
    public static String decryptOfPKCS7(String content, String slatKey, String vectorKey) throws Exception {
        byte[] aesKey = Base64.getDecoder().decode(slatKey);
        byte[] aesIV = Base64.getDecoder().decode(vectorKey);
        byte[] aesCipher = Base64.getDecoder().decode(content);
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        Key sKeySpec = new SecretKeySpec(aesKey, "AES");
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(aesIV));
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
        return new String(cipher.doFinal(aesCipher), StandardCharsets.UTF_8);
    }

}
