package javaSpringEntranceExam.domains.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenRequest {

    @NotBlank
    private String refreshToken;
}
