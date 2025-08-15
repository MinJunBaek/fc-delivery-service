package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

  private final StoreUserService storeUserService;
  private final StoreRepository storeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    StoreUserEntity storeUserEntity = storeUserService.getRegisterUser(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    StoreEntity storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(storeUserEntity.getStoreId(), StoreStatus.REGISTERED)
        .orElseThrow(() -> new NullPointerException(storeUserEntity.getStoreId().toString()));

    UserSession userSession = UserSession.builder()
        .userId(storeUserEntity.getId())
        .email(storeUserEntity.getEmail())
        .password(storeUserEntity.getPassword())
        .status(storeUserEntity.getStatus())
        .role(storeUserEntity.getRole())
        .registeredAt(storeUserEntity.getRegisteredAt())
        .lastLoginAt(storeUserEntity.getLastLoginAt())
        .unRegisteredAt(storeUserEntity.getUnRegisteredAt())
        .storeId(storeEntity.getId())
        .storeName(storeEntity.getName())
        .build();

    return userSession;
  }
}
