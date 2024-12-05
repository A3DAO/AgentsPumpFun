package com.agent.common.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.agent.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 签名工具类
 *
 * @author lll
 */
@Slf4j
public class SignatureUtil {

    /**
     * 参数键名-签名
     */
    public static final String PARAM_KEY_SIGNATURE = "Signature";
    /**
     * 哈希算法sha256
     */
    public static final String HASH_ALGORITHM_SHA256 = "HmacSHA256";
    private static final String PERSONAL_MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

    /**
     * 登录签名验签
     *
     * @param signature
     * @param address
     * @param timestamp
     * @return
     */
    public static boolean checkLogin(String signature, String address, String timestamp, String host, String signCode) {

        String message =
                "action:\n" + signCode + " Authentication\n" + "onlySignOn:\n" + host + "\n" + "timestamp:\n" + timestamp;

        String prefix = PERSONAL_MESSAGE_PREFIX + message.length();
        byte[] msgHash = Hash.sha3((prefix + message).getBytes());

        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[64];

        if (v < 27) {
            v += 27;
        }

        Sign.SignatureData sd = new Sign.SignatureData(v, Arrays.copyOfRange(signatureBytes, 0, 32),
                Arrays.copyOfRange(signatureBytes, 32, 64));

        String addressRecovered = null;
        boolean match = false;

        // Iterate for each possible key to recover
        for (int i = 0; i < 4; i++) {
            BigInteger publicKey = Sign.recoverFromSignature((byte) i,
                    new ECDSASignature(new BigInteger(1, sd.getR()), new BigInteger(1, sd.getS())), msgHash);

            if (publicKey != null) {
                addressRecovered = "0x" + Keys.getAddress(publicKey);
                if (addressRecovered.toLowerCase().equals(address.toLowerCase())) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    /**
     * 创建接口请求签名
     */
    public static String sign(String apiKey, String apiSecret, String host, String uri, String method, String timestamp,
                              Map<String, String> paramMap) throws Exception {
        assertParam(apiKey, "Illegal apiKey!");
        assertParam(apiSecret, "Illegal apiSecret!");
        assertParam(host, "Illegal host!");
        assertParam(uri, "Illegal uri!");

        // 构建待签名字符串
        final StringBuilder sb = new StringBuilder();
        sb.append(method.toUpperCase()).append('\n').append(host.toLowerCase()).append('\n').append(uri).append('\n')
                .append(timestamp).append("\n").append(apiKey).append("\n");
        paramMap.remove(PARAM_KEY_SIGNATURE);
        SortedMap<String, String> map = new TreeMap<>(paramMap);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        sb.deleteCharAt(sb.length() - 1);

        // 获取摘要字节数组
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance(HASH_ALGORITHM_SHA256);

            SecretKeySpec secKey = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), HASH_ALGORITHM_SHA256);

            hmacSha256.init(secKey);

            String plainText = sb.toString();
            if (log.isDebugEnabled()) {
                log.debug("plainText={}", plainText);
            }
            byte[] hash = hmacSha256.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // base64处理
            String actualSign = Base64.getEncoder().encodeToString(hash);
            return actualSign;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 签名元素合法校验
     */
    private static void assertParam(String param, String message) {
        if (StrUtil.isBlank(param)) {
            throw new BizException(message);
        }
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    public static void main(String[] args) throws Exception {
        String apiKey = "41c93a7a-1b1c-4a8b-94df-536665b4eae3";
        String apiSecret = "efc4b03f-f435-4050-a58f-40903714eb4e";
        String host = "defe-api.otbestsuite.com";
        String uri = "/api/v1/trade/order";
        String method = "POST";
        String timestamp = "1658889772224";
        Map<String, String> paramMap = MapUtil.newHashMap();
        String sign = sign(apiKey, apiSecret, host, uri, method, timestamp, paramMap);
        System.out.println(sign);
    }

}
