package org.delivery.db.store;

import java.util.List;
import java.util.Optional;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

  // 특정ID를 가진 유효한 스토어 조회
  /* SELECT * FROM STORE WHERE id = ? AND status = 'REGISTERED' ORDER BY id DESC */
  Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

  // 유효한 스토어 리스트 조회
  /* SELECT * FROM STORE WHERE status = 'UNREGISTERED' ORDER BY id DESC */
  List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);

  // 유효한 특정 카테고리의 스토어 리스트 조회
  List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory storeCategory);

  // 가게 이름으로 스토어 조회하기
  /* SELECT * FROM Store WHERE name = ? AND status = ? >LIMIT 1 */
  Optional<StoreEntity> findFirstByNameAndStatus(String name, StoreStatus status);
}
