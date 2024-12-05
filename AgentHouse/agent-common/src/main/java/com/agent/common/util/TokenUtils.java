package com.agent.common.util;

import com.agent.common.exception.BizException;
import com.agent.common.model.ApiPairDTO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * TokenUtils
 *
 * @author lll
 */
@Slf4j
public class TokenUtils {

    private final static String base64Secret = "5Lit5Zu95pyJNTbkuKrmsJHml48=";
    // 1天 = 86400000
    private final static long expireMillSeconds = 100 * 24 * 60 * 60 * 1000L;

    private final static String issuer = "DefeServer";

    /**
     * 创建token
     *
     * @param userId
     * @param apiKey
     * @param apiSecret
     * @return
     */
    public static String createToken(final String userId, final String apiKey, final String apiSecret) {
        String privateId = new Date().toString();
        return JwtHelper.createJWT(privateId, userId, apiKey, apiSecret, issuer, expireMillSeconds, base64Secret);
    }

    private static Claims parseJWT(String jsonWebToken) {
        return JwtHelper.parseJWT(jsonWebToken, base64Secret);
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static ApiPairDTO getApiPair(final String token) {
        if (StringUtils.isBlank(token)) {
            throw new BizException("Invalid api key!");
        }

        Claims cs = parseJWT(token);
        if (cs == null || !StringUtils.equals(cs.getIssuer(), issuer) || StringUtils.isBlank(cs.getSubject())) {
            throw new BizException("Invalid api key!");
        }

        final String apiKey = (String) cs.get("apiKey");
        final String apiSecret = (String) cs.get("apiSecret");
        if (StringUtils.isAnyBlank(apiKey, apiSecret)) {
            throw new BizException("Invalid api key!");
        }

        return new ApiPairDTO(apiKey, apiSecret, cs.getSubject());
    }

    public static void main(String[] args) {
        String address = "0xC94D98e65271B5Dc648023d9F798F0Ae16b8B09E";
        String apiKey = "190e910e-6065-4e77-952d-9ec9f68d6d68";
        String apiSecret = "0a58deee-15c1-49eb-997b-ddf7ae592c66";
        System.out.println(createToken(address, apiKey, apiSecret));
    }
}
