package javaSpringEntranceExam.domains.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String token;

    private String refreshToken;

    public TokenResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

}
