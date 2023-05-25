package javaSpringEntranceExam.domains.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String displayName;

    public SignupResponse(Long id,String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = this.firstName +" "+ this.lastName;
    }

}
