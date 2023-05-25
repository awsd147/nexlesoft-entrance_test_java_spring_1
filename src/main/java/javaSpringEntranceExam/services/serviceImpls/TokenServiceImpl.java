package javaSpringEntranceExam.services.serviceImpls;

import javaSpringEntranceExam.models.Token;
import javaSpringEntranceExam.repositories.TokenRepository;
import javaSpringEntranceExam.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;
    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }
}
