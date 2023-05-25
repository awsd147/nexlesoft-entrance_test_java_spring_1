package javaSpringEntranceExam.services.serviceImpls;

import javaSpringEntranceExam.domains.request.LoginRequest;
import javaSpringEntranceExam.domains.request.SignupRequest;
import javaSpringEntranceExam.domains.response.SignupResponse;
import javaSpringEntranceExam.models.User;
import javaSpringEntranceExam.repositories.UserRepository;
import javaSpringEntranceExam.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        User user = new User();
        BeanUtils.copyProperties(signupRequest, user);
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        return new SignupResponse(result.getId(),result.getEmail(),  result.getFirstName(), result.getLastName());
    }

    @Override
    public User findByEmail(LoginRequest loginRequest) {
        return userRepository.findUserByEmail(loginRequest.getEmail());
    }

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}
