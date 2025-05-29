package com.seph_worker.worker.security;

import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class TokenUtils {
    private final static String ACCESS_TOKEN = "X\"qZ5:+c=]$8H2h3&y[pF^a;,P'6z7r)#}EQYCxAwU!VD~{Sg4";

    //private final static Long ACCESS_TOKEN_EXPIRES_IN = 30L; // Duración en segundos
   private final static Long ACCESS_TOKEN_EXPIRES_IN = 10_800L;

    public static String createToken(CoreUser user) {
        long expirationTime = ACCESS_TOKEN_EXPIRES_IN * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        return Jwts.builder()
                .setSubject(user.getUsername())   // guarda el username en subject
                .claim("userId", user.getId())    // guarda id como claim simple
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                .compact();
    }


    // Obtener usuario desde token
    public static Integer getUserFromToken(String token) {
        token = token.substring(7).trim(); // quitar "Bearer "
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        Integer userId = claims.get("userId", Integer.class);
        return userId;
    }

    public static Object getAuthorization(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String user = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (ExpiredJwtException e) {
            return "Token expirado";
        } catch (JwtException e) {
            return "Token inválido";
        }
    }
}
