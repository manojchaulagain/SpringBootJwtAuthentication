package com.anoush.authentication.security.jwt;

import com.anoush.authentication.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${anoush.app.jwtSecret}")
    private String jwtSecret;

    @Value("${anoush.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {0} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {0}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {0}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {0}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {0}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}