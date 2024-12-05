package com.agent.provider.util;

import com.agent.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

@Slf4j
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 加密/解密算法 / 工作模式 / 填充方式 Java 6支持PKCS5Padding填充方式 Bouncy Castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final String bit = "21A4B37E-42C8-429F-D0BD-3A98889EAA39";
    private static String encryPassword = "";

    static {
        // 如果是PKCS7Padding填充方式，则必须加上下面这行
        Security.addProvider(new BouncyCastleProvider());
    }

    private static String getBit() {
        return bit + "-" + encryPassword;
    }

    /**
     * 是否设置加密密码
     *
     * @return
     */
    public static boolean haveEncryPassword() {
        return StringUtils.isEmpty(encryPassword) ? false : true;
    }

    /**
     * 生成密钥
     *
     * @return 密钥
     * @throws Exception
     */
    public static String generateKey() throws Exception {
        if (StringUtils.isEmpty(encryPassword)) {
            throw new Exception("INVALID_ENCRYPTION_PASSWORD");
        }
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(getBit().getBytes());
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(128, secureRandom);
        // 生成密钥
        SecretKey secretKey = kg.generateKey();
        // 获得密钥的字符串形式
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    public static Key getKey(String strKey) {
        try {
            if (strKey == null) {
                strKey = "";
            }
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128, secureRandom);
            return _generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }
    }

    public static String aesEncrypt(String sourceStr) {
        try {
            final String encryptKey = generateKey();
            return encrypt(sourceStr, Base64.encodeBase64String(encryptKey.getBytes()));
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    /**
     * AES加密
     *
     * @param source 源字符串
     * @param key    密钥
     * @return 加密后的密文
     * @throws Exception
     */
    private static String encrypt(String source, String key) throws Exception {
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = Base64.decodeBase64(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        // System.out.println("secretKey length:" + secretKey.getEncoded().length);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(sourceBytes);
        return Base64.encodeBase64String(decrypted);
    }

    public static String aesDecrypt(String encryptedStr) {
        try {
            final String decryptKey = generateKey();
            return decrypt(encryptedStr, Base64.encodeBase64String(decryptKey.getBytes()));
        } catch (Exception e) {
            throw new BizException(e);
        }
    }

    /**
     * AES解密
     *
     * @param encryptStr 加密后的密文
     * @param key        密钥
     * @return 源字符串
     * @throws Exception
     */
    private static String decrypt(String encryptStr, String key) throws Exception {
        byte[] sourceBytes = Base64.decodeBase64(encryptStr);
        byte[] keyBytes = Base64.decodeBase64(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM));
        byte[] decoded = cipher.doFinal(sourceBytes);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    public static String getEncryPassword() {
        return encryPassword;
    }

    public static void setEncryPassword(String encryPassword) {
        AESUtil.encryPassword = encryPassword;
        log.info("收到设置加密密码: {}", encryPassword.length());
    }
}
