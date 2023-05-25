package javaSpringEntranceExam.controller;

import jakarta.validation.Valid;
import javaSpringEntranceExam.domains.request.LoginRequest;
import javaSpringEntranceExam.domains.request.SignupRequest;
import javaSpringEntranceExam.domains.request.TokenRequest;
import javaSpringEntranceExam.domains.response.SignupResponse;
import javaSpringEntranceExam.domains.response.TokenResponse;
import javaSpringEntranceExam.services.RefreshTokenService;
import javaSpringEntranceExam.services.UserService;
import javaSpringEntranceExam.services.serviceImpls.AuthServiceImpl;
import javaSpringEntranceExam.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthServiceImpl authServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    /*
      -- link api to access -- : http://localhost:8080/api/sign-up
      -- Payload to signUp --
      {
        "firstName":"xx",
        "lastName":"xx",
        "email":"xx@gmail.com",
        "password":"123456"
       }
     */
    @PostMapping("/api/sign-up")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (userService.existByEmail((signupRequest.getEmail()))) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        SignupResponse signupResponse = userService.signup(signupRequest);

        return ResponseEntity.ok(signupResponse);
    }

    /*
      --link api to access-- : http://localhost:8080/api/sign-in
      -- Payload to signIn --
      {
        "email":"xx@gmail.com",
        "password":"123456"
       }
    */
    @PostMapping("/api/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (!userService.existByEmail((loginRequest.getEmail()))) {
            return ResponseEntity.badRequest().body("Error: User not found");
        }

        return ResponseEntity.ok(authServiceImpl.login(loginRequest));
    }

    /*
      -- link api to access -- : http://localhost:8080/api/refresh-token
      -- Payload to refresh-token --
      {
        "refreshToken":"xxx"
      }
    */
    @PostMapping("/api/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRequest tokenRequest) throws ParseException {
        Boolean isExpired = refreshTokenService.verifyExpiration(refreshTokenService.findByToken(tokenRequest.getRefreshToken()).getExpiresIn());
        if(isExpired){
            //Create new refresh token when expired -> need to sign in to create new token & new refreshToken.
            // TODO
            return null;
        }
            String email = userService.findUserById(refreshTokenService.findByToken(tokenRequest.getRefreshToken()).getUserId()).getEmail();
            String newToken = Jwts.builder().setSubject(email).setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 86400000)).signWith(SignatureAlgorithm.HS512, Keys.secretKeyFor(SignatureAlgorithm.HS512)).compact();
        return ResponseEntity.ok(new TokenResponse(newToken, tokenRequest.getRefreshToken()));
    }
}
