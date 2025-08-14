package org.delivery.storeadmin.domain.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.db.storeuser.StoreUserRepository;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserService {

  private final StoreUserRepository storeUserRepository;

  private final PasswordEncoder passwordEncoder; // Bean으로 주입했기 때문에 불러오는게 가능

  public StoreUserEntity register(StoreUserEntity storeUserEntity) {
    storeUserEntity.register(StoreUserStatus.REGISTERED, passwordEncoder.encode(storeUserEntity.getPassword()));
    storeUserRepository.save(storeUserEntity);
    return storeUserEntity;
  }

  public Optional<StoreUserEntity> getRegisterUser(String email) {
    return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
  }
}
