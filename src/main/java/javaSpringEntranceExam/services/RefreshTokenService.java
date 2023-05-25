package javaSpringEntranceExam.services;

import javaSpringEntranceExam.models.Token;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface RefreshTokenService {

//    TokenResponse refreshToken(TokenRequest tokenRequest);
    Token findByToken(String refreshToken);

    Boolean verifyExpiration(String expired) throws ParseException;
}
