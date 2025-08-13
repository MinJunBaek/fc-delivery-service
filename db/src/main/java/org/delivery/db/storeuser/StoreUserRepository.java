package org.delivery.db.storeuser;

import java.util.Optional;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUserEntity, Long> {
  Optional<StoreUserEntity> findFirstByEmailAndStatusOrderByIdDesc(String email, StoreUserStatus status);
}
