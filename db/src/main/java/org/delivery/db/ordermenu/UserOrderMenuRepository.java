package org.delivery.db.ordermenu;

import java.util.List;
import org.delivery.db.ordermenu.enums.UserOrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenuEntity, Long> {

  List<UserOrderMenuEntity> findAllByUserOrderIdAndStatus(Long userOrderId, UserOrderMenuStatus userOrderMenuStatus);
}
