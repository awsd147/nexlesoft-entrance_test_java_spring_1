package javaSpringEntranceExam.utils;

import javaSpringEntranceExam.services.serviceImpls.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

@Component
public class JwtUtils {

    private static int jwtExpirationMs = 864000;

    private static int refreshExpirationMs = 25920000;

    private static Date now = new Date();

    public static String generateToken(UserDetailsImpl userDetails) {
        Date expiryDate = DateTimeUtils.addDateSeconds(now,jwtExpirationMs );
        String token = Jwts.builder()
                .setId(String.valueOf(userDetails.getId()))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
        return token;
    }

    public static String generateRefreshToken(UserDetailsImpl userDetails) {
        Date expiryDate = DateTimeUtils.addDateSeconds(now, refreshExpirationMs);
        String refreshToken = Jwts.builder()
                .setId(String.valueOf(userDetails.getId()))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
        return refreshToken;
    }

}