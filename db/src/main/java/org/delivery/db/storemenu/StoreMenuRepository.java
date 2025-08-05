package org.delivery.db.storemenu;

import java.util.List;
import java.util.Optional;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity, Long> {

  // 유효한 메뉴체크
  /* SELECT * FROM store_menus WHERE id = ? AND status = ? Order By id DESC LIMIT 1; */
  Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

  // 특정 가게의 메뉴 가져오기
  /* SELECT * FROM store_menus WHERE store_id = ? AND status = ? Order By sequence DESC; */
  List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDesc(Long storeId, StoreMenuStatus status);
}
