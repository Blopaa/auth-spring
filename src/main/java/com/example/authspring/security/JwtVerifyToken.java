package com.example.authspring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtVerifyToken {
    public String verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("superSecret");
            JWTVerifier verifier = JWT.require(algorithm).withClaimPresence("email").build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("email").asString();
        }catch (JWTVerificationException e){
            return null;
        }
    }
}
