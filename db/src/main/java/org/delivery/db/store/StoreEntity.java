package org.delivery.db.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity(name = "stores")
public class StoreEntity extends BaseEntity {

  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 150, nullable = false)
  private String address;

  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreStatus status;

  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreCategory category;

  private double star;

  @Column(length = 200, nullable = false)
  private String thumbnailUrl;

  @Column(length = 11, scale = 4, nullable = false)
  private BigDecimal minimumAmount;

  @Column(length = 11, scale = 4, nullable = false)
  private BigDecimal minimumDeliveryAmount;

  @Column(length = 20)
  private String phoneNumber;
}
