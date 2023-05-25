package javaSpringEntranceExam.services;

import javaSpringEntranceExam.models.Token;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    Token save(Token token);
}
