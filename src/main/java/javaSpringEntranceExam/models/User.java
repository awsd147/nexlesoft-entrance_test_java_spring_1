package javaSpringEntranceExam.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, length = 250, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 250)
    private String password;

//    @Column(name = "createdAt", columnDefinition = "CURRENT_TIMESTAMP")
//    private Timestamp createdAt;
//
//    @Column(name = "updatedAt", columnDefinition = "CURRENT_TIMESTAMP")
//    private Timestamp updatedAt;


}

