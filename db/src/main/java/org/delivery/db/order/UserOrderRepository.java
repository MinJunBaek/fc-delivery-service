package org.delivery.db.order;

import java.util.List;
import java.util.Optional;
import org.delivery.db.order.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

  List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus userOrderStatus);

  Optional<UserOrderEntity> findFirstByIdAndStatusAndUserId(Long id, UserOrderStatus userOrderStatus, Long userId);

  List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> userOrderStatus);
}
