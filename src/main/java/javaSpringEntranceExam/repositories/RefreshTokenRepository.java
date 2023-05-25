package javaSpringEntranceExam.repositories;

import javaSpringEntranceExam.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RefreshTokenRepository extends JpaRepository<Token, Long> {
      Token findByRefreshToken(String refreshToken);
}
