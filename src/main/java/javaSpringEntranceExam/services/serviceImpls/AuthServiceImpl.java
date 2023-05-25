package javaSpringEntranceExam.services.serviceImpls;

import javaSpringEntranceExam.domains.request.LoginRequest;
import javaSpringEntranceExam.domains.response.LoginResponse;
import javaSpringEntranceExam.models.Token;
import javaSpringEntranceExam.models.User;
import javaSpringEntranceExam.services.UserService;
import javaSpringEntranceExam.utils.DateTimeUtils;
import javaSpringEntranceExam.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AuthServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private UserService userService;

    private static int refreshExpirationMs = 86400000;

    private static Date now = new Date();


    /**
     * @param request
     * @return
     */
    public LoginResponse login(LoginRequest request) {
        String username = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        String token = JwtUtils.generateToken(userDetailsImpl);
        String refreshToken = JwtUtils.generateRefreshToken(userDetailsImpl);
        User user = userService.findUserById(userDetailsImpl.getId());

        Token obj = new Token();
        obj.setUserId(userDetailsImpl.getId());
        obj.setRefreshToken(refreshToken);
        obj.setExpiresIn(DateTimeUtils.converDateTime(now, refreshExpirationMs));

        tokenService.save(obj);
        return new LoginResponse(user.getEmail(),user.getFirstName(),user.getLastName(),token, refreshToken);
    }
}
