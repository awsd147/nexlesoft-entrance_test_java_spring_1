package javaSpringEntranceExam.repositories;

import javaSpringEntranceExam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   Boolean existsByEmail(String email);

   @Query("SELECT u FROM User u WHERE u.email = ?1")
   public User findUserByEmail(String email);

   User findUserById(Long userId);

}
