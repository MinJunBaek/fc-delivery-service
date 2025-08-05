package org.delivery.db.storemenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity(name = "store_menus")
public class StoreMenuEntity extends BaseEntity {

  @Column(nullable = false)
  private Long storeId;
  @Column(length = 100, nullable = false)
  private String name;
  @Column(precision = 11, scale = 4, nullable = false)
  private BigDecimal amount;
  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreMenuStatus status;
  @Column(length = 200, nullable = false)
  private String thumbnailUrl;
  private int likeCount; // 기본이 0이기 때문에
  private int sequence; // 순서

  public void register(StoreMenuStatus status) {
    this.status = status;
  }

  public static StoreMenuEntity of(Long storeId, String name, BigDecimal amount, String thumbnailUrl) {
    StoreMenuEntity entity = new StoreMenuEntity();
    entity.storeId = storeId;
    entity.name = name;
    entity.amount = amount;
    entity.thumbnailUrl = thumbnailUrl;
    return entity;
  }
}
