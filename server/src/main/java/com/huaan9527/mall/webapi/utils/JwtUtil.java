package com.huaan9527.mall.webapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET_KEY = "Junh";

    // 生成 JWT
    public static String generateToken(String openid, String sessionKey) {
        return JWT.create()
                .withSubject(openid)  // 用户唯一标识 (openid)
                .withClaim("session_key", sessionKey)  // 添加 session_key
                .sign(Algorithm.HMAC256(SECRET_KEY));  // 使用 HMAC256 算法进行签名
    }

    // 验证 JWT
    public static boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 获取 openid
    public static String getOpenidFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return decodedJWT.getSubject();  // 获取 subject，即openid
    }
}
