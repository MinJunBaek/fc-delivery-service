package org.delivery.db.user;

import java.util.Optional;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /*
  SELECT *
  FROM user
  WHERE id = ? AND status = ?
  ORDER BY id DESC
  limit 1;
   */
  Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status); // 쿼리메서드

  /*
  SELECT *
  FROM user
  WHERE email = ? AND password = ? AND status
  ORDER BY id DESC
  LIMIT 1;
   */
  Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
