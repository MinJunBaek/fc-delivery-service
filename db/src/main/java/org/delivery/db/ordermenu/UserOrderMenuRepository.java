package org.delivery.db.ordermenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenuEntity, Long> {

}
