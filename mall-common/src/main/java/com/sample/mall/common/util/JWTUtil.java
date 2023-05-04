package com.sample.mall.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;

import java.util.Base64;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by LuoboGan
 * Date:2023-05-04
 */
public class JWTUtil {

    public static void main(String[] args) throws InterruptedException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);

        String secret = "123456";

        String token = JWT.create()
                .withHeader(Maps.newHashMap())
                .withClaim("id",666)
                .withClaim("name","luobogan")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(secret));
        System.out.println("\n=== 生成Token ===");
        System.out.println(token);

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String header = decodedJWT.getHeader();
        String payload = decodedJWT.getPayload();
        String signature = decodedJWT.getSignature();
        System.out.println("\n === 验证Token ===");
        System.out.println(header);
        System.out.println(new String(Base64.getDecoder().decode(header)));
        System.out.println(payload);
        System.out.println(new String(Base64.getDecoder().decode(payload)));
        System.out.println(signature);

        System.out.println("\n === 解析Token ===");
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        for(Map.Entry<String,Claim> claimEntry: claimMap.entrySet()){
            System.out.println(claimEntry.getKey() + "-" + claimEntry.getValue());
        }

        System.out.println("\n === 验证Token过期 ===");
        Thread.sleep(6000L);
        DecodedJWT verify = jwtVerifier.verify(token);
        System.out.println(verify);
    }

}
