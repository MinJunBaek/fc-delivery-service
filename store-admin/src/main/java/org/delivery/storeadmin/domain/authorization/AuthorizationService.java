package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUserEntity;
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    StoreUserEntity storeUserEntity = storeUserService.getRegisterUser(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    // Role도 기본적으로 넣는게 필수이다.
    UserDetails user = User.builder()
        .username(storeUserEntity.getEmail())
        .password(storeUserEntity.getPassword())
        .roles(storeUserEntity.getRole().toString())
        .build();
    return user;
  }
}
