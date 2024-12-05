package com.agent.common.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtHelper
 *
 * @author lll
 */
public class JwtHelper {

    /**
     * 解析JWT
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 创建JWT
     *
     * @param no
     * @param userId
     * @param apiKey
     * @param apiSecret
     * @param issuer
     * @param TTLMillis
     * @param base64Security
     * @return
     */
    public static String createJWT(String no, String userId, final String apiKey, final String apiSecret, String issuer,
                                   long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("no", no)
                .claim("system_date", System.currentTimeMillis()).claim("apiKey", apiKey).claim("apiSecret", apiSecret)
                .setSubject(userId).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);

        // //添加Token过期时间
        // long nowMillis = System.currentTimeMillis();
        // if (TTLMillis >= 0) {
        // long expMillis = nowMillis + TTLMillis;
        // Date exp = new Date(expMillis);
        // builder.setExpiration(exp).setNotBefore(new Date());
        //
        // }

        // 生成JWT
        return builder.compact();
    }

}
