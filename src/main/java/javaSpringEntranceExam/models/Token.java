package javaSpringEntranceExam.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERID")
    private Long userId;

    @Column(name = "REFRESHTOKEN", length = 250, nullable = false)
    private String refreshToken;

    @Column(name = "EXPIRESIN", length = 64, nullable = false)
    private String expiresIn;


//    public String getExpiryDate() {
//        return expiresIn;
//    }

//    @Column(name = "createdAt")
//    private LocalDateTime createdAt;

//    @Column(name = "updatedAt")
//    private LocalDateTime updatedAt;

}
