package org.delivery.db.store.enums;

public enum StoreCategory {

  KOREAN_FOOD("한식", "한식"),
  CHINESE_FOOD("중식", "중식"),
  WESTERN_FOOD("양식", "양식"),
  JAPANESE_FOOD("일식", "일식"),
  CHICKEN("치킨", "치킨"),
  PIZZA("피자", "피자"),
  HAMBURGER("햄버거", "햄버거"),
  COFFEE_TEA("커피&차", "커피&차"),
  ;

  private String display;
  private String description;

  StoreCategory(String display, String description) {
    this.display = display;
    this.description = description;
  }
}
