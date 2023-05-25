package javaSpringEntranceExam.services;

import javaSpringEntranceExam.domains.request.LoginRequest;
import javaSpringEntranceExam.domains.request.SignupRequest;
import javaSpringEntranceExam.domains.response.SignupResponse;
import javaSpringEntranceExam.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    SignupResponse signup(SignupRequest signupRequest);

    User findByEmail(LoginRequest loginRequest);

    Boolean existByEmail(String email);

    User findUserById(Long userId);
}
