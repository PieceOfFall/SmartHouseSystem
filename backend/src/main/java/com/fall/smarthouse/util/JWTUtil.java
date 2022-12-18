package com.fall.smarthouse.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author FAll
 * @date 2022/12/4 15:34
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {

    // 前缀盐值
    public static final String SALT_BEFORE = "my";

    // 后缀盐值
    public static final String SALT_AFTER = "name";

    // token 返回头部
    public static String header;

    // token 前缀
    public static String tokenPrefix;

    // 签名密钥
    public static String secret;

    // 有效期
    public static long expireTime;

    /**
     * @param header
     * @author FAll
     * @description 设置 token 头部
     * @date 2022/12/4 15:39
     */
    public void setHeader(String header) {
        JWTUtil.header = header;
    }

    /**
     * @param tokenPrefix
     * @author FAll
     * @description 设置 token 前缀
     * @date 2022/12/4 15:39
     */
    public void setTokenPrefix(String tokenPrefix) {
        JWTUtil.tokenPrefix = tokenPrefix;
    }

    /**
     * @param secret
     * @author FAll
     * @description 设置 token 密钥
     * @date 2022/12/4 15:39
     */
    public void setSecret(String secret) {
        JWTUtil.secret = secret;
    }

    /**
     * @param expireTimeInt
     * @author FAll
     * @description 设置 token 有效时间
     * @date 2022/12/4 15:39
     */
    public void setExpireTime(int expireTimeInt) {
        JWTUtil.expireTime = expireTimeInt;
    }

    /**
     * @param sub
     * @author FAll
     * @description 创建 TOKEN
     * @return: java.lang.String
     * @date 2022/12/4 15:39
     */
    public static String createToken(String sub) {
        return tokenPrefix + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000 * 60 * 60 * 24))
                .sign(Algorithm.HMAC512(secret));
    }

    /**
     * @author FAll
     * @description 验证 token
     * @return: java.lang.String
     * @date 2022/12/4 15:39
     */
    public static String validateToken(String token) throws Exception {
        try {
            Verification verification = JWT.require(Algorithm.HMAC512(secret));
            JWTVerifier jwtVerifier = verification.build();
            // 去除 token 的前缀
            String noPrefixToken = token.replace(tokenPrefix, "");
            DecodedJWT decodedJwt = jwtVerifier.verify(noPrefixToken);
            if (decodedJwt != null) {
                return decodedJwt.getSubject();
            }
            return "";
        } catch (TokenExpiredException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }

    /**
     * @param token
     * @author FAll
     * @description 检查 token 是否需要更新
     * @return: boolean
     * @date 2022/12/4 15:39
     */
    public static boolean isNeedUpdate(String token) {
        // 获取 token 过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (Exception e) {
            return true;
        }
        // 需要更新
        return (expiresAt.getTime() - System.currentTimeMillis()) < (expireTime >> 1);
    }

}
