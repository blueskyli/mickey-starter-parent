package com.mickey.core.utils.common;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Description: DES加密工具类
 * @author J·K
 * @date 2020/2/27 2:16 下午
 */
@Slf4j
public class DESUtil {

    /**
     * 加密方式
     */
    private static final String ALGORITHM = "DES";

    private static Key key;
    /**
     * 密钥key
     */
    private static String CIPHER = "489923eddfa74d7482c458b1dd6e26d4";
    /**
     * 编码类型
     */
    private static String CHARSET = "UTF-8";

    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上密钥种子
            secureRandom.setSeed(CIPHER.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            log.error("DESUtil 类加载 初始化加密对象异常");
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        BASE64Encoder base64encoder = new BASE64Encoder();
        try {
            byte[] bytes = str.getBytes(CHARSET);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return base64encoder.encode(doFinal);
        } catch (Exception e) {
            log.error("DESUtil 加密 Exception ：", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 解密
     *
     * @param cipherText
     * @return
     */
    public static String decrypt(String cipherText) {
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            byte[] bytes = base64decoder.decodeBuffer(cipherText);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSET);
        } catch (Exception e) {
            log.error("DESUtil 解密 Exception ：", e);
            throw new RuntimeException(e);
        }
    }
}
