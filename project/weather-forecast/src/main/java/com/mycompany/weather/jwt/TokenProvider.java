package com.mycompany.weather.jwt;

import static  com.mycompany.weather.jwt.Constants.REMEMBERME_VALIDITY_SECONDS;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;


public class TokenProvider {
    static final String AUTHORITIES_KEY ="auth";

    String secretKey;

    long tokenValidity;

    long tokenValidityForRememberMe;

    //TODO: Need to change @PostConstruct in other way
    @PostConstruct
    public void init(){
        this.secretKey = "my-secret-jwt-key";
        this.tokenValidity = TimeUnit.HOURS.toMillis(10);
        //TODO: Need to do it in better way this tokenValidityForRememberMe initialization
        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBERME_VALIDITY_SECONDS);
    }

    public String createToken(String username, Set<String> authorities, Boolean rememberMe){
        long now = (new Date()).getTime();
        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;

        return Jwts.builder()
            .setSubject(username)
            .claim(AUTHORITIES_KEY, authorities.stream().collect(Collectors.joining(",")))
            .signWith(SignatureAlgorithm.HS512,secretKey)
            .setExpiration(new Date(now + validity))
            .compact();
    }

    public  JWTCredential getCredential(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Set<String> authorities
                = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
                .stream()
                .collect(Collectors.toSet());

        return  new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        }
        catch(SignatureException e){
             e.getMessage();
             return false;
        }
    }
}
