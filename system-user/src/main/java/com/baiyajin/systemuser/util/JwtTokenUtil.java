package com.baiyajin.systemuser.util;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;
    private static String secret = "rent_bus";

    public JwtTokenUtil() {
    }

    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception var3) {
            username = null;
        }

        return username;
    }

    public static JSONObject validateToken(Claims claims) {
        JSONObject json1 = new JSONObject();
        json1.put("userId", claims.get("userId"));
        json1.put("realName", claims.get("realName"));
        json1.put("phone", claims.get("phone"));
        json1.put("userName", claims.get("userName"));
        json1.put("idCard", claims.get("idCard"));
        json1.put("date", claims.get("date"));
        return json1;
    }

    public static boolean isPast(Claims claims) {
        boolean result = false;
        long nowDate = System.currentTimeMillis();
        Long date = Long.valueOf(claims.get("date").toString());
        if (nowDate - date.longValue() > 1800000L) {
            result = true;
        }

        return result;
    }

    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = (Claims)Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception var3) {
            claims = null;
        }

        return claims;
    }

    public static String generateToken(String userId, String userName, String realName, String idCard, String phone) {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", userId);
        claims.put("userName", userName);
        claims.put("idCard", idCard);
        claims.put("phone", phone);
        claims.put("realName", realName);
        claims.put("date", System.currentTimeMillis());
        return generateToken(claims);
    }

    public static String generateToken(String userName, String password) {
        Map<String, Object> claims = new HashMap();
        claims.put("userName", userName);
        claims.put("password", password);
        return generateToken(claims);
    }

    static String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("date", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception var3) {
            refreshedToken = null;
        }

        return refreshedToken;
    }
}

