package javaSpringEntranceExam.domains.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String displayName;

    private String accessToken;

    private String refreshToken;

    public LoginResponse(String email, String firstName, String lastName, String accessToken, String refreshToken) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.displayName = this.firstName +" "+ this.lastName;
    }

}